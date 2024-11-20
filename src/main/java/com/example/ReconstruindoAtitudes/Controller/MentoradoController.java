package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPutDTO;
import com.example.ReconstruindoAtitudes.services.MentoradoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mentorado")
@CrossOrigin("**")
public class MentoradoController {

    @Autowired
    private MentoradoService service;

    @PostMapping("/cadastro")
    public ResponseEntity<?> postMentorado(@RequestBody @Valid MentoradoPostDTO data){
        return service.cadastrarMentorado(data);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationTokenGetDto> loginMentorado(@RequestBody @Valid AuthenticationPostDTO data){
        return service.loginMentorado(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MentoradoGetDTO>> getMentorados(){
        return service.listarMentorados();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MentoradoGetDTO> getMentoradoById(@PathVariable Long id){
        return service.retornaMentoradoPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentoradoGetDTO> atualizaMentorado(@RequestBody @Valid MentoradoPutDTO data, @PathVariable Long id){
        return service.atualizarMentorado(data, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MentoradoGetDTO> deletarMentorado(@PathVariable Long id){
        return service.deletaMentorado(id);
    }

}
