package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPutDTO;
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
    public ResponseEntity<?> cadastrarInstituicao(InstituicaoPostDTO data) {
        Optional<InstituicaoModel> procuraInstituicao = this.repository.findByEmail(data.email());

        if (procuraInstituicao.isEmpty()) {
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            InstituicaoModel instituicao = new InstituicaoModel(data, senhaEncriptada);
            this.repository.save(instituicao);

            String token = this.tokenService.generateToken(instituicao);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(instituicao.getId(), instituicao.getEmail(), instituicao.getRole(), token));
        }

        return ResponseEntity.badRequest().body("Instituição já cadastrada! " + data.email());
    }

    // Login
    public ResponseEntity<AuthenticationTokenGetDto> loginInstituicao(AuthenticationPostDTO data) {
        InstituicaoModel instituicao = this.repository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Instituição não encontrado!"));

        if (passwordEncoder.matches(data.senha(), instituicao.getPassword())) {
            String token = this.tokenService.generateToken(instituicao);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(instituicao.getId(), instituicao.getEmail(), instituicao.getRole(), token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    // Retorna todas
    public ResponseEntity<List<InstituicaoGetDTO>> listarInstituicoes() {

        return ResponseEntity.ok(repository.findAll().stream().map(InstituicaoGetDTO::new).toList());
    }

    // Retorna por um por ID
    public ResponseEntity<InstituicaoGetDTO> retornaInstituicaoPorId(Long id) {
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()) {
            var instituicao = procuraInstituicao.get();

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza
    public ResponseEntity<InstituicaoGetDTO> atualizarInstituicao(InstituicaoPutDTO data, Long id) {
        var instituicao = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Instituição com id: " + id + " não encontrado"));


        if (data.nome() != null) {
            instituicao.setNome(data.nome());
        }
        if(data.senha() != null){
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            instituicao.setSenha(senhaEncriptada);
        }

        repository.save(instituicao);
        return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));

    }

    // Deleta
    public ResponseEntity<InstituicaoGetDTO> deletaInstituicaoPorId(Long id) {
        var procuraInstituicao = repository.findById(id);

        if (procuraInstituicao.isPresent()) {
            var instituicao = procuraInstituicao.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new InstituicaoGetDTO(instituicao));
        }

        return ResponseEntity.notFound().build();

    }

    public Optional<InstituicaoModel> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
