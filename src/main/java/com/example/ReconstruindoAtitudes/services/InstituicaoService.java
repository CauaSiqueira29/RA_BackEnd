package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPutDTO;
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
    private InstituicaoRepository repository;

    public ResponseEntity<InstituicaoModel> cadastrarInstituicao(InstituicaoPostDTO data){

        var instituicao = new InstituicaoModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(instituicao));
    }

    public ResponseEntity<List<InstituicaoGetDTO>> listarInstituicoes(){

        return ResponseEntity.ok(repository.findAll().stream().map(InstituicaoGetDTO::new).toList());
    }

    public ResponseEntity<InstituicaoGetDTO> retornaInstituicaoPorId(Long id){
        var procuraInstituicao = repository.findById(id);

        if(procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<InstituicaoGetDTO> atualizarInstituicao(InstituicaoPutDTO data, Long id){
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();
            if (data.nome() != null){
                instituicao.setNome(data.nome());
            }

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<InstituicaoGetDTO> deletaInstituicaoPorId(Long id){
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

}
