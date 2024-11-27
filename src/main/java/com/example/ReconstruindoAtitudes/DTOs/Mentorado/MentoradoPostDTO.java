package com.example.ReconstruindoAtitudes.DTOs.Mentorado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MentoradoPostDTO(@NotBlank String nome,
                               @NotBlank @Email(message = "E-mail Inválido") String email,
                               @NotNull Long instituicaoId,
                               @NotNull LocalDate dataNascimento,
                               @NotBlank String senha){
}
