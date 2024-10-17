package com.example.ReconstruindoAtitudes.DTOs.Mentor;

import jakarta.validation.constraints.NotBlank;

public record MentorPostDTO(@NotBlank String nome,
                            @NotBlank String bio) {
}
