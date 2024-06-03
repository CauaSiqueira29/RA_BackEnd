package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

import java.util.List;

//DTO que pega os dados
public record AgressorGetDTO(Long id, 
                             String email,
                             String nome,
                             String contato,
                             String dataNascimento,
                             String estadocivil,
                             String genero,
                             String profissao,
                             List<String> perguntas) {
    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(),
                agressor.getContato(), agressor.getDataNascimento(), agressor.getEstadocivil(),
                agressor.getGenero(), agressor.getProfissao(), agressor.getPerguntas());
    }
}
