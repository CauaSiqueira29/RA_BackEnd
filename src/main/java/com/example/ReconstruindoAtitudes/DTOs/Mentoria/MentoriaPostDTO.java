package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record   MentoriaPostDTO(@NotNull Long mentorId,
                              @NotNull Long horarioId,
                              @NotNull Long mentoradoId) {
    
}
