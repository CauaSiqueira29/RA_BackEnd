package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MentoriaPutDTO(@NotNull LocalDateTime hora) {
}
