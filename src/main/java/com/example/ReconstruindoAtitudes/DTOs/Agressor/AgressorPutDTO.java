package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AgressorPutDTO(@NotBlank String nome,
                             @NotBlank @Email(message = "E-mail Inválido") String email) {
}
