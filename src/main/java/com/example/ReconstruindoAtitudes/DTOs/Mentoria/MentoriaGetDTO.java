package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

import java.time.LocalTime;

public record MentoriaGetDTO(long id, String nomeMentor, LocalTime horario) {

    public MentoriaGetDTO(MentoriaModel mentoria) {
        this(mentoria.getId(), String.valueOf(mentoria.getMentor()), mentoria.getHour());
    }

}
