package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

import java.time.LocalDateTime;

public record MentoriaGetDTO(long id,
                             String nome,
                             LocalDateTime horario) {
    public MentoriaGetDTO(MentoriaModel mentoria) {
        this(mentoria.getId(), mentoria.getMentor().getName(), mentoria.getHorario());
    }
}
