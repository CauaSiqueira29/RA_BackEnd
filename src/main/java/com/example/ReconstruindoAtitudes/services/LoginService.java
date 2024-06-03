package com.example.ReconstruindoAtitudes.services;

import com.example.ReconstruindoAtitudes.Model.InstituicaoModel;
import com.example.ReconstruindoAtitudes.Repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public boolean authenticate(String cnpj, String senha) {
        InstituicaoModel instituicao = instituicaoRepository.findByCnpj(cnpj);
        return instituicao != null && instituicao.getSenha().equals(senha) && instituicao.getCnpj().equals(cnpj);
    }
}
