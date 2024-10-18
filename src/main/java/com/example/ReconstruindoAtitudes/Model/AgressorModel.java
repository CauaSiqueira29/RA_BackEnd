package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPostDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="agressores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AgressorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String nome;

    private LocalDate dataNascimento;

    @OneToOne
    private MentoriaModel mentoria;

    public AgressorModel(AgressorPostDTO data){
        this.email = data.email();
        this.nome = data.nome();
        this.dataNascimento = data.dataNascimento();
    }
}
