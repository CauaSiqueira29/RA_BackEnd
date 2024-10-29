package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Model.MentorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record MentoriaPostDTO(@NotBlank MentorModel mentor,
                              @NotNull LocalTime hora,
                              @NotBlank MentoradoModel mentoradoNome,
                              @NotBlank MentoradoModel mentoradoContato) {
    
}
