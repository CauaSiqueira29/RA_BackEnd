package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record   MentoriaPostDTO(@NotNull Long mentorId,
                              @NotNull LocalDateTime hora,
                              @NotNull Long mentoradoId) {
    
}
