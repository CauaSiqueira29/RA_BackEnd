package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginPostDTO(@NotBlank String cnpj,
                           @NotBlank String senha) {
}
