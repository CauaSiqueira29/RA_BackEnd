package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPutDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorTokenGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
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
    public ResponseEntity<AgressorTokenGetDTO> postAgressor(@RequestBody @Valid AgressorPostDTO data){
        return service.cadastrarAgressor(data);
    }

    @PostMapping("/login")
    public ResponseEntity<AgressorTokenGetDTO> loginAgressor(@RequestBody @Valid AuthenticationPostDTO data){
        return service.loginAgressor(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgressorGetDTO>> getAgressores(){
        return service.listarAgressores();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<AgressorGetDTO> getAgressorById(@PathVariable Long id){
        return service.retornaAgressorPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgressorGetDTO> atualizaAgressor(@RequestBody @Valid AgressorPutDTO data, @PathVariable Long id){
        return service.atualizarAgressor(data, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgressorGetDTO> deletarAgressor(@PathVariable Long id){
        return service.deletaAgressor(id);
    }

}
