package com.example.ReconstruindoAtitudes.Repository;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<InstituicaoModel, Long> {
    InstituicaoModel findByCnpj(String cnpj);

    Optional<InstituicaoModel> findByEmail(String email);
}
