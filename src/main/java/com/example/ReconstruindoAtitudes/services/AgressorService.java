package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPutDTO;
import com.example.ReconstruindoAtitudes.DTOs.Authentication.AuthenticationPostDTO;
import com.example.ReconstruindoAtitudes.Infra.Security.TokenService;
import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgressorService {

    private final AgressorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity<AgressorModel> cadastrarAgressor(AgressorPostDTO data){
        Optional<AgressorModel> procuraAgressor = this.repository.findByEmail(data.email());

        if(procuraAgressor.isEmpty()){
            AgressorModel agressor = new AgressorModel(data, )


            return ResponseEntity.status(HttpStatus.CREATED).body(agressor);
        }

        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity<List<AgressorGetDTO>> listarAgressores(){
        return ResponseEntity.ok(repository.findAll().stream().map(AgressorGetDTO::new).toList());
    }

    public ResponseEntity<AgressorGetDTO> retornaAgressorPorId(Long id){
        var procuraAgressor = repository.findById(id);

        if(procuraAgressor.isPresent()){
            var agressor = procuraAgressor.get();
            return ResponseEntity.ok().body(new AgressorGetDTO(agressor));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AgressorGetDTO> atualizarAgressor(AgressorPutDTO data, Long id){
        var proocuraAgressor = repository.findById(id);

         if (proocuraAgressor.isPresent()){
             var agressor = proocuraAgressor.get();

             if(data.nome() != null){
                 agressor.setNome(data.nome());
             }
             if(data.email() != null){
                 agressor.setEmail(data.email());
             }

             repository.save(agressor);
             return ResponseEntity.ok().body(new AgressorGetDTO(agressor));
         }

         return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AgressorGetDTO> deletaAgressor(Long id){
        var procuraAgressor = repository.findById(id);

        if (procuraAgressor.isPresent()){
            var agressor = procuraAgressor.get();

            repository.deleteById(id);

            return ResponseEntity.ok().body(new AgressorGetDTO(agressor));
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<AgressorGetDTO> loginAgressor(@RequestBody @Valid AuthenticationPostDTO data){
        AgressorModel agressor = this.repository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        if(passwordEncoder.matches(agressor.getPassword(), data.senha())){
            String token = this.tokenService.generateToken(agressor);
            return ResponseEntity.ok(new AgressorGetDTO(agressor));
        }

        return ResponseEntity.badRequest().build();

    }

}
