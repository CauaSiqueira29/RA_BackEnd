package com.example.ReconstruindoAtitudes.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

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
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/mentorado/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/EsqueceuSenha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentorado/listar").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.GET, "/mentorado/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.PUT, "/mentorado/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.DELETE, "/mentorado/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.POST, "/instituicao/cadastro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/instituicao/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/instituicao/listar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/instituicao/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.DELETE, "/instituicao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/mentor/cadastro").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.GET, "/mentor/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentor/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.PUT, "/mentor/{id}").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.DELETE, "/mentor/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.POST, "/mentoria/agendar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentoria/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mentoria/listar/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.PUT, "/mentoria/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.DELETE, "/mentoria/{id}").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.POST, "/anamnese/registrar").hasRole("MENTORADO")
                        .requestMatchers(HttpMethod.GET, "/anamnese/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/anamnese/listar/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.DELETE, "/anamnese/{id}").hasRole("INSTITUICAO")
                        .requestMatchers(HttpMethod.POST, "/horario/cadastrar").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.GET, "/horario/retorna/{mentorId}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/horario/atualiza/{id}").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.DELETE, "/horario/deletar/{id}").hasRole("MENTOR")
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

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
