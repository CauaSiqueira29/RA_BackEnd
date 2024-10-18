package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Model.MentorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record MentoriaPostDTO(@NotBlank MentorModel mentor,
                              @NotNull LocalTime hour,
                              @NotBlank AgressorModel agressorNome,
                              @NotBlank AgressorModel agressorContato) {
    
}
