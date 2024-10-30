package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPutDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private MentoradoRepository mentoradoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public ResponseEntity<String> agendarMentoria(MentoriaPostDTO data){
        var procuraMentorado = mentoradoRepository.findById(data.mentorId());
        var procuraMentor = mentorRepository.findById(data.mentoradoId());

        if (procuraMentorado.isPresent() && procuraMentor.isPresent()){
            var mentoria = new MentoriaModel(data, procuraMentor.get(), procuraMentorado.get());

            mentoriaRepository.save(mentoria);

            return ResponseEntity.ok("Mentoria agendada com sucesso");
        }

        return ResponseEntity.badRequest().body("Não foi encontrado um mentorado com id: " + data.mentoradoId() +
                " ou um mentor com id: " + data.mentorId());
    }

    public ResponseEntity<List<MentoriaGetDTO>> listarMentorias(){
        return ResponseEntity.ok(mentoriaRepository.findAll().stream().map(MentoriaGetDTO::new).toList());
    }

    public ResponseEntity<MentoriaGetDTO> retornaMentoriaPorId(Long id){
        var procuraMentoria = mentoriaRepository.findById(id);

        if(procuraMentoria.isPresent()){
            var mentoria = procuraMentoria.get();
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<MentoriaGetDTO> atualizarMentoria(MentoriaPutDTO data, Long id){
        var proocuraMentoria = mentoriaRepository.findById(id);

        if (proocuraMentoria.isPresent()){
            var mentoria = proocuraMentoria.get();

            if(data.hora() != null){
                mentoria.setHora(data.hora());
            }

            mentoriaRepository.save(mentoria);
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<MentoriaGetDTO> deletaMentoria(Long id){
        var procuraMentoria = mentoriaRepository.findById(id);

        if (procuraMentoria.isPresent()){
            var mentoria = procuraMentoria.get();

            mentoriaRepository.deleteById(id);

            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();

    }
}
