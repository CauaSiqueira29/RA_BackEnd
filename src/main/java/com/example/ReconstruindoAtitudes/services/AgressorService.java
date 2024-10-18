package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Agressor.AgressorPutDTO;
import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgressorService {

    @Autowired
    private AgressorRepository repository;

    public ResponseEntity<AgressorModel> cadastrarAgressor(AgressorPostDTO data){
        AgressorModel agressor = new AgressorModel(data);
        repository.save(agressor);
        return ResponseEntity.status(HttpStatus.CREATED).body(agressor);
    }

    public ResponseEntity<List<AgressorGetDTO>> listarAgressores(){
        return ResponseEntity.ok(repository.findAll().stream().map(AgressorGetDTO::new).toList());
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

}
