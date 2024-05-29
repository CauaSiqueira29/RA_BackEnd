package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

public record AgressorDTO(Long id, String email, String nome, String senha) {
    public AgressorDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(), agressor.getSenha());
    }
}
