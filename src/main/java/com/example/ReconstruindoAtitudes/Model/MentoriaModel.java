package com.example.ReconstruindoAtitudes.Model;


import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentorias")
public class MentoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorModel mentor;

    private LocalDateTime diaHora;

    @ManyToOne
    @JoinColumn(name = "mentorado_id")
    private MentoradoModel mentorado;

    public MentoriaModel(MentoriaPostDTO data, MentorModel mentor, MentoradoModel mentorado){
        this.mentor = mentor;
        this.diaHora = data.diaHora();
        this.mentorado = mentorado;
    }

}

