package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorBicicletaNaoEncontrada;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPecaNaoEncontrada;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ifgoiano.trabalho.dto.BicicletaDto;
import edu.ifgoiano.trabalho.exception.PecasInsuficientesException;
import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.BicicletaRepository;
import edu.ifgoiano.trabalho.model.repository.PecaRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BicicletaService {

  @Autowired private BicicletaRepository bicicletaRepository;
  @Autowired private PessoaRepository pessoaRepository;
  @Autowired private PecaRepository pecaRepository;

  @Transactional
  public BicicletaDto atualizarCompletamente(BicicletaDto dto, Long id) {
    if (!pessoaRepository.existsById(id)) {
      throw excecaoPorPessoaNaoEncontrada(id);
    }

    Bicicleta bicicleta = dto.toEntity();
    bicicleta.setId(id);

    bicicleta = bicicletaRepository.save(bicicleta);

    log.info("Bicicleta \"" + bicicleta.getId() + "\" atualizada.");

    return BicicletaDto.ofBicicleta(bicicleta);
  }

  @Transactional
  public BicicletaDto atualizarParcialmente(BicicletaDto dto, Long id) {
    Bicicleta bicicleta =
        bicicletaRepository.findById(id).orElseThrow(() -> excecaoPorBicicletaNaoEncontrada(id));

    atualizarParcialmente(bicicleta, dto);

    bicicleta = bicicletaRepository.save(bicicleta);

    log.info("Bicicleta \"" + bicicleta.getId() + "\" atualizada.");

    return BicicletaDto.ofBicicleta(bicicleta);
  }

  public boolean existePorId(Long id) {
    return bicicletaRepository.existsById(id);
  }

  public BicicletaDto buscarPorId(Long id) {
    Bicicleta bicicleta =
        bicicletaRepository.findById(id).orElseThrow(() -> excecaoPorBicicletaNaoEncontrada(id));

    return BicicletaDto.ofBicicleta(bicicleta);
  }

  public Iterable<BicicletaDto> buscarTodos() {
    List<Bicicleta> bicicletas = bicicletaRepository.findAll();

    return BicicletaDto.ofBicicletas(bicicletas);
  }

  public Iterable<BicicletaDto> buscarPorDono(Long idDono) {
    if (!pessoaRepository.existsById(idDono)) {
      throw excecaoPorPessoaNaoEncontrada(idDono);
    }

    List<Bicicleta> bicicletas = bicicletaRepository.findByDonoId(idDono);
    return BicicletaDto.ofBicicletas(bicicletas);
  }
  
  @Transactional
  public BicicletaDto montarBicicleta(Iterable<Long> pecasId, Float lucro, String serial) {
    List<Peca> pecas = StreamSupport.stream(pecasId.spliterator(), true).map(id -> {
        Optional<Peca> peca = pecaRepository.findById(id);
        return peca.orElseThrow(() -> excecaoPorPecaNaoEncontrada(id));
      }
    ).toList();
    
    pecas.forEach(p -> {
      if (p.getQuantidade() <= 0)
        throw new PecasInsuficientesException(
            "Não há %s o suficiente. Id=%d".formatted(p.getNome(), p.getId()));
      
      p.setQuantidade(p.getQuantidade()-1);
    });
    
    BigDecimal precoCusto = pecas.stream()
        .map(Peca::getValor)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .setScale(2);
    
    pecaRepository.saveAll(pecas);
    
    Bicicleta bicicleta = new Bicicleta();
    bicicleta.setDono(new Pessoa(1L));
    bicicleta.setCodigoSerial(serial);
    bicicleta.setValor(precoCusto.multiply(BigDecimal.valueOf(1 + lucro)));
    
    bicicleta = bicicletaRepository.save(bicicleta);
    return BicicletaDto.ofBicicleta(bicicleta);
  }

  private void atualizarParcialmente(Bicicleta bicicleta, BicicletaDto dto) {
    if (dto.marca != null) bicicleta.setMarca(dto.marca);
    if (dto.valor != null) bicicleta.setValor(dto.valor);
    if (dto.donoId != null) {
      Pessoa dono = buscarDono(dto.donoId);
      bicicleta.setDono(dono);
    }
    if (dto.codigoSerial != null) bicicleta.setCodigoSerial(dto.codigoSerial);
    if (dto.modelo != null) bicicleta.setModelo(dto.modelo);
    if (dto.cor != null) bicicleta.setCor(dto.cor);
  }

  private Pessoa buscarDono(Long idDono) {
    return pessoaRepository
        .findById(idDono)
        .orElseThrow(() -> excecaoPorPessoaNaoEncontrada(idDono));
  }

}
