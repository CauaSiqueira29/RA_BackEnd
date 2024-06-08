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
                             String pergunta1,
                             String pergunta2,
                             String pergunta3,
                             String pergunta4,
                             String pergunta5,
                             String pergunta6,
                             String pergunta7,
                             String pergunta8,
                             String pergunta9,
                             String pergunta10,
                             String pergunta11,
                             String pergunta12,
                             String pergunta13,
                             String pergunta14,
                             String pergunta15,
                             String pergunta16,
                             String pergunta17,
                             String pergunta18,
                             String pergunta19){

    public AgressorGetDTO(AgressorModel agressor){
        this(agressor.getId(), agressor.getNome(), agressor.getEmail(),
                agressor.getContato(), agressor.getDataNascimento(), agressor.getEstadocivil(),
                agressor.getGenero(), agressor.getProfissao(), agressor.getPergunta1(),
                agressor.getPergunta2(), agressor.getPergunta3(), agressor.getPergunta4(),
                agressor.getPergunta5(), agressor.getPergunta6(), agressor.getPergunta7(),
                agressor.getPergunta8(), agressor.getPergunta9(),agressor.getPergunta10(),
                agressor.getPergunta11(), agressor.getPergunta12(), agressor.getPergunta13(),
                agressor.getPergunta14(),agressor.getPergunta15(),agressor.getPergunta16(),
                agressor.getPergunta17(),agressor.getPergunta18(), agressor.getPergunta19());
    }
}