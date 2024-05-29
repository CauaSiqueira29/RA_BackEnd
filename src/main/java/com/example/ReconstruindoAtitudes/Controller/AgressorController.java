package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.AgressorCadastroDTO;
import com.example.ReconstruindoAtitudes.DTOs.AgressorDTO;
import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agressor")
public class AgressorController {

    @Autowired
    private AgressorRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<AgressorModel> postAgressor(@RequestBody @Valid AgressorCadastroDTO data){
        AgressorModel agressor = new AgressorModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(agressor));
    }

    @GetMapping("/retornacadastrados")
    public List<AgressorDTO> getAgressores(){
        List<AgressorDTO> agressorList = repository.findAll().stream().map(AgressorDTO::new).toList();
        return agressorList;
    }
}
