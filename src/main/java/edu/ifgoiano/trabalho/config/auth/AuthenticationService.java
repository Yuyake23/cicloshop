package edu.ifgoiano.trabalho.config.auth;

import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.Usuario;
import edu.ifgoiano.trabalho.model.enums.Permissao;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import edu.ifgoiano.trabalho.model.repository.UsuarioRepository;
import edu.ifgoiano.trabalho.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UsuarioRepository usuarioRepository;
  private final PessoaRepository pessoaRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse registrar(RegisterRequest request) {
    Usuario usuario =
        Usuario.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .pessoa(
                pessoaRepository
                    .findByDocumento(request.getDocumento())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pessoa com documento=%s não encontrada.".formatted(request.getDocumento()))))
            .permissao(Permissao.CLIENTE)
            .build();

    try {
      usuarioRepository.save(usuario);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Usuário já registrado.");
    }

    String token = jwtService.gerarToken(usuario);
    return AuthenticationResponse.builder().token(token).build();
  }

  public AuthenticationResponse autenticar(AuthenticationRequest request) {
    Authentication autenticacao =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    Usuario usuario = (Usuario) autenticacao.getPrincipal();

    String token = jwtService.gerarToken(usuario);
    return AuthenticationResponse.builder().token(token).build();
  }
}
