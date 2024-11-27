package com.example.ReconstruindoAtitudes.DTOs.Mentorado;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Model.Role.UserRole;

import java.time.LocalDate;

public record MentoradoGetDTO(Long id,
                              String nome,
                              String email,
                              InstituicaoGetDTO instituicao,
                              LocalDate dataNascimento){

    public MentoradoGetDTO(MentoradoModel mentorado){
        this(mentorado.getId(), mentorado.getNome(), mentorado.getEmail(), new InstituicaoGetDTO(mentorado.getInstituicao()) , mentorado.getDataNascimento());
    }
}