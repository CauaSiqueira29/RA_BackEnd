package com.example.ReconstruindoAtitudes.DTOs.Horario;

import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorGetDTO;
import com.example.ReconstruindoAtitudes.Model.HorarioModel;

import java.time.LocalTime;

public record HorarioGetDto(Long id,
                            String emailMentor,
                            LocalTime horario,
                            boolean agendado) {

    public HorarioGetDto(HorarioModel horario){
        this(horario.getId(), horario.getMentor().email, horario.getHorario(), horario.isAgendado());
    }

}
