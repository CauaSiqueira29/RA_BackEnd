package com.example.ReconstruindoAtitudes.Repository;

import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentoradoRepository extends JpaRepository<MentoradoModel, Long> {

    Optional<MentoradoModel> findByEmail(String email);

    MentoradoModel findByAnamnese(AnamneseModel anamneseModel);
}
