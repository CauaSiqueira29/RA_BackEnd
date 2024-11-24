package com.example.ReconstruindoAtitudes.DTOs.Authentication;

import jakarta.validation.constraints.Email;

public record AuthenticationSenhaPutDto(@Email String email,
                                        String senha) {
}
