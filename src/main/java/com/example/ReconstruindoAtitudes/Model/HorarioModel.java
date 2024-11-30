package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mentor_id", "horario"})
})
public class HorarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private MentorModel mentor;

    private LocalDateTime horario;
    private boolean agendado;

    public HorarioModel(HorarioPostDto data, MentorModel mentor) {
        this.mentor = mentor;
        this.horario = data.horario();
        this.agendado = false;
    }
}
