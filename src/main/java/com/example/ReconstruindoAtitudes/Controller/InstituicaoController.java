package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.LoginPostDTO;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instituicao")
@CrossOrigin(origins = "*")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<InstituicaoModel> postInstituicao(@RequestBody @Valid InstituicaoPostDTO data){
        var instituicao = new InstituicaoModel(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(instituicao));
    }

 

    @GetMapping("/retornainstituicao")
    public List<InstituicaoGetDTO> getAgressores(){
        return repository.findAll().stream().map(InstituicaoGetDTO::new).toList();
    }

}
