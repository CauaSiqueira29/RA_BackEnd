package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;

public record MentorPostDTO(@NotBlank String nome,
                            @NotBlank String bio) {
}
