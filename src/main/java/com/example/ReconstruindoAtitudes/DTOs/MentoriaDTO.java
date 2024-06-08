package com.example.ReconstruindoAtitudes.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MentoriaDTO {

    @NotBlank(message = "Mentor is mandatory")
    private String mentor;

    @NotBlank(message = "Hour is mandatory")
    private String hour;

    @NotBlank(message = "Agressor name is mandatory")
    private String agressorNome;

    @NotBlank(message = "Agressor contact is mandatory")
    private String agressorContato;
    
}
