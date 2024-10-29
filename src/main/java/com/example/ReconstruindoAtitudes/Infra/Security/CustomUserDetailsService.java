package com.example.ReconstruindoAtitudes.Infra.Security;

import com.example.ReconstruindoAtitudes.Model.UsuarioModel;
import com.example.ReconstruindoAtitudes.Repository.MentoradoRepository;
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
    private MentoradoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuario = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

}
