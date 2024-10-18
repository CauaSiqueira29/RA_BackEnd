package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AgressorPostDTO(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String contato,
                              @NotBlank LocalDate dataNascimento,
                              @NotBlank String estadocivil,
                              @NotBlank String genero,
                              @NotBlank String profissao){
}
