package com.example.ReconstruindoAtitudes.DTOs.Authentication;

import com.example.ReconstruindoAtitudes.Model.Role.UserRole;

public record AuthenticationTokenGetDto(Long id,
                                        String email,
                                        UserRole userRole,
                                        String token) {
}
