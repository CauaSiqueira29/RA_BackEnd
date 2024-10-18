package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamneseGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamnesePostDTO;
import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.Repository.AnamneseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamneseService {

    @Autowired
    private AnamneseRepository repository;

    public ResponseEntity<AnamneseModel> cadastrarAnamnese(AnamnesePostDTO data){
        var anamnese = new AnamneseModel(data);
        repository.save(anamnese);
        return ResponseEntity.ok().body(anamnese);
    }

    public ResponseEntity<List<AnamneseGetDTO>> listarAnamneses(){
        return ResponseEntity.ok(repository.findAll().stream().map(AnamneseGetDTO::new).toList());
    }

}
