package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaFisicaNaoEncontrada;

import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PessoaFisicaService {

  @Autowired private PessoaFisicaRepository pessoaFisicaRepository;

  @Transactional
  public PessoaFisicaDto atualizarCompletamente(PessoaFisicaDto dto, Long id) {
    if (!pessoaFisicaRepository.existsById(id)) {
      throw excecaoPorPessoaFisicaNaoEncontrada(id);
    }

    PessoaFisica pessoa = dto.toEntity();

    pessoa = pessoaFisicaRepository.save(pessoa);

    log.info("Pessoa física \"" + pessoa.getId() + "\" atualizada.");

    return PessoaFisicaDto.ofPessoaFisica(pessoa);
  }

  @Transactional
  public PessoaFisicaDto atualizarParcialmente(PessoaFisicaDto dto, Long id) {
    PessoaFisica pessoa =
        pessoaFisicaRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorPessoaFisicaNaoEncontrada(id));

    atualizarParcialmente(pessoa, dto);

    pessoa = pessoaFisicaRepository.save(pessoa);

    log.info("Pessoa física \"" + pessoa.getId() + "\" atualizada.");

    return PessoaFisicaDto.ofPessoaFisica(pessoa);
  }

  public boolean existePorId(Long id) {
    return pessoaFisicaRepository.existsById(id);
  }

  public PessoaFisicaDto buscarPorId(Long id) {
    PessoaFisica pessoaFisica =
        pessoaFisicaRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorPessoaFisicaNaoEncontrada(id));

    return PessoaFisicaDto.ofPessoaFisica(pessoaFisica);
  }

  public Iterable<PessoaFisicaDto> buscarTodos() {
    List<PessoaFisica> pessoaFisicas = pessoaFisicaRepository.findAll();

    return PessoaFisicaDto.ofPessoasFisicas(pessoaFisicas);
  }

  private void atualizarParcialmente(PessoaFisica pessoa, PessoaFisicaDto dto) {
    if (dto.nome != null) pessoa.setNome(dto.nome);
    if (dto.cpf != null) pessoa.setDocumento(dto.cpf);
    if (dto.telefone != null) pessoa.setTelefone(dto.telefone);
    if (dto.endereco != null) pessoa.setEndereco(dto.endereco);
    if (dto.dataNascimento != null) pessoa.setDataGenese(dto.dataNascimento);
    if (dto.observacoes != null) pessoa.setObservacoes(dto.observacoes);
  }
}
