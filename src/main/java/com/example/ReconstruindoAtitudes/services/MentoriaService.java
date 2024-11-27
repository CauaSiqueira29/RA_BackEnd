package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPutDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.Repository.HorarioRepository;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private MentoradoRepository mentoradoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    // Agenda mentoria
    public ResponseEntity<?> agendarMentoria(MentoriaPostDTO data) {
        var mentorado = mentoradoRepository.findById(data.mentoradoId()).orElseThrow(() ->
                new RuntimeException("Mentorado com id: " + data.mentoradoId() + " não encontrado"));
        var mentor = mentorRepository.findById(data.mentorId()).orElseThrow(() ->
                new RuntimeException("Mentor com id: " + data.mentorId() + " não encontrado"));
        var horario = horarioRepository.findById(data.horarioId())
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        if (horario.isAgendado()) {
            throw new RuntimeException("Este horário já está agendado");
        }

        horario.setAgendado(true);
        horarioRepository.save(horario);

        var mentoria = new MentoriaModel(data, mentor, mentorado, horario);

        mentoriaRepository.save(mentoria);

        //Serve para atualizar o mentorado e mentor com as mentorias.
        if (mentorado.getMentorias() == null) {
            mentorado.setMentorias(new ArrayList<>());
        }
        mentorado.getMentorias().add(mentoria);
        mentoradoRepository.save(mentorado);

        if (mentor.getMentorias() == null) {
            mentor.setMentorias(new ArrayList<>());
        }
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
        var procuraMentoria = mentoriaRepository.findById(id);

        if (procuraMentoria.isPresent()) {
            var mentoria = procuraMentoria.get();

            if (data.horarioId() != null) {
                var novoHorario = horarioRepository.findById(data.horarioId())
                        .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

                if (novoHorario.isAgendado()) {
                    throw new RuntimeException("Novo horário já está agendado!");
                }

                if (mentoria.getHorario() != null) {
                    var horarioAntigo = mentoria.getHorario();
                    horarioAntigo.setAgendado(false);
                    horarioRepository.save(horarioAntigo);
                }

                novoHorario.setAgendado(true);
                horarioRepository.save(novoHorario);
                mentoria.setHorario(novoHorario);
            }

            mentoriaRepository.save(mentoria);
            return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
        }

        return ResponseEntity.notFound().build();
    }

    // Deleta mentoria
    public ResponseEntity<MentoriaGetDTO> deletaMentoria(Long id) {
        var mentoria = mentoriaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Mentoria agendada não existe"));

        if (mentoria.getHorario() != null) {
            var horario = mentoria.getHorario();
            horario.setAgendado(false);
            horarioRepository.save(horario);
        }

        mentoriaRepository.deleteById(id);

        return ResponseEntity.ok().body(new MentoriaGetDTO(mentoria));
    }

}
