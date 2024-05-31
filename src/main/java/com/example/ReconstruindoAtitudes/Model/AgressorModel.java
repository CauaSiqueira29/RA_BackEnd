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

    private String senha;

    private String nome;

    private String contato;

    private String dataDeNascimento;

    private String estadoCivil;

    private String genero;

    private String profissao;

    @ElementCollection
    private List<String> perguntas;

    public AgressorModel(AgressorPostDTO data){
        this.email = data.email();
        this.senha = data.senha();
        this.nome = data.nome();
        this.contato = data.contato();
        this.dataDeNascimento = data.dataDeNascimento();
        this.estadoCivil = data.estadoCivil();
        this.genero = data.genero();
        this.profissao = data.profissao();
        this.perguntas = data.perguntas();
    }
}
