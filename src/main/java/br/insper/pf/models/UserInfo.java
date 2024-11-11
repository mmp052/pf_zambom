package br.insper.pf.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String username;
    private String role;
    private boolean isAuthenticated;

    // Getters e Setters
}

