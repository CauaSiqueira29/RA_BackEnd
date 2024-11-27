package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

import java.time.LocalDateTime;

public record MentoriaGetDTO(Long id,
                             MentorGetDTO mentor,
                             MentoradoGetDTO mentorado,
                             HorarioGetDto horario) {

    public MentoriaGetDTO(MentoriaModel mentoria) {
        this(mentoria.getId(), new MentorGetDTO(mentoria.getMentor()), new MentoradoGetDTO(mentoria.getMentorado()),
                new HorarioGetDto(mentoria.getHorario()));
    }
}
