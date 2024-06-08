package com.example.ReconstruindoAtitudes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReconstruindoAtitudes.Model.Mentoria;

@Repository
public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
}
