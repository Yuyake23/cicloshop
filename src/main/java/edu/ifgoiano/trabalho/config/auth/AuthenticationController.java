package edu.ifgoiano.trabalho.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  /**
   * Endpoint para registrar novos usuários.
   *
   * @param request O pedido de registração contento os dados do usuário a ser registrado.
   * @return Uma resposta de autenticação contendo um token.
   */
  @PostMapping("/registrar")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse registrar(@RequestBody RegisterRequest request) {
    return authenticationService.registrar(request);
  }

  /**
   * Endpoint para autenticação de usuários registrados.
   *
   * @param request O pedido contendo as credenciais do usuário.
   * @return Uma resposta de autenticação contendo um token.
   */
  @PostMapping("/autenticar")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse autenticar(@RequestBody AuthenticationRequest request) {
    return authenticationService.autenticar(request);
  }
}
