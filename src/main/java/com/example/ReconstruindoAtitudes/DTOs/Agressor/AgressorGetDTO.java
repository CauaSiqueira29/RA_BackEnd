package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;

import java.time.LocalDate;

public record AgressorGetDTO(Long id,
                             String email,
                             String nome,
                             String contato,
                             LocalDate dataNascimento,
                             String estadocivil,
                             String genero,
                             String profissao){

    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(),
                agressor.getContato(), agressor.getDataNascimento(), agressor.getEstadocivil(),
                agressor.getGenero(), agressor.getProfissao());
    }
}