package com.example.ReconstruindoAtitudes.Controller;


import com.example.ReconstruindoAtitudes.DTOs.MentoriaDTO;
import com.example.ReconstruindoAtitudes.Model.Mentoria;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
@CrossOrigin(origins = "*")
public class MentoriaController {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarMentoria(@Valid @RequestBody MentoriaDTO mentoriaRequest) {
        Mentoria mentoria = new Mentoria();
        mentoria.setMentor(mentoriaRequest.getMentor());
        mentoria.setHour(mentoriaRequest.getHour());
        mentoria.setAgressorNome(mentoriaRequest.getAgressorNome());
        mentoria.setAgressorContato(mentoriaRequest.getAgressorContato());
        mentoriaRepository.save(mentoria);

        return ResponseEntity.ok("Mentoria agendada com sucesso!");
    }

    @GetMapping("/agenda")
    public List<Mentoria> getMentorias() {
        return mentoriaRepository.findAll();
    }

}
