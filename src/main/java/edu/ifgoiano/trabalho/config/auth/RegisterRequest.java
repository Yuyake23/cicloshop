package edu.ifgoiano.trabalho.config.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Caracteriza um pedido de registração na base de dados. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  private String username;
  private String documento;
  private String password;
}
