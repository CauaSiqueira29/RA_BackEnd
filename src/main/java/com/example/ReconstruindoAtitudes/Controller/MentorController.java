package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPutDTO;
import com.example.ReconstruindoAtitudes.services.AnamneseService;
import com.example.ReconstruindoAtitudes.services.MentorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mentor")
@CrossOrigin("**")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @Autowired
    private AnamneseService anamneseService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> postMentor(@RequestBody @Valid MentorPostDTO data) {
        return mentorService.cadastrarMentor(data);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationTokenGetDto> loginMentor(@RequestBody @Valid AuthenticationPostDTO data) {
        return mentorService.loginMentor(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> getMentores() {
        return ResponseEntity.status(HttpStatus.OK).body(mentorService.listarMentores());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MentorGetDTO> getMentorById(@PathVariable Long id) {
        return mentorService.retornaMentorPorId(id);
    }

    @GetMapping("/listarAnamneses/{mentorId}")
    public ResponseEntity<?> getAnamnesePorAgendamento(@PathVariable Long mentorId){
        return anamneseService.retornaAnamneseAgendada( mentorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentorGetDTO> atualizaMentor(@RequestBody @Valid MentorPutDTO data, @PathVariable Long id) {
        return mentorService.atualizarMentor(data, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MentorGetDTO> deletarMentor(@PathVariable Long id) {
        return mentorService.deletaMentorPorId(id);
    }

}
