package com.br.reusai.api.host.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.domain.usecase.user.CreateUserUsecase;
import com.br.reusai.api.domain.usecase.user.LoadMyProfileUsecase;
import com.br.reusai.api.host.controller.converter.UserControllerConverter;
import com.br.reusai.api.host.controller.data.request.CreateUserRequest;
import com.br.reusai.api.host.controller.data.response.CreateUserResponse;
import com.br.reusai.api.host.controller.data.response.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUsecase createUserUsecase;
    private final UserControllerConverter userControllerConverter;
    private final LoadMyProfileUsecase loadMyProfileUsecase;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest request
    ) {
        String userId = createUserUsecase.execute(userControllerConverter.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userControllerConverter.toCreateUserResponse(userId));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("Authorization") String token){
        String tokenWithoutBearer = token.replace("Bearer ", "");

        DecodedJWT jwt = JWT.decode(tokenWithoutBearer);
        String userId = jwt.getSubject();

        User userProfile = loadMyProfileUsecase.execute(userId);
        UserProfileResponse userProfileResponse = new UserProfileResponse(userProfile.getId(), userProfile.getUsername(),  userProfile.getEmail(), userProfile.getPhotoUrl());

        return ResponseEntity.status(HttpStatus.OK).body(userProfileResponse);
    }
}
