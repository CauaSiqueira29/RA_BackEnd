package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPostDTO;
import com.example.ReconstruindoAtitudes.DTOs.Instituicao.TransformaEmMentorPostDto;
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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="mentores")
public class MentorModel extends UsuarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String bio;
    private boolean foiMentorado;

    @OneToMany(mappedBy = "mentor")
    private List<MentoriaModel> mentorias;

    public MentorModel(MentorPostDTO data, String senha){
        this.name = data.nome();
        this.bio = data.bio();
        this.email = data.email();
        this.senha = senha;
        this.role = UserRole.MENTOR;
        this.foiMentorado = false;
    }

    public MentorModel(TransformaEmMentorPostDto data, MentoradoModel mentorado) {
        this.name = mentorado.getNome();
        this.bio = data.bio();
        this.email = mentorado.getEmail();
        this.senha = mentorado.getSenha();
        this.role = UserRole.MENTOR;
        this.foiMentorado = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

}
