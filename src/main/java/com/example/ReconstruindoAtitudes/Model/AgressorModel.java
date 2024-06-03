package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.AgressorPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgressorModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String nome;

    private String contato;

    private String dataNascimento;

    private String estadocivil;

    private String genero;

    private String profissao;

    @ElementCollection
    private List<String> perguntas;

    public AgressorModel(AgressorPostDTO data){
        this.email = data.email();
        this.nome = data.nome();
        this.contato = data.contato();
        this.dataNascimento = data.dataNascimento();
        this.estadocivil = data.estadocivil();
        this.genero = data.genero();
        this.profissao = data.profissao();
        this.perguntas = data.perguntas();
    }
}
