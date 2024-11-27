package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Instituicao.InstituicaoPostDTO;
import com.example.ReconstruindoAtitudes.Model.Role.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="instituicoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoModel extends UsuarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cnpj;

    @OneToMany(mappedBy = "instituicao")
    private List<MentoradoModel> mentorados;

    @ManyToMany(mappedBy = "instituicoes")
    private List<MentorModel> mentores;

    public InstituicaoModel(InstituicaoPostDTO data, String senha){
        this.nome = data.nomeIes();
        this.cnpj = data.cnpj();
        this.email = data.email();
        this.senha = senha;
        this.role = UserRole.INSTITUICAO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

}