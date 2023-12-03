package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorVendaNaoEncontrada;

import edu.ifgoiano.trabalho.dto.VendaDto;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Venda;
import edu.ifgoiano.trabalho.model.repository.VendaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VendaService {

  @Autowired private VendaRepository vendaRepository;

  @Transactional
  public VendaDto salvar(VendaDto dto) {
    Venda venda = vendaRepository.save(dto.toEntity());

    log.info("Venda \"" + venda.getId() + "\" registrada.");

    return VendaDto.ofVenda(venda);
  }

  @Transactional
  public Iterable<VendaDto> salvarTodos(Iterable<VendaDto> dtos) {
    List<Venda> vendas =
        StreamSupport.stream(dtos.spliterator(), true).map(VendaDto::toEntity).toList();

    vendas = vendaRepository.saveAll(vendas);

    log.info("Vendas \"" + vendas.stream().map(Venda::getId) + "\" registradas.");

    return VendaDto.ofVendas(vendas);
  }

  @Transactional
  public VendaDto atualizarCompletamente(VendaDto dto, Long id) {
    if (!vendaRepository.existsById(id)) {
      throw excecaoPorVendaNaoEncontrada(id);
    }

    Venda venda = dto.toEntity();
    venda.setId(id);

    venda = vendaRepository.save(venda);

    log.info("Venda \"" + venda.getId() + "\" atualizada.");

    return VendaDto.ofVenda(venda);
  }

  @Transactional
  public VendaDto atualizarParcialmente(VendaDto dto, Long id) {
    Venda venda = vendaRepository.findById(id).orElseThrow(() -> excecaoPorVendaNaoEncontrada(id));

    atualizarParcialmente(venda, dto);

    venda = vendaRepository.save(venda);

    log.info("Venda \"" + venda.getId() + "\" atualizada.");

    return VendaDto.ofVenda(venda);
  }

  public boolean existePorId(Long id) {
    return vendaRepository.existsById(id);
  }

  public VendaDto buscarPorId(Long id) {
    Venda venda = vendaRepository.findById(id).orElseThrow(() -> excecaoPorVendaNaoEncontrada(id));

    return VendaDto.ofVenda(venda);
  }

  public Iterable<VendaDto> buscarTodos() {
    List<Venda> contratosFuncionario = vendaRepository.findAll();

    return VendaDto.ofVendas(contratosFuncionario);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!vendaRepository.existsById(id)) {
      throw excecaoPorVendaNaoEncontrada(id);
    }

    vendaRepository.deleteById(id);

    log.info("Venda \"" + id + "\" deletada.");
  }

  private void atualizarParcialmente(Venda venda, VendaDto dto) {
    if (dto.produtosId != null)
      venda.setProdutos(dto.produtosId.stream().map(Produto::new).toList());
    if (dto.compradorId != null) venda.setComprador(new Pessoa(dto.compradorId));
    if (dto.contratoFuncionarioId != null)
      venda.setFuncionario(new ContratoFuncionario(dto.contratoFuncionarioId));
    if (dto.dataVenda != null) venda.setDataVenda(dto.dataVenda);
    if (dto.valor != null) venda.setValor(dto.valor);
    if (dto.observacoes != null) venda.setObservacoes(dto.observacoes);
  }
}
