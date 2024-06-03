package com.example.ReconstruindoAtitudes.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendarMentoriaModel {

    private Long mentorId;
    private LocalDateTime horario;

}
