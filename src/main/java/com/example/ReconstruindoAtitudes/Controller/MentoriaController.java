package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mentoria")
@CrossOrigin(origins = "*")
public class MetoriaController {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @PostMapping("/agendar")
    public ResponseEntity<MentoriaModel> agendarMentoria(@RequestBody @Valid MentoriaPostDTO data) {
        var mentoria = new MentoriaModel(data);
        mentoriaRepository.save(mentoria);
        return ResponseEntity.ok(mentoria);
    }

    @GetMapping("/mentorias")
    public List<MentoriaGetDTO> getMentorias(){
        return mentoriaRepository.findAll().stream().map(MentoriaGetDTO::new).toList();
    }

}
