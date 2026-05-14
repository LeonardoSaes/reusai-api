package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.user.CreateUserUsecase;
import com.br.reusai.api.host.controller.converter.UserControllerConverter;
import com.br.reusai.api.host.controller.data.request.CreateUserRequest;
import com.br.reusai.api.host.controller.data.response.CreateUserResponse;
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

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest request
    ) {
        String userId = createUserUsecase.execute(userControllerConverter.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userControllerConverter.toCreateUserResponse(userId));
    }
}
