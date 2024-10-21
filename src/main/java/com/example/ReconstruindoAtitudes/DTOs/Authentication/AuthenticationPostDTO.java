package com.example.ReconstruindoAtitudes.DTOs.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationPostDTO(@NotBlank @Email(message = "E-mail Inválido") String email,
                                    @NotBlank String senha) {
}
