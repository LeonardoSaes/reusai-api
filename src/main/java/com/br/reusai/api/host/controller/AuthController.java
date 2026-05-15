package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.auth.RefreshTokenUsecase;
import com.br.reusai.api.domain.usecase.auth.SigninUsecase;
import com.br.reusai.api.host.controller.data.request.security.AccountCredencialsDTO;
import com.br.reusai.api.host.controller.data.response.security.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication endpoints")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SigninUsecase signinUsecase;
    private final RefreshTokenUsecase refreshTokenUsecase;

    @Operation(summary = "Authenticates an user and return a token")
    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody AccountCredencialsDTO credencials){
        return ResponseEntity.status(HttpStatus.OK).body(signinUsecase.execute(credencials));
    }

    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping("/refresh/{username}")
    public ResponseEntity<TokenDTO> refresh(@PathVariable String username, @RequestHeader("Authorization") String refreshToken){
        return ResponseEntity.status(HttpStatus.OK).body(refreshTokenUsecase.execute(username, refreshToken));
    }
}
