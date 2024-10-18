package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public ResponseEntity<InstituicaoModel> cadastrarInstituicao(InstituicaoPostDTO data){

        var instituicao = new InstituicaoModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoRepository.save(instituicao));
    }

    public ResponseEntity<List<InstituicaoGetDTO>> listarInstituicoes(){

        return ResponseEntity.ok(instituicaoRepository.findAll().stream().map(InstituicaoGetDTO::new).toList());
    }
}
