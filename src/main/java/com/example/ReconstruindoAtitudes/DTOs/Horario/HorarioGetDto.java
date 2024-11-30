package com.example.ReconstruindoAtitudes.DTOs.Horario;

import com.example.ReconstruindoAtitudes.Model.HorarioModel;

import java.time.LocalDateTime;

public record HorarioGetDto(Long id,
                            String emailMentor,
                            LocalDateTime horario,
                            boolean agendado) {

    public HorarioGetDto(HorarioModel horario){
        this(horario.getId(), horario.getMentor().email, horario.getHorario(), horario.isAgendado());
    }

}
