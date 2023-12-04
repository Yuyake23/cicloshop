package edu.ifgoiano.trabalho;

import edu.ifgoiano.trabalho.config.ApplicationConfig;
import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import edu.ifgoiano.trabalho.dto.PessoaJuridicaDto;
import edu.ifgoiano.trabalho.model.entity.Usuario;
import edu.ifgoiano.trabalho.model.enums.Permissao;
import edu.ifgoiano.trabalho.model.repository.UsuarioRepository;
import edu.ifgoiano.trabalho.service.PessoaService;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "edu.ifgoiano.trabalho")
public class CicloshopApplication {

  @Bean
  public CommandLineRunner init(
      @Autowired PessoaService pessoaService,
      @Autowired UsuarioRepository usuarioRepository,
      @Autowired ApplicationConfig applicationConfig) {
    return args -> {
      PessoaDto oficina =
          pessoaService.salvar(
              new PessoaJuridicaDto(
                  null,
                  null,
                  null,
                  null,
                  "20.224.210/0001-70",
                  "Oficina",
                  "Oficina",
                  LocalDate.now()));
      PessoaDto funcionario =
          pessoaService.salvar(
              new PessoaFisicaDto(
                  null,
                  null,
                  "Pires do Rio (GO)",
                  "Filho do dono",
                  "Natanael",
                  "01286980046",
                  LocalDate.of(2000, 02, 12)));
      PessoaDto cliente =
          pessoaService.salvar(
              new PessoaFisicaDto(
                  null,
                  null,
                  "Orizona (GO)",
                  null,
                  "Bruno",
                  "86552881010",
                  LocalDate.of(2003, 01, 20)));

      PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();

      usuarioRepository.saveAll(
          Arrays.asList(
              new Usuario(
                  null,
                  "admin",
                  passwordEncoder.encode("1234"),
                  oficina.toEntity(),
                  Permissao.ADMINISTRADOR),
              new Usuario(
                  null,
                  "natan",
                  passwordEncoder.encode("1234"),
                  funcionario.toEntity(),
                  Permissao.FUNCIONARIO),
              new Usuario(
                  null,
                  "bruno",
                  passwordEncoder.encode("1234"),
                  cliente.toEntity(),
                  Permissao.CLIENTE)));
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(CicloshopApplication.class, args);
  }
}
