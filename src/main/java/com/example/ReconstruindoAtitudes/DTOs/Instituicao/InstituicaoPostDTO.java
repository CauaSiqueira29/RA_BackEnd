package com.example.ReconstruindoAtitudes.DTOs.Instituicao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InstituicaoPostDTO(@NotBlank String nomeIes,
                                 @NotBlank String senha,
                                 @NotBlank @Email(message = "E-mail Inválido") String email,
                                 @NotBlank String cnpj) {
}
