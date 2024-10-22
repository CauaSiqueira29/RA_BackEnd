package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPutDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoTokenGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorTokenGetDTO;
import com.example.ReconstruindoAtitudes.Infra.Security.TokenService;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    // Cadastro
    public ResponseEntity<MentorTokenGetDTO> cadastrarMentor(MentorPostDTO data){
        Optional<MentorModel> procuraMentor = this.repository.findByEmail(data.email());

        if(procuraMentor.isEmpty()){
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            MentorModel mentor = new MentorModel(data, senhaEncriptada);
            this.repository.save(mentor);

            String token = this.tokenService.generateToken(mentor);
            return ResponseEntity.ok(new MentorTokenGetDTO(mentor.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    // Login
    public ResponseEntity<MentorTokenGetDTO> loginMentor(AuthenticationPostDTO data){
        MentorModel mentor = this.repository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Mentor não encontrado!"));

        if(passwordEncoder.matches(mentor.getPassword(), data.senha())){
            String token = this.tokenService.generateToken(mentor);
            return ResponseEntity.ok(new MentorTokenGetDTO(mentor.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    // Retorna todas
    public ResponseEntity<List<MentorGetDTO>> listarMentor(){
        return ResponseEntity.ok(repository.findAll().stream().map(MentorGetDTO::new).toList());
    }

    // Retorna por um por ID
    public ResponseEntity<MentorGetDTO> retornaMentorPorId(Long id){
        var procuraMentor = repository.findById(id);

        if(procuraMentor.isPresent()){
            var mentor = procuraMentor.get();

            return ResponseEntity.ok().body(new MentorGetDTO(mentor));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza
    public ResponseEntity<MentorGetDTO> atualizarInstituicao(MentorPutDTO data, Long id){
        var procuraMentor = repository.findById(id);

        if (procuraMentor.isPresent()){
            var mentor = procuraMentor.get();
            if (data.nome() != null){
                mentor.setNome(data.nome());
            }

            return ResponseEntity.ok().body(new MentorGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

}
