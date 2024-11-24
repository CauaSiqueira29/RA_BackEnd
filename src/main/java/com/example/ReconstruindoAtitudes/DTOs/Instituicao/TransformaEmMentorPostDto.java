package com.example.ReconstruindoAtitudes.DTOs.Instituicao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransformaEmMentorPostDto(@NotBlank String email,
                                        @NotBlank String bio) {
}
