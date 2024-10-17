package com.example.ReconstruindoAtitudes.DTOs.Instituicao;

import jakarta.validation.constraints.NotBlank;

public record InstituicaoPostDTO(@NotBlank String nome, @NotBlank String senha, @NotBlank String cnpj) {
}
