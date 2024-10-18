package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository repository;

    public ResponseEntity<MentoriaModel> agendarMentoria(MentoriaPostDTO data){
        var mentoria = new MentoriaModel(data);
        repository.save(mentoria);
        return ResponseEntity.ok(mentoria);
    }

    public ResponseEntity<List<MentoriaGetDTO>> listarMentorias(){
        return ResponseEntity.ok(repository.findAll().stream().map(MentoriaGetDTO::new).toList());
    }
}
