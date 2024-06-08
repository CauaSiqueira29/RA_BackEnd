package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.AgressorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
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
    private AgressorRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<AgressorModel> postAgressor(@RequestBody @Valid AgressorPostDTO data){
        AgressorModel agressor = new AgressorModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(agressor));
    }

    @GetMapping("/retornacadastrados")
    public List<AgressorGetDTO> getAgressores(){
        List<AgressorGetDTO> agressorList = repository.findAll().stream().map(AgressorGetDTO::new).toList();
        return agressorList;
    }


}
