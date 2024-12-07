package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamneseGetDTO;
import com.example.ReconstruindoAtitudes.DTOs.Anamnnese.AnamnesePostDTO;
import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import com.example.ReconstruindoAtitudes.Repository.AnamneseRepository;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamneseService {

    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private MentoradoRepository mentoradoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    // Registra anamnese
    public ResponseEntity<?> cadastrarAnamnese(AnamnesePostDTO data) {
        var procuraMentorado = mentoradoRepository.findById(data.mentoradoId());

        if (procuraMentorado.isEmpty()) {
            throw new RuntimeException("Id do mentorado não encontrado: " + data.mentoradoId());
        }

        var mentorado = procuraMentorado.get();
        var anamnese = new AnamneseModel(data, mentorado);

        mentorado.setAnamnese(anamnese);

        anamneseRepository.save(anamnese);
        mentoradoRepository.save(mentorado);

        return ResponseEntity.ok().body(new AnamneseGetDTO(anamnese));
    }

    // Retorna anamnese
    public ResponseEntity<List<AnamneseGetDTO>> listarAnamneses() {
        return ResponseEntity.ok(anamneseRepository.findAll().stream().map(AnamneseGetDTO::new).toList());

    }

    // Retorna anamnese por id
    public ResponseEntity<AnamneseGetDTO> retornaAnamnesePorId(Long id) {
        var procuraAnamnese = anamneseRepository.findById(id);

        if (procuraAnamnese.isPresent()) {
            var anamnese = procuraAnamnese.get();

            return ResponseEntity.ok().body(new AnamneseGetDTO(anamnese));
        }

        throw new RuntimeException("Anamnese com id: " + id + " não encontrada!");
    }

    // Retorna anamnese de mentorado agendado com mentor
    public ResponseEntity<?> retornaAnamneseAgendada(Long mentorId) {
        var mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor com id: " + mentorId + " não encontrado"));

        var anamneses = mentor.getMentorias().stream().map(m -> m.getMentorado().getAnamnese()).map(AnamneseGetDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(anamneses);
    }

    // Deleta anamnese
    public ResponseEntity<AnamneseGetDTO> deletaAnamnese(Long id) {
        var procuraAnamnese = anamneseRepository.findById(id).orElseThrow(() -> new RuntimeException("Anamnese com id: " + id + " não encontrada"));
        MentoradoModel mentorado = mentoradoRepository.findByAnamnese(procuraAnamnese);

        mentorado.setAnamnese(null);
        mentoradoRepository.save(mentorado);
        anamneseRepository.delete(procuraAnamnese);

        return ResponseEntity.ok().build();


    }

}