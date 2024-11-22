package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentoria.MentoriaPutDTO;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import com.example.ReconstruindoAtitudes.services.MentoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mentoria")
@CrossOrigin("**")
public class MentoriaController {

    @Autowired
    private MentoriaService service;

    @PostMapping("/agendar")
    public ResponseEntity<?> agendarMentoria(@RequestBody @Valid MentoriaPostDTO data) {
        return service.agendarMentoria(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MentoriaGetDTO>> getMentorias(){
        return service.listarMentorias();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MentoriaGetDTO> getMentoriaById(@PathVariable Long id){
        return service.retornaMentoriaPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentoriaGetDTO> atualizaMentoriaById(@RequestBody @Valid MentoriaPutDTO data, @PathVariable Long id){
        return service.atualizarMentoria(data, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MentoriaGetDTO> deleteMentoriaById(@PathVariable Long id){
        return service.deletaMentoria(id);
    }

}
