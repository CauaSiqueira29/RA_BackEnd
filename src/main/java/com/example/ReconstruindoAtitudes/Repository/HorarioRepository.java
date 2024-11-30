package com.example.ReconstruindoAtitudes.Repository;

import com.example.ReconstruindoAtitudes.Model.HorarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioModel, Long> {

    boolean existsByMentor_IdAndHorario(Long mentorId, LocalDateTime horario);

    List<HorarioModel> findByMentor_IdAndAgendadoFalse(Long mentorId);
}
