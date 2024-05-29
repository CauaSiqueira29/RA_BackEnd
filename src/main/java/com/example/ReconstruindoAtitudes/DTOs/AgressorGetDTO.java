package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

public record AgressorGetDTO(Long id, String email, String nome, String senha) {
    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(), agressor.getSenha());
    }
}
