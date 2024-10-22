package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPutDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoTokenGetDTO;
import com.example.ReconstruindoAtitudes.Infra.Security.TokenService;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    // Cadastro
    public ResponseEntity<InstituicaoTokenGetDTO> cadastrarInstituicao(InstituicaoPostDTO data){
        Optional<InstituicaoModel> procuraInstituicao = this.repository.findByEmail(data.email());

        if(procuraInstituicao.isEmpty()){
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            InstituicaoModel instituicao = new InstituicaoModel(data, senhaEncriptada);
            this.repository.save(instituicao);
            System.out.println("Instituição salva = " + data.email());

            String token = this.tokenService.generateToken(instituicao);
            return ResponseEntity.ok(new InstituicaoTokenGetDTO(instituicao.getEmail(), token));
        }

        System.out.println("Instituição já cadastrada = " + data.email());
        return ResponseEntity.badRequest().build();
    }

    // Login
    public ResponseEntity<InstituicaoTokenGetDTO> loginInstituicao(AuthenticationPostDTO data){
        InstituicaoModel instituicao = this.repository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Instituição não encontrado!"));

        if(passwordEncoder.matches(instituicao.getPassword(), data.senha())){
            String token = this.tokenService.generateToken(instituicao);
            return ResponseEntity.ok(new InstituicaoTokenGetDTO(instituicao.getEmail(), token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Retorna todas
    public ResponseEntity<List<InstituicaoGetDTO>> listarInstituicoes(){

        return ResponseEntity.ok(repository.findAll().stream().map(InstituicaoGetDTO::new).toList());
    }

    // Retorna por um por ID
    public ResponseEntity<InstituicaoGetDTO> retornaInstituicaoPorId(Long id){
        var procuraInstituicao = repository.findById(id);

        if(procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza
    public ResponseEntity<InstituicaoGetDTO> atualizarInstituicao(InstituicaoPutDTO data, Long id){
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();
            if (data.nome() != null){
                instituicao.setNome(data.nome());
            }

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

    // Deleta
    public ResponseEntity<InstituicaoGetDTO> deletaInstituicaoPorId(Long id){
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()){
            var instituicao = procuraInstituicao.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

}
