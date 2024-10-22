package com.example.ReconstruindoAtitudes.DTOs.Mentor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MentorPostDTO(@NotBlank String nome,
                            @NotBlank @Email(message = "E-mail Inválido") String email,
                            @NotBlank String senha,
                            @NotBlank String bio) {
}
