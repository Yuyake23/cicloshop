package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PessoaService {

  @Autowired private PessoaRepository pessoaRepository;

  @SuppressWarnings("unchecked")
  @Transactional
  public <E extends PessoaDto> E salvar(E dto) {
    Pessoa pessoa = pessoaRepository.save(dto.toEntity());

    log.info("Pessoa " + pessoa.getTipoPessoa() + " \"" + pessoa.getId() + "\" criada.");

    return (E) PessoaDto.ofPessoa(pessoa);
  }

  @SuppressWarnings("unchecked")
  @Transactional
  public <E extends PessoaDto> Iterable<E> salvarTodos(Iterable<E> dtos) {
    List<Pessoa> pessoas =
        StreamSupport.stream(dtos.spliterator(), true).map(PessoaDto::toEntity).toList();

    pessoas = pessoaRepository.saveAll(pessoas);

    log.info("Pessoas  \"" + pessoas.stream().map(Pessoa::getId).toList() + "\" criadas.");

    return (Iterable<E>) PessoaDto.ofPessoas(pessoas);
  }

  public boolean existePorId(Long id) {
    return pessoaRepository.existsById(id);
  }

  public PessoaDto buscarPorId(Long id) {
    Pessoa pessoa =
        pessoaRepository.findById(id).orElseThrow(() -> excecaoPorPessoaNaoEncontrada(id));

    return PessoaDto.ofPessoa(pessoa);
  }

  public Iterable<? extends PessoaDto> buscarTodos() {
    List<Pessoa> pessoas = pessoaRepository.findAll();

    return PessoaDto.ofPessoas(pessoas);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!pessoaRepository.existsById(id)) {
      throw excecaoPorPessoaNaoEncontrada(id);
    }

    pessoaRepository.deleteById(id);

    log.info("Pessoa \"" + id + "\" deletada.");
  }
}
