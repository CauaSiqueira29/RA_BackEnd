package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPutDTO;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import com.example.ReconstruindoAtitudes.services.InstituicaoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    private InstituicaoService service;

    @PostMapping("/cadastro")
    public ResponseEntity<InstituicaoModel> postInstituicao(@RequestBody @Valid InstituicaoPostDTO data){
        return service.cadastrarInstituicao(data);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<InstituicaoGetDTO>> getInstituicao(){
        return service.listarInstituicoes();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<InstituicaoGetDTO> getInstituicaoById(@PathVariable Long id){
        return service.retornaInstituicaoPorId(id);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<InstituicaoGetDTO> atualizaInstituicao(@RequestBody @Valid InstituicaoPutDTO data, Long id){
        return service.atualizarInstituicao(data, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InstituicaoGetDTO> deleteInstituicao(@PathVariable Long id){
        return service.deletaInstituicaoPorId(id);
    }

}
