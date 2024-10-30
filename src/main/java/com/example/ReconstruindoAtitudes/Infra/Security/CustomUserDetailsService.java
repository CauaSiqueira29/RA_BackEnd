package com.example.ReconstruindoAtitudes.Infra.Security;

import com.example.ReconstruindoAtitudes.Model.UsuarioModel;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
import com.example.ReconstruindoAtitudes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuario = this.service.buscarPorEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

}
