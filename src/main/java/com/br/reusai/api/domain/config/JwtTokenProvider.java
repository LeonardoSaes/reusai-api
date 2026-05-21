package com.br.reusai.api.domain.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.reusai.api.domain.exception.InvalidJwtAuthenticationException;
import com.br.reusai.api.host.controller.data.response.security.TokenDTO;
import com.br.reusai.api.gateway.UserGateway;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserGateway userGateway;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String userId, List<String> roles){
        long currentTimeSeconds = (System.currentTimeMillis() / 1000) - 2;
        Date now = new Date(currentTimeSeconds * 1000);

        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String accessToken = getAccessToken(userId, roles, now, validity);
        String refreshToken = getRefreshToken(userId, roles, validity);
        return new TokenDTO(
                userId,
                true,
                now,
                validity,
                accessToken,
                refreshToken
        );
    }

    public TokenDTO createRefreshToken(String refreshToken){
        if(refreshTokenContainsBearer(refreshToken)){
            refreshToken = refreshToken.substring("Bearer ".length());
        }

        JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(10).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);

        String userId = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return createAccessToken(userId, roles);
    }

    private static boolean refreshTokenContainsBearer(String refreshToken) {
        return StringUtils.isNotBlank(refreshToken) && refreshToken.startsWith("Bearer ");
    }

    private String getRefreshToken(String userId, List<String> roles, Date validity) {
        Date refreshTokenValidity = new Date(validity.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(new Date())
                .withExpiresAt(refreshTokenValidity)
                .withSubject(userId)
                .sign(algorithm);
    }

    private String getAccessToken(String userId, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userId)
                .withIssuer(issuerUrl)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        String sub = decodedJWT.getSubject();

        var userById = userGateway.getUserById(sub);
        if (userById != null) {
            return new UsernamePasswordAuthenticationToken(userById, "", userById.getAuthorities());
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(sub);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token){
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).acceptLeeway(10).build();
        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            DecodedJWT decodedJWT = decodedToken(token);
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        } catch(Exception e){
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
