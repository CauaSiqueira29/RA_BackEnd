package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPutDTO;
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

    public ResponseEntity<MentoriaGetDTO> retornaMentoriaPorId(Long id){
        var procuraMentoria = repository.findById(id);

        if(procuraMentoria.isPresent()){
            var mentoria = procuraMentoria.get();
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<MentoriaGetDTO> atualizarMentoria(MentoriaPutDTO data, Long id){
        var proocuraMentoria = repository.findById(id);

        if (proocuraMentoria.isPresent()){
            var mentoria = proocuraMentoria.get();

            if(data.hora() != null){
                mentoria.setHora(data.hora());
            }

            repository.save(mentoria);
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<MentoriaGetDTO> deletaMentoria(Long id){
        var procuraMentoria = repository.findById(id);

        if (procuraMentoria.isPresent()){
            var mentoria = procuraMentoria.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();

    }
}
