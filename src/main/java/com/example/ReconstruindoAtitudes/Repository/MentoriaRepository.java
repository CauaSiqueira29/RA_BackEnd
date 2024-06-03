package com.example.ReconstruindoAtitudes.Repository;

import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MentoriaRepository extends JpaRepository<MentoriaModel, Long> {
    boolean existsByMentorAndHorario(MentorModel mentor, LocalDateTime horario);
}
