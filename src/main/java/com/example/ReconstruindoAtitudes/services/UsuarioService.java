package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.Model.AgressorModel;
import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Model.MentorModel;
import com.example.ReconstruindoAtitudes.Model.UsuarioModel;
import com.example.ReconstruindoAtitudes.Repository.AgressorRepository;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private AgressorRepository agressorRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public Optional<UsuarioModel> buscarPorEmail(String email) {

        // Procura um Agressor;
        Optional<AgressorModel> agressor = agressorRepository.findByEmail(email);
        if (agressor.isPresent()) {
            return Optional.of(agressor.get());
        }

        // Procura um Mentor;
        Optional<MentorModel> mentor = mentorRepository.findByEmail(email);
        if (mentor.isPresent()) {
            return Optional.of(mentor.get());
        }

        // Procura uma Instituicao;
        Optional<InstituicaoModel> instituicao = instituicaoRepository.findByEmail(email);
        if (instituicao.isPresent()) {
            return Optional.of(instituicao.get());
        }

        // Retorna vazio se não encontrar nada.
        return Optional.empty();
    }
}
