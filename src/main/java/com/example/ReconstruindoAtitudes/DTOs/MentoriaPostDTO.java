package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MentoriaPostDTO(@NotNull LocalDateTime horario,
                              @NotBlank String nome) {
}
