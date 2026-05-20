package com.br.reusai.api.host.controller.data.request.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCredencialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

}
