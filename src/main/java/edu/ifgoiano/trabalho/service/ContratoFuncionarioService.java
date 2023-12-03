package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorContratoFuncionarioNaoEncontrado;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorDadosBancariosNaoEncontrados;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;

import edu.ifgoiano.trabalho.dto.ContratoFuncionarioDto;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.DadosBancarios;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.ContratoFuncionarioRepository;
import edu.ifgoiano.trabalho.model.repository.DadosBancariosRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContratoFuncionarioService {

  @Autowired private ContratoFuncionarioRepository contratoFuncionarioRepository;
  @Autowired private DadosBancariosRepository dadosBancariosRepository;
  @Autowired private PessoaRepository pessoaRepository;

  @Transactional
  public ContratoFuncionarioDto salvar(ContratoFuncionarioDto dto) {
    ContratoFuncionario contratoFuncionario = contratoFuncionarioRepository.save(dto.toEntity());
    if (dto.dadosBancarios.id == null) {
      dadosBancariosRepository.save(dto.dadosBancarios.toEntity());
    } else {
      DadosBancarios dadosBancarios =
          dadosBancariosRepository
              .findById(dto.dadosBancarios.id)
              .orElseThrow(() -> excecaoPorDadosBancariosNaoEncontrados(dto.dadosBancarios.id));
      contratoFuncionario.setDadosBancarios(dadosBancarios);
    }

    log.info("Funcionário \"" + contratoFuncionario.getId() + "\" criado.");

    return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
  }

  @Transactional
  public Iterable<ContratoFuncionarioDto> salvarTodos(Iterable<ContratoFuncionarioDto> dtos) {
    List<ContratoFuncionario> contratosFuncionario =
        StreamSupport.stream(dtos.spliterator(), true)
            .map(ContratoFuncionarioDto::toEntity)
            .toList();

    contratosFuncionario = contratoFuncionarioRepository.saveAll(contratosFuncionario);

    log.info(
        "Funcionários \""
            + contratosFuncionario.stream().map(ContratoFuncionario::getId)
            + "\" criados.");

    return ContratoFuncionarioDto.ofContratosFuncionario(contratosFuncionario);
  }

  @Transactional
  public ContratoFuncionarioDto atualizarCompletamente(ContratoFuncionarioDto dto, Long id) {
    if (!contratoFuncionarioRepository.existsById(id)) {
      throw excecaoPorContratoFuncionarioNaoEncontrado(id);
    }

    ContratoFuncionario contratoFuncionario = dto.toEntity();
    contratoFuncionario.setId(id);

    contratoFuncionario = contratoFuncionarioRepository.save(contratoFuncionario);

    log.info("Contrato \"" + contratoFuncionario.getId() + "\" atualizado.");

    return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
  }

  @Transactional
  public ContratoFuncionarioDto atualizarParcialmente(ContratoFuncionarioDto dto, Long id) {
    ContratoFuncionario contratoFuncionario =
        contratoFuncionarioRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorContratoFuncionarioNaoEncontrado(id));

    atualizarParcialmente(contratoFuncionario, dto);

    contratoFuncionario = contratoFuncionarioRepository.save(contratoFuncionario);

    log.info("Contrato \"" + contratoFuncionario.getId() + "\" atualizado.");

    return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
  }

  public boolean existePorId(Long id) {
    return contratoFuncionarioRepository.existsById(id);
  }

  public ContratoFuncionarioDto buscarPorId(Long id) {
    ContratoFuncionario contratoFuncionario =
        contratoFuncionarioRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorContratoFuncionarioNaoEncontrado(id));

    return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
  }

  public Iterable<ContratoFuncionarioDto> buscarTodos() {
    List<ContratoFuncionario> contratosFuncionario = contratoFuncionarioRepository.findAll();

    return ContratoFuncionarioDto.ofContratosFuncionario(contratosFuncionario);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!contratoFuncionarioRepository.existsById(id)) {
      throw excecaoPorContratoFuncionarioNaoEncontrado(id);
    }

    contratoFuncionarioRepository.deleteById(id);

    log.info("Funcionário \"" + id + "\" deletado.");
  }

  private void atualizarParcialmente(
      ContratoFuncionario contratoFuncionario, ContratoFuncionarioDto dto) {
    if (dto.funcionarioId != null)
      contratoFuncionario.setFuncionario(buscarPessoa(dto.funcionarioId));
    if (dto.cargo != null) contratoFuncionario.setCargo(dto.cargo);
    if (dto.salario != null) contratoFuncionario.setSalario(dto.salario);
    if (dto.dadosBancarios != null)
      contratoFuncionario.setDadosBancarios(dto.dadosBancarios.toEntity());
    if (dto.dataEntrada != null) contratoFuncionario.setDataEntrada(dto.dataEntrada);
    if (dto.dataSaida != null) contratoFuncionario.setDataSaida(dto.dataSaida);
  }

  private Pessoa buscarPessoa(Long funcionarioId) {
    return pessoaRepository
        .findById(funcionarioId)
        .orElseThrow(() -> excecaoPorPessoaNaoEncontrada(funcionarioId));
  }
}
