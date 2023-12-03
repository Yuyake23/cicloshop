package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPecaNaoEncontrada;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;

import edu.ifgoiano.trabalho.dto.PecaDto;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.PecaRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PecaService {

  @Autowired private PecaRepository pecaRepository;
  @Autowired private PessoaRepository pessoaRepository;

  @Transactional
  public PecaDto atualizarCompletamente(PecaDto dto, Long id) {
    if (!pessoaRepository.existsById(id)) {
      throw excecaoPorPessoaNaoEncontrada(id);
    }

    Peca peca = dto.toEntity();
    peca.setId(id);

    peca = pecaRepository.save(peca);

    log.info("Peça \"" + peca.getId() + "\" atualizada.");

    return PecaDto.ofPeca(peca);
  }

  @Transactional
  public PecaDto atualizarParcialmente(PecaDto dto, Long id) {
    Peca peca = pecaRepository.findById(id).orElseThrow(() -> excecaoPorPecaNaoEncontrada(id));

    atualizarParcialmente(peca, dto);

    peca = pecaRepository.save(peca);

    log.info("Peça \"" + peca.getId() + "\" atualizada.");

    return PecaDto.ofPeca(peca);
  }

  public boolean existePorId(Long id) {
    return pecaRepository.existsById(id);
  }

  public PecaDto buscarPorId(Long id) {
    Peca peca = pecaRepository.findById(id).orElseThrow(() -> excecaoPorPecaNaoEncontrada(id));

    return PecaDto.ofPeca(peca);
  }

  public Iterable<PecaDto> buscarTodos() {
    List<Peca> pecas = pecaRepository.findAll();

    return PecaDto.ofPecas(pecas);
  }

  public Iterable<PecaDto> buscarPorDono(Long idDono) {
    if (!pessoaRepository.existsById(idDono)) {
      throw excecaoPorPessoaNaoEncontrada(idDono);
    }

    List<Peca> pecas = pecaRepository.findByDonoId(idDono);
    return PecaDto.ofPecas(pecas);
  }

  private void atualizarParcialmente(Peca peca, PecaDto dto) {
    if (dto.marca != null) peca.setMarca(dto.marca);
    if (dto.valor != null) peca.setValor(dto.valor);
    if (dto.donoId != null) {
      Pessoa dono = buscarDono(dto.donoId);
      peca.setDono(dono);
    }
    if (dto.nome != null) peca.setNome(dto.nome);
    if (dto.quantidade != null) peca.setQuantidade(dto.quantidade);
  }

  private Pessoa buscarDono(Long idDono) {
    return pessoaRepository
        .findById(idDono)
        .orElseThrow(() -> excecaoPorPessoaNaoEncontrada(idDono));
  }
}
