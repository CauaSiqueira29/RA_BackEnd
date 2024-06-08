package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.InstituicaoPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="instituicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String senha;

    private String cnpj;

    public InstituicaoModel(InstituicaoPostDTO data){
        this.nome = data.nome();
        this.senha = data.senha();
        this.cnpj = data.cnpj();
    }
}