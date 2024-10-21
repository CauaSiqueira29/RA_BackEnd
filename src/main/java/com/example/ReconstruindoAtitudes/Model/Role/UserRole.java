package com.example.ReconstruindoAtitudes.Model.Role;

public enum UserRole {

    ADMIN("admin"),

    INSTITUICAO("instituicao"),

    MENTORADO("mentorado"),

    MENTOR("mentor");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
