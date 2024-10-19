package com.example.ReconstruindoAtitudes.DTOs.Mentoria;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record MentoriaPutDTO(@NotNull LocalTime hora) {
}
