package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationTokenGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPutDTO;
import com.example.ReconstruindoAtitudes.Infra.Security.TokenService;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
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

    private final MentoradoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    // Cadastro
    public ResponseEntity<?> cadastrarMentorado(MentoradoPostDTO data){
        Optional<MentoradoModel> procuraMentorado = this.repository.findByEmail(data.email());

        if(procuraMentorado.isEmpty()){
            var senhaEncriptada = passwordEncoder.encode(data.senha());
            MentoradoModel mentorado = new MentoradoModel(data, senhaEncriptada);
            this.repository.save(mentorado);

            String token = this.tokenService.generateToken(mentorado);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(mentorado.getEmail(), token));
        }

        return ResponseEntity.badRequest().body("Usuário já cadastrado! " + data.email());
    }

    // Login
    public ResponseEntity<AuthenticationTokenGetDto> loginMentorado(AuthenticationPostDTO data){
        MentoradoModel mentorado = this.repository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if(passwordEncoder.matches(data.senha(), mentorado.getPassword())){
            String token = this.tokenService.generateToken(mentorado);
            return ResponseEntity.ok(new AuthenticationTokenGetDto(mentorado.getEmail(), token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    // Retorna todos
    public ResponseEntity<List<MentoradoGetDTO>> listarMentorados(){
        return ResponseEntity.ok(repository.findAll().stream().map(MentoradoGetDTO::new).toList());
    }

    // Retorna por um por ID
    public ResponseEntity<MentoradoGetDTO> retornaMentoradoPorId(Long id){
        var procuraMentorado = repository.findById(id);

        if(procuraMentorado.isPresent()){
            var mentorado = procuraMentorado.get();
            return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));
        }

        return ResponseEntity.notFound().build();
    }

    // Atualiza
    public ResponseEntity<MentoradoGetDTO> atualizarMentorado(MentoradoPutDTO data, Long id){
        var procuraMentorado = repository.findById(id);

         if (procuraMentorado.isPresent()){
             var mentorado = procuraMentorado.get();

             if(data.nome() != null){
                 mentorado.setNome(data.nome());
             }
             if(data.email() != null){
                 mentorado.setEmail(data.email());
             }
             if (data.senha() != null) {
                 var senhaEncriptada = passwordEncoder.encode(data.senha());
                 mentorado.setSenha(senhaEncriptada);
             }

             repository.save(mentorado);
             return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));
         }

         return ResponseEntity.notFound().build();
    }

    // Deleta
    public ResponseEntity<MentoradoGetDTO> deletaMentorado(Long id){
        var procuraMentorado = repository.findById(id);

        if (procuraMentorado.isPresent()){
            var mentorado = procuraMentorado.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new MentoradoGetDTO(mentorado));
        }

        return ResponseEntity.notFound().build();

    }

    public Optional<MentoradoModel> findByEmail(String email){
        return repository.findByEmail(email);
    }

}
