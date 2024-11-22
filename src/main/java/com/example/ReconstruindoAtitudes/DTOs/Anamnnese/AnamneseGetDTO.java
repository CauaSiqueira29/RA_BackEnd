package com.example.ReconstruindoAtitudes.DTOs.Anamnnese;

import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoGetDTO;
import com.example.ReconstruindoAtitudes.Model.AnamneseModel;
import com.example.ReconstruindoAtitudes.Model.MentoradoModel;

public record AnamneseGetDTO (MentoradoGetDTO mentorado,
                              String pergunta1,
                              String pergunta2,
                              String pergunta3,
                              String pergunta4,
                              String pergunta5,
                              String pergunta6,
                              String pergunta7,
                              String pergunta8,
                              String pergunta9,
                              String pergunta10,
                              String pergunta11,
                              String pergunta12,
                              String pergunta13,
                              String pergunta14,
                              String pergunta15,
                              String pergunta16,
                              String pergunta17,
                              String pergunta18,
                              String pergunta19){

    public AnamneseGetDTO(AnamneseModel anamnese){
        this(new MentoradoGetDTO(anamnese.getMentorado()), anamnese.getPergunta1(), anamnese.getPergunta2(), anamnese.getPergunta3(), anamnese.getPergunta4(),
                anamnese.getPergunta5(), anamnese.getPergunta6(), anamnese.getPergunta7(), anamnese.getPergunta8(),
                anamnese.getPergunta9(), anamnese.getPergunta10(), anamnese.getPergunta11(), anamnese.getPergunta12(),
                anamnese.getPergunta13(), anamnese.getPergunta14(), anamnese.getPergunta15(), anamnese.getPergunta16(),
                anamnese.getPergunta17(), anamnese.getPergunta18(), anamnese.getPergunta19());
    }
}
