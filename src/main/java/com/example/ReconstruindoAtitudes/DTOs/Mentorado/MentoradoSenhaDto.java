package com.example.ReconstruindoAtitudes.DTOs.Mentorado;

import jakarta.validation.constraints.Email;

public record MentoradoSenhaDto(@Email String email,
                                String senha) {
}
