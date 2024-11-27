package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentorias")
public class MentoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private MentorModel mentor;

    @ManyToOne
    @JoinColumn(name = "mentorado_id", nullable = false)
    private MentoradoModel mentorado;

    @OneToOne
    @JoinColumn(name = "horario_id", nullable = false, unique = true)
    private HorarioModel horario;

    public MentoriaModel(MentoriaPostDTO data, MentorModel mentor, MentoradoModel mentorado, HorarioModel horario) {
        this.mentor = mentor;
        this.mentorado = mentorado;
        this.horario = horario;
    }
}
