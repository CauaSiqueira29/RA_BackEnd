package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Mentor.MentorPostDTO;
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

    @OneToMany(mappedBy = "mentor")
    private List<MentoriaModel> mentorias;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public MentorModel(MentorPostDTO data, String senha){
        this.name = data.nome();
        this.bio = data.bio();
        this.email = data.email();
        this.senha = senha;
        this.role = UserRole.MENTOR;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

}
