package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AgressorPostDTO(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String senha,
                              @NotBlank String contato,
                              @NotBlank String dataDeNascimento,
                              @NotBlank String estadoCivil,
                              @NotBlank String genero,
                              @NotBlank String profissao,
                              @NotEmpty @Size(min = 19, max = 19) List<String> perguntas) {

}
