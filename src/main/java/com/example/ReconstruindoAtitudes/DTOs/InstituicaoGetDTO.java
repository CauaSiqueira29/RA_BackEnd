package com.example.ReconstruindoAtitudes.DTOs;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;

public record InstituicaoGetDTO(String nome, String cnpj) {

    public InstituicaoGetDTO(InstituicaoModel instituicao){
        this(instituicao.getNome(), instituicao.getCnpj());
    }
}
