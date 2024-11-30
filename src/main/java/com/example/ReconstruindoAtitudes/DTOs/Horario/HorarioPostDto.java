package com.example.ReconstruindoAtitudes.DTOs.Horario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HorarioPostDto(@NotNull Long mentorId,
                             @NotNull LocalDateTime horario) {
}
