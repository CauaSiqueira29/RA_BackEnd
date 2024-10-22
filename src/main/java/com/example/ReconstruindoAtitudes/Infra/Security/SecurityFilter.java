package com.example.ReconstruindoAtitudes.Infra.Security;

import com.example.ReconstruindoAtitudes.Model.Role.UserRole;
import com.example.ReconstruindoAtitudes.Model.UsuarioModel;
import com.example.ReconstruindoAtitudes.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            UsuarioModel usuario = service.buscarPorEmail(login).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

            UserRole userRole = usuario.getRole();
            var authorities = new SimpleGrantedAuthority("ROLE_" + userRole.getRole().toUpperCase());

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, List.of(authorities));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }


}
