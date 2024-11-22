package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamneseGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamnesePostDTO;
import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.services.AnamneseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("anamnese")
@CrossOrigin("**")
public class AnamneseController {

    @Autowired
    private AnamneseService service;


    @PostMapping("/registrar")
    public ResponseEntity<?> resgistrarAnamnese(@RequestBody @Valid AnamnesePostDTO data){
        return service.cadastrarAnamnese(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnamneseGetDTO>> listarAnamneses(){
        return service.listarAnamneses();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<AnamneseGetDTO> retornaAnamnesePorId(@PathVariable Long id){
        return service.retornaAnamnesePorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnamneseGetDTO> deletaAnamnese(@PathVariable Long id){
        return service.deletaAnamnese(id);
    }

}
