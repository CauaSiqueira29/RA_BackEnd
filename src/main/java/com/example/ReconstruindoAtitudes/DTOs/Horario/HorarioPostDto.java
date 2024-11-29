package com.example.ReconstruindoAtitudes.DTOs.Horario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HorarioPostDto(@NotNull Long mentorId,
                             @NotNull LocalTime horario) {
}
