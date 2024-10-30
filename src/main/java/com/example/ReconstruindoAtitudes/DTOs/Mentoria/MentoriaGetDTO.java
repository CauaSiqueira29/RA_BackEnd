package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

import java.time.LocalDateTime;

public record MentoriaGetDTO(Long id, MentorGetDTO mentor, MentoradoGetDTO mentorado, LocalDateTime diaHora) {

    // dessa forma acaba com a recursividade, descoberta dessa possibilidade de utilizar um Dto dentro de outro Dto
    public MentoriaGetDTO(MentoriaModel mentoria) {
        this(mentoria.getId(), new MentorGetDTO(mentoria.getMentor()), new MentoradoGetDTO(mentoria.getMentorado()), mentoria.getDiaHora());
    }
}
