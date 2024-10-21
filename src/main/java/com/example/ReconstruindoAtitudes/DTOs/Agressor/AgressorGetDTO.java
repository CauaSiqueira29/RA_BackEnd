package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Model.Role.UserRole;

import java.time.LocalDate;

public record AgressorGetDTO(Long id,
                             String nome,
                             String email,
                             LocalDate dataNascimento,
                             UserRole role){

    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(), agressor.getDataNascimento(), agressor.getRole());
    }
}