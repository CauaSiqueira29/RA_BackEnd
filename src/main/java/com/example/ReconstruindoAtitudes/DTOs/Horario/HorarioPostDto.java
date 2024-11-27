package com.example.ReconstruindoAtitudes.DTOs.Horario;

import java.time.LocalTime;

public record HorarioPostDto(Long mentorId,
                             LocalTime horario) {
}
