package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
import com.example.ReconstruindoAtitudes.services.AgressorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agressor")
@CrossOrigin(origins = "*")
public class AgressorController {

    @Autowired
    private AgressorService service;

    @PostMapping("/cadastro")
    public ResponseEntity<AgressorModel> postAgressor(@RequestBody @Valid AgressorPostDTO data){
        return service.cadastrarAgressor(data);
    }

    @GetMapping("/retornacadastrados")
    public ResponseEntity<List<AgressorGetDTO>> getAgressores(){
        return service.listarAgressores();
    }


}
