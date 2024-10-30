package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.services.InstituicaoService;
import com.example.ReconstruindoAtitudes.services.MentorService;
import com.example.ReconstruindoAtitudes.services.MentoradoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private MentoradoService mentoradoService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private InstituicaoService instituicaoService;

    @PostMapping()
    public ResponseEntity<AuthenticationTokenGetDto> loginUsuarios(@RequestBody @Valid AuthenticationPostDTO data){
        var procuraMentorado = mentoradoService.findByEmail(data.email());
        var procuraInstituicao = instituicaoService.findByEmail(data.email());
        var procuraMentor = mentorService.findByEmail(data.email());

        if (procuraMentorado.isPresent()){
            return mentoradoService.loginMentorado(data);
        }
        if (procuraInstituicao.isPresent()){
            return instituicaoService.loginInstituicao(data);
        }
        if (procuraMentor.isPresent()){
            return mentorService.loginMentor(data);
        }

        return ResponseEntity.badRequest().build();
    }

}
