package com.example.ReconstruindoAtitudes.Model;



import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaDTO;
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
public class MentoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MentorModel mentor;
    private LocalTime hour;
    private AgressorModel agressorNome;
    private AgressorModel agressorContato;

    public MentoriaModel(MentoriaDTO data){
        this.mentor = data.mentor();
        this.hour = data.hour();
        this.agressorNome = data.agressorNome();
        this.agressorContato = data.agressorContato();
    }

}

