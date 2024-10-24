package com.example.ReconstruindoAtitudes.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/agressor/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agressor/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/agressor/listar").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.GET, "/agressor/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.PUT, "/agressor/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.DELETE, "/agressor/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.POST, "/instituicao/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/instituicao/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/instituicao/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/instituicao/listar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/instituicao/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.DELETE, "/instituicao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/mentor/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentor/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentor/listar").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.GET, "/mentor/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.PUT, "/mentor/{id}").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.DELETE, "/mentor/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.POST, "/mentoria/agendar").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.GET, "/mentoria/listar").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.GET, "/mentoria/listar/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.PUT, "/mentoria/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.DELETE, "/mentoria/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.POST, "/anamnese/registrar").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.GET, "/anamnese/listar").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.GET, "/anamnese/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.DELETE, "/anamnese/{id}").hasRole("INSTITUICAO")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
