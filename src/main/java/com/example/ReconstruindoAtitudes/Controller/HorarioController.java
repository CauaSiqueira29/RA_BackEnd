package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioPostDto;
import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioPutDto;
import com.example.ReconstruindoAtitudes.services.HorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horario")
@CrossOrigin("**")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarHorario(@RequestBody @Valid HorarioPostDto data){
        return service.cadastrarHorario(data);
    }

    @GetMapping("/retorna/{mentorId}")
    public ResponseEntity<?> retornaHorariosDisponiveis(@PathVariable Long mentorId){
        return service.retornaHorariosDisponiveis(mentorId);
    }

    @PutMapping("/atualiza/{id}")
    public ResponseEntity<?> atualizaHorario(@RequestBody HorarioPutDto data, @PathVariable Long id){
        return service.atualizaHorario(data, id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletaHorario(@PathVariable Long id){
        return service.deletaHorario(id);
    }
}
