package com.example.ReconstruindoAtitudes.DTOs.Agressor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AgressorPutDTO(String nome,
                             String email) {
}
