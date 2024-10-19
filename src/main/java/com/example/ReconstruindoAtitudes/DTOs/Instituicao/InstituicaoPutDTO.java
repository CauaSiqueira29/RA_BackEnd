package com.example.ReconstruindoAtitudes.DTOs.Instituicao;

import jakarta.validation.constraints.NotBlank;

public record InstituicaoPutDTO(@NotBlank String nome) {
}
