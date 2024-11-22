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

    // Agenda mentoria
    public ResponseEntity<?> agendarMentoria(MentoriaPostDTO data) {
        var mentorado = mentoradoRepository.findById(data.mentoradoId()).orElseThrow(() ->
                new RuntimeException("Mentorado com id: " + data.mentoradoId() + " não encontrado"));
        var mentor = mentorRepository.findById(data.mentorId()).orElseThrow(() ->
                new RuntimeException("Mentor com id: " + data.mentorId() + " não encontrado"));

        var mentoria = new MentoriaModel(data, mentor, mentorado);

        mentoriaRepository.save(mentoria);

        //Serve para atualizar o mentorado e mentor com as mentorias.
        mentorado.getMentorias().add(mentoria);
        mentoradoRepository.save(mentorado);

        mentor.getMentorias().add(mentoria);
        mentorRepository.save(mentor);

        return ResponseEntity.ok(new MentoriaGetDTO(mentoria));
    }

    // Retorna mentorias
    public ResponseEntity<List<MentoriaGetDTO>> listarMentorias() {
        return ResponseEntity.ok(mentoriaRepository.findAll().stream().map(MentoriaGetDTO::new).toList());
    }

    // Retorna mentorias por id
    public ResponseEntity<MentoriaGetDTO> retornaMentoriaPorId(Long id) {
        var procuraMentoria = mentoriaRepository.findById(id);

        if (procuraMentoria.isPresent()) {
            var mentoria = procuraMentoria.get();
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza mentoria
    public ResponseEntity<MentoriaGetDTO> atualizarMentoria(MentoriaPutDTO data, Long id) {
        var proocuraMentoria = mentoriaRepository.findById(id);

        if (proocuraMentoria.isPresent()) {
            var mentoria = proocuraMentoria.get();

            if (data.diaHora() != null) {
                mentoria.setDiaHora(data.diaHora());
            }

            mentoriaRepository.save(mentoria);
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    // Deleta mentoria
    public ResponseEntity<MentoriaGetDTO> deletaMentoria(Long id) {
        var procuraMentoria = mentoriaRepository.findById(id);

        if (procuraMentoria.isPresent()) {
            var mentoria = procuraMentoria.get();

            mentoriaRepository.deleteById(id);

            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();

    }
}
