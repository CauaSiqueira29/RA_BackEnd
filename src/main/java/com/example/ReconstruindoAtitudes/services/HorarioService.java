package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioGetDto;
import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioPostDto;
import com.example.ReconstruindoAtitudes.DTOs.Horario.HorarioPutDto;
import com.example.ReconstruindoAtitudes.Model.HorarioModel;
import com.example.ReconstruindoAtitudes.Repository.HorarioRepository;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final MentorRepository mentorRepository;

    // Cadastrar horário
    public ResponseEntity<?> cadastrarHorario(HorarioPostDto data){
        var mentor = mentorRepository.findById(data.mentorId()).orElseThrow(() ->
                new RuntimeException("Mentor com id: '" + data.mentorId() + "' não encontrado"));
        var existe = horarioRepository.existsByMentor_IdAndHorario(data.mentorId(), data.horario());
        if(existe){
            throw new RuntimeException("Mentor já possui esse horário cadastrado: " + data.horario());
        }

        var horario = new HorarioModel(data, mentor);

        horarioRepository.save(horario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new HorarioGetDto(horario));
    }

    // Retorna os horários disponíveis
    public ResponseEntity<?> retornaHorariosDisponiveis(Long mentorId){
        return ResponseEntity.status(HttpStatus.OK).body(
                horarioRepository.findByMentor_IdAndAgendadoFalse(mentorId).stream().map(HorarioGetDto::new).toList()
        );
    }

    //Atualizar Horario
    public ResponseEntity<?> atualizaHorario(HorarioPutDto data, Long id){
        var horario = horarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Horário com id: '" + id + "' não encontrado!"));

        if(data.horario() != null){
            horario.setHorario(data.horario());
        }

        horarioRepository.save(horario);

        return ResponseEntity.status(HttpStatus.OK).body(new HorarioGetDto(horario));

    }

    // Delete horario
    public ResponseEntity<?> deletaHorario(Long id){
        var horario = horarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Horário com id: '" + id + "' não encontrado!"));

        horarioRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new HorarioGetDto(horario));

    }

}
