package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

//DTO q envia os dados do agressor
public record AgressorPostDTO(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String contato,
                              @NotBlank String dataNascimento,
                              @NotBlank String estadocivil,
                              @NotBlank String genero,
                              @NotBlank String profissao,
                              @NotBlank String pergunta1,
                              @NotBlank String pergunta2,
                              @NotBlank String pergunta3,
                              @NotBlank String pergunta4,
                              @NotBlank String pergunta5,
                              @NotBlank String pergunta6,
                              @NotBlank String pergunta7,
                              @NotBlank String pergunta8,
                              @NotBlank String pergunta9,
                              @NotBlank String pergunta10,
                              @NotBlank String pergunta11,
                              @NotBlank String pergunta12,
                              @NotBlank String pergunta13,
                              @NotBlank String pergunta14,
                              @NotBlank String pergunta15,
                              @NotBlank String pergunta16,
                              @NotBlank String pergunta17,
                              @NotBlank String pergunta18,
                              @NotBlank String pergunta19){
}
