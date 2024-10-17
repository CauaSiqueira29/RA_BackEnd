package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentoria")
@CrossOrigin(origins = "*")
public class MentoriaController {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarMentoria(@Valid @RequestBody MentoriaDTO data) {
        MentoriaModel mentoria = new MentoriaModel(data);
        mentoriaRepository.save(mentoria);
        return ResponseEntity.ok("Mentoria agendada com sucesso!");
    }

    @GetMapping("/agendamentos")
    public List<MentoriaModel> getMentorias() {
        return mentoriaRepository.findAll();
    }

}
