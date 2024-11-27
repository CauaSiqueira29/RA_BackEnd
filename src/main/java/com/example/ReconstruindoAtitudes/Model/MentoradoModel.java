package com.example.ReconstruindoAtitudes.Model;

import com.example.ReconstruindoAtitudes.DTOs.Mentorado.MentoradoPostDTO;
import com.example.ReconstruindoAtitudes.Model.Role.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="mentorados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentoradoModel extends UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    @OneToOne
    private AnamneseModel anamnese;

    @OneToMany(mappedBy = "mentorado")
    private List<MentoriaModel> mentorias;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoModel instituicao;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public MentoradoModel(MentoradoPostDTO data, String senha, InstituicaoModel instituicao){
        this.nome = data.nome();
        this.email = data.email();
        this.dataNascimento = data.dataNascimento();
        this.senha = senha;
        this.role = UserRole.MENTORADO;
        this.instituicao = instituicao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

}
