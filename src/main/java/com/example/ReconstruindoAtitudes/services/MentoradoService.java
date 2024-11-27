package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationSenhaPutDto;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPutDTO;
import com.example.ReconstruindoAtitudes.Infra.Security.TokenService;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MentoradoService {

    private final MentoradoRepository mentoradoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final InstituicaoRepository instituicaoRepository;
    private final MentoriaRepository mentoriaRepository;

    // Cadastro
    public ResponseEntity<?> cadastrarMentorado(MentoradoPostDTO data) {
        var procuraMentorado = mentoradoRepository.findByEmail(data.email());
        var instituicao = instituicaoRepository.findById(data.instituicaoId()).orElseThrow(() ->
                new RuntimeException("Instituição com id: '" + data.instituicaoId() + "' não encontrada"));

        if (procuraMentorado.isEmpty()) {
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            MentoradoModel mentorado = new MentoradoModel(data, senhaEncriptada, instituicao);
            this.mentoradoRepository.save(mentorado);

            String token = this.tokenService.generateToken(mentorado);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(mentorado.getId(), mentorado.getEmail(), mentorado.getRole(), token));
        }

        return ResponseEntity.badRequest().body("Usuário já cadastrado! " + data.email());
    }

    // Login
    public ResponseEntity<AuthenticationTokenGetDto> loginMentorado(AuthenticationPostDTO data) {
        var mentorado = mentoradoRepository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (passwordEncoder.matches(data.senha(), mentorado.getPassword())) {
            String token = this.tokenService.generateToken(mentorado);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(mentorado.getId(), mentorado.getEmail(), mentorado.getRole(), token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    // Retorna todos
    public ResponseEntity<List<MentoradoGetDTO>> listarMentorados() {
        return ResponseEntity.ok(mentoradoRepository.findAll().stream().map(MentoradoGetDTO::new).toList());
    }

    // Retorna por um por ID
    public ResponseEntity<MentoradoGetDTO> retornaMentoradoPorId(Long id) {
        var procuraMentorado = mentoradoRepository.findById(id);

        if (procuraMentorado.isPresent()) {
            var mentorado = procuraMentorado.get();
            return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza
    public ResponseEntity<MentoradoGetDTO> atualizarMentorado(MentoradoPutDTO data, Long id) {
        var mentorado = mentoradoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Mentor com id: " + id + " não encontrado"));

        if (data.nome() != null) {
            mentorado.setNome(data.nome());
        }
        if (data.email() != null) {
            mentorado.setEmail(data.email());
        }
        if(data.instituicaoId() != null){
            var instituicao = instituicaoRepository.findById(data.instituicaoId()).orElseThrow(() ->
                    new RuntimeException("Instituição com id: '" + data.instituicaoId() + "' não encontrada"));

            mentorado.setInstituicao(instituicao);
        }
        if (data.senha() != null) {
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            mentorado.setSenha(senhaEncriptada);
        }

        mentoradoRepository.save(mentorado);
        return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));

    }

    // Esqueceu a senha
    public ResponseEntity<?> esqueceuSenha(AuthenticationSenhaPutDto data){
        var mentorado = mentoradoRepository.findByEmail(data.email()).orElseThrow(() ->
                new RuntimeException("Mentorado com email: '" + data.email() + "' não encontrado!"));

        var senhaEncriptada = passwordEncoder.encode(data.senha());
        mentorado.setSenha(senhaEncriptada);
        mentoradoRepository.save(mentorado);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Deleta
    public ResponseEntity<MentoradoGetDTO> deletaMentorado(Long id) {
        var procuraMentorado = mentoradoRepository.findById(id);

        if (procuraMentorado.isPresent()) {
            var mentorado = procuraMentorado.get();

            if(mentorado.getMentorias() != null){
                mentorado.getMentorias().forEach(m -> {
                    m.setMentorado(null);
                    mentoriaRepository.delete(m);
                });

            }
            if (mentorado.getInstituicao() != null){
                var instituicao = mentorado.getInstituicao();
                instituicao.getMentorados().remove(mentorado);
                instituicaoRepository.save(instituicao);

            }

            mentoradoRepository.deleteById(id);

            return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));
        }

        return ResponseEntity.notFound().build();

    }

    public Optional<MentoradoModel> findByEmail(String email) {
        return mentoradoRepository.findByEmail(email);
    }

}
