package com.example.ReconstruindoAtitudes.DTOs.Login;

import jakarta.validation.constraints.NotBlank;

public record LoginPostDTO(@NotBlank String cnpj,
                           @NotBlank String senha) {
}
