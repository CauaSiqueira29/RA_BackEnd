package com.example.ReconstruindoAtitudes.DTOs.Mentor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MentorPostDTO(@NotBlank String nome,
                            @NotBlank @Email(message = "E-mail Inválido") String email,
                            @NotNull Long instituicaoId,
                            @NotBlank String senha,
                            @NotBlank String bio) {
}
