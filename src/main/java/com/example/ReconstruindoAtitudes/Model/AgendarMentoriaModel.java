package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.MentoriaPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="agendamentos")
@Entity
public class AgendarMentoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nomeMentor;

    private LocalDateTime horario;

    public AgendarMentoriaModel(MentoriaPostDTO data){
        this.nomeMentor = data.nome();
        this.horario = data.horario();
    }

}
