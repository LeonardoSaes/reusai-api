package com.br.reusai.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private String photoUrl;
    private String cep;
    private List<Item> items;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    public User(String id, String username, String email, String password, String photoUrl, String cep, List<Item> items, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.photoUrl = photoUrl;
        this.cep = cep;
        this.items = items;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public User(String username, String email, String password, String photoUrl, String cep, List<Item> items) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.photoUrl = photoUrl;
        this.cep = cep;
        this.items = items;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
