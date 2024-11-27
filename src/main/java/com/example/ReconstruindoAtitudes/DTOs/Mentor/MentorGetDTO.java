package com.example.ReconstruindoAtitudes.DTOs.Mentor;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.Model.MentorModel;

import java.util.List;

public record MentorGetDTO(long id,
                           String nome,
                           String email,
                           List<InstituicaoGetDTO> instituicoes,
                           String bio) {

    public MentorGetDTO(MentorModel mentor){
        this(mentor.getId(), mentor.getName(), mentor.email,
                mentor.getInstituicoes().stream().map(InstituicaoGetDTO::new).toList(), mentor.getBio());
    }

}
