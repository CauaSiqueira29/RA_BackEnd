package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

import java.util.List;

public record AgressorGetDTO(Long id, String email,
                             String nome,
                             String senha,
                             String contato,
                             String dataDeNascimento,
                             String estadoCivil,
                             String genero,
                             String profissao,
                             List<String> perguntas) {
    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(), agressor.getSenha(),
                agressor.getContato(), agressor.getDataDeNascimento(), agressor.getEstadoCivil(),
                agressor.getGenero(), agressor.getProfissao(), agressor.getPerguntas());
    }
}
