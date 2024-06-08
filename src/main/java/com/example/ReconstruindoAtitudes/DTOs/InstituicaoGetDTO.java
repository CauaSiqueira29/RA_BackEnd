package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;

public record InstituicaoGetDTO( String cnpj) {

    public InstituicaoGetDTO(InstituicaoModel instituicao){
        this(instituicao.getCnpj());
    }
}
