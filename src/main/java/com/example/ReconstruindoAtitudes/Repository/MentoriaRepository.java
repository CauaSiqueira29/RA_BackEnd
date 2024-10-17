package com.example.ReconstruindoAtitudes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReconstruindoAtitudes.Model.MentoriaModel;

public interface MentoriaRepository extends JpaRepository<MentoriaModel, Long> {
}
