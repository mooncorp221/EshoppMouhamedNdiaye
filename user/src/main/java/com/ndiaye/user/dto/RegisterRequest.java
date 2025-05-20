package com.ndiaye.user.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String username;
    private String email;
    private String password;
    private String role;
}
