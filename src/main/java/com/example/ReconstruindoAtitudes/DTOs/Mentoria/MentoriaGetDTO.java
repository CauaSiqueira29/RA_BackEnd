package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

import java.time.LocalDateTime;

public record MentoriaGetDTO(Long id, MentorModel mentor, MentoradoModel mentorado, LocalDateTime hora) {

    public MentoriaGetDTO(MentoriaModel mentoria) {
        this(mentoria.getId(), mentoria.getMentor(), mentoria.getMentorado(), mentoria.getHora());
    }
}
