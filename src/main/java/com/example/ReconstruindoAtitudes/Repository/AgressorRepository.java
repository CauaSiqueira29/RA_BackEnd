package com.example.ReconstruindoAtitudes.Repository;


import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgressorRepository extends JpaRepository<AgressorModel, Long> {

    Optional<AgressorModel> findByEmail(String email);
}
