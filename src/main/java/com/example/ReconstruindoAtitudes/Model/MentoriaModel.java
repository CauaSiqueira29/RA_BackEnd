package com.example.ReconstruindoAtitudes.Model;



import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

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

    private LocalTime hora;

    @OneToOne
    private AgressorModel agressorNome;

    @OneToOne
    private AgressorModel agressorContato;

    public MentoriaModel(MentoriaPostDTO data){
        this.mentor = data.mentor();
        this.hora = data.hora();
        this.agressorNome = data.agressorNome();
        this.agressorContato = data.agressorContato();
    }

}

