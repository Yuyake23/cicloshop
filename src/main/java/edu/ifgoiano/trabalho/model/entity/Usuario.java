package edu.ifgoiano.trabalho.model.entity;

import edu.ifgoiano.trabalho.model.enums.Permissao;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** Representa um usuário no sistema. Para uso com Spring Security. */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Usuario implements UserDetails, Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  private String username;
  private String password;

  // TODO migration v1: criação classe usuario
  // TODO migration v2: criação coluna pessoa OneToOne
  @OneToOne
  @JoinColumn(name = "usuario_pessoa_id")
  public Pessoa pessoa;

  @Enumerated(EnumType.STRING)
  private Permissao permissao;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(permissao.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
