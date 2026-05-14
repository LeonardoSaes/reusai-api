package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.auth.AuthUsecase;
import com.br.reusai.api.host.controller.data.request.security.AccountCredencialsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication endpoints")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUsecase authUsecase;

    @Operation(summary = "Authenticates an user and return a token")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AccountCredencialsDTO credencials){
        return ResponseEntity.status(HttpStatus.OK).body(authUsecase.signIn(credencials));
    }
}
