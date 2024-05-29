package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AgressorCadastroDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha) {

}
