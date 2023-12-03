package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorFornecedorNaoEncontrado;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPrecoProdutoNaoEncontrado;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorProdutoNaoEncontrado;

import edu.ifgoiano.trabalho.dto.PrecoProdutoDto;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.repository.FornecedorRepository;
import edu.ifgoiano.trabalho.model.repository.PrecoProdutoRepository;
import edu.ifgoiano.trabalho.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrecoProdutoService {

  @Autowired private PrecoProdutoRepository precoProdutoRepository;
  @Autowired private ProdutoRepository produtoRepository;
  @Autowired private FornecedorRepository fornecedorRepository;

  @Transactional
  public PrecoProdutoDto salvar(PrecoProdutoDto dto) {
    PrecoProduto precoProduto = precoProdutoRepository.save(dto.toEntity());

    log.info(
        "Preço \""
            + precoProduto.getId()
            + "\" do produto \""
            + precoProduto.getProduto().getId()
            + "\" atualizada.");

    return PrecoProdutoDto.ofPrecoProduto(precoProduto);
  }

  @Transactional
  public Iterable<PrecoProdutoDto> salvarTodos(Iterable<PrecoProdutoDto> dtos) {
    List<PrecoProduto> precosProduto =
        StreamSupport.stream(dtos.spliterator(), true).map(PrecoProdutoDto::toEntity).toList();

    precosProduto = precoProdutoRepository.saveAll(precosProduto);

    log.info("Preços \"" + precosProduto.stream().map(PrecoProduto::getId) + "\" salvos.");

    return PrecoProdutoDto.ofPrecosProdutos(precosProduto);
  }

  @Transactional
  public PrecoProdutoDto atualizarCompletamente(PrecoProdutoDto dto, Long id) {
    if (!precoProdutoRepository.existsById(id)) {
      throw excecaoPorPrecoProdutoNaoEncontrado(id);
    }

    PrecoProduto precoProduto = dto.toEntity();
    precoProduto.setId(id);

    precoProduto = precoProdutoRepository.save(precoProduto);
    return PrecoProdutoDto.ofPrecoProduto(precoProduto);
  }

  @Transactional
  public PrecoProdutoDto atualizarParcialmente(PrecoProdutoDto dto, Long id) {
    PrecoProduto precoProduto =
        precoProdutoRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorPrecoProdutoNaoEncontrado(id));

    atualizarParcialmente(precoProduto, dto);

    precoProduto = precoProdutoRepository.save(precoProduto);
    return PrecoProdutoDto.ofPrecoProduto(precoProduto);
  }

  public boolean existePorId(Long id) {
    return precoProdutoRepository.existsById(id);
  }

  public PrecoProdutoDto buscarPorId(Long id) {
    PrecoProduto precoProduto =
        precoProdutoRepository
            .findById(id)
            .orElseThrow(() -> excecaoPorPrecoProdutoNaoEncontrado(id));

    return PrecoProdutoDto.ofPrecoProduto(precoProduto);
  }

  public Iterable<PrecoProdutoDto> buscarTodos() {
    List<PrecoProduto> precoProduto = precoProdutoRepository.findAll();

    return PrecoProdutoDto.ofPrecosProdutos(precoProduto);
  }

  public Iterable<PrecoProdutoDto> buscarPorProduto(Long produtoId) {
    if (!produtoRepository.existsById(produtoId)) {
      throw excecaoPorProdutoNaoEncontrado(produtoId);
    }

    List<PrecoProduto> precosProdutos = precoProdutoRepository.findByProdutoId(produtoId);
    return PrecoProdutoDto.ofPrecosProdutos(precosProdutos);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!precoProdutoRepository.existsById(id)) {
      throw excecaoPorPrecoProdutoNaoEncontrado(id);
    }

    precoProdutoRepository.deleteById(id);
  }

  private void atualizarParcialmente(PrecoProduto precoProduto, PrecoProdutoDto dto) {
    if (dto.produtoId != null) precoProduto.setProduto(buscarProduto(dto.produtoId));
    if (dto.fornecedorId != null) precoProduto.setFornecedor(buscarFornecedor(dto.fornecedorId));
    if (dto.preco != null) precoProduto.setPreco(dto.preco);
    if (dto.porcentagemLucro != null) precoProduto.setPorcentagemLucro(dto.porcentagemLucro);
  }

  private Produto buscarProduto(Long produtoId) {
    return produtoRepository
        .findById(produtoId)
        .orElseThrow(() -> excecaoPorProdutoNaoEncontrado(produtoId));
  }

  private Fornecedor buscarFornecedor(Long fornecedorId) {
    return fornecedorRepository
        .findById(fornecedorId)
        .orElseThrow(() -> excecaoPorFornecedorNaoEncontrado(fornecedorId));
  }
}
