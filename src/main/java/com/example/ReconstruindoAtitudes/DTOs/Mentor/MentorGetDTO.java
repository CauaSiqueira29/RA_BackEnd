package com.example.ReconstruindoAtitudes.DTOs.Mentor;

import com.example.ReconstruindoAtitudes.Model.MentorModel;

public record MentorGetDTO(long id,
                           String nome,
                           String bio) {
    public MentorGetDTO(MentorModel mentor){
        this(mentor.getId(), mentor.getName(), mentor.getBio());
    }
}
