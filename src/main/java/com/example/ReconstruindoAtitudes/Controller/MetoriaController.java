package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.Model.AgendarMentoriaModel;
import com.example.ReconstruindoAtitudes.services.MentoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mentoria")
@CrossOrigin(origins = "*")
public class MetoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarMentoria(@RequestBody @Valid AgendarMentoriaModel request) {
        String resultado = mentoriaService.agendarMentoria(request.getMentorId(), request.getHorario());
        return ResponseEntity.ok(resultado);
    }

}
