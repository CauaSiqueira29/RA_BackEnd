package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

import java.time.LocalDate;

public record AgressorGetDTO(Long id,
                             String email,
                             String nome,
                             LocalDate dataNascimento){

    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(),
                agressor.getDataNascimento());
    }
}