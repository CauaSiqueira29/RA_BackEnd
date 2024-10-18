package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="mentores")
public class MentorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String bio;

    public MentorModel(MentorPostDTO data){
        this.name = data.nome();
        this.bio = data.bio();

    }

}
