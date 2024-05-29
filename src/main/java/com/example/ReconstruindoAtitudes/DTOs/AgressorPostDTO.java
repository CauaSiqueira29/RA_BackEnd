package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AgressorPostDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha) {

}
