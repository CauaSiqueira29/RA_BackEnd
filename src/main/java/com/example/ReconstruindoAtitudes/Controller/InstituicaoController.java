package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.*;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @PostMapping("/cadastro/intituicao")
    public ResponseEntity<InstituicaoModel> postInstituicao(@RequestBody @Valid InstituicaoPostDTO data){
        var instituicao = new InstituicaoModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoRepository.save(instituicao));
    }

    @PostMapping("/cadastro/mentores")
    public ResponseEntity<MentorModel> postMentor(@RequestBody @Valid MentorPostDTO data){
        var mentor = new MentorModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorRepository.save(mentor));
    }

    @GetMapping("/retorna/instituicao")
    public List<InstituicaoGetDTO> getInstituicao(){
        return instituicaoRepository.findAll().stream().map(InstituicaoGetDTO::new).toList();
    }

    @GetMapping("/retorna/mentores")
    public List<MentorGetDTO> getMentores(){
        return mentorRepository.findAll().stream().map(MentorGetDTO::new).toList();
    }

    @GetMapping("/retorna/mentorias")
    public List<MentoriaGetDTO> getMentorias(){
        return mentoriaRepository.findAll().stream().map(MentoriaGetDTO::new).toList();
    }

    @DeleteMapping("/delete/mentor/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id){
        if (mentorRepository.existsById(id)) {
            mentorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
