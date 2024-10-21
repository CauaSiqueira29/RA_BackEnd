package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgressorPostDTO(@NotBlank String nome,
                              @NotBlank @Email(message = "E-mail Inválido") String email,
                              @NotNull LocalDate dataNascimento,
                              @NotBlank String senha){
}
