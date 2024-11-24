package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationSenhaPutDto;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.services.InstituicaoService;
import com.example.ReconstruindoAtitudes.services.MentorService;
import com.example.ReconstruindoAtitudes.services.MentoradoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("**")
public class AuthenticationController {

    @Autowired
    private MentoradoService mentoradoService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private InstituicaoService instituicaoService;

    @PostMapping("/login")
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/EsqueceuSenha")
    public ResponseEntity<?> alterarSenha(@RequestBody @Valid AuthenticationSenhaPutDto data){
        var procuraMentorado = mentoradoService.findByEmail(data.email());
        var procuraInstituicao = instituicaoService.findByEmail(data.email());
        var procuraMentor = mentorService.findByEmail(data.email());

        if (procuraMentorado.isPresent()){
            return mentoradoService.esqueceuSenha(data);
        }
        if (procuraInstituicao.isPresent()){
            return instituicaoService.esqueceuSenha(data);
        }
        if (procuraMentor.isPresent()){
            return mentorService.esqueceuSenha(data);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
