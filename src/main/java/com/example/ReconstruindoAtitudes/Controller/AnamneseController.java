package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamneseGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamnesePostDTO;
import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.services.AnamneseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("anamnese")
public class AnamneseController {

    @Autowired
    private AnamneseService service;


    @PostMapping("/registrar")
    public ResponseEntity<AnamneseModel> resgistrarAnamnese(@RequestBody @Valid AnamnesePostDTO data){
        return service.cadastrarAnamnese(data);
    }

    public ResponseEntity<List<AnamneseGetDTO>> listarAnamneses(){
        return service.listarAnamneses();
    }

}
