package com.example.ReconstruindoAtitudes.Repository;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<InstituicaoModel, Long> {
    InstituicaoModel findByCnpj(String cnpj);
}
