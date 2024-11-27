package com.example.ReconstruindoAtitudes.DTOs.Instituicao;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;

public record InstituicaoGetDTO( Long IesId,
                                 String nome,
                                 String cnpj,
                                 String email) {

    public InstituicaoGetDTO(InstituicaoModel instituicao){
        this(instituicao.getId(), instituicao.getNome(), instituicao.getCnpj(), instituicao.getEmail());
    }
}
