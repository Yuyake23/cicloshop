package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorProdutoNaoEncontrado;

import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import edu.ifgoiano.trabalho.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoService {

  @Autowired private ProdutoRepository produtoRepository;
  @Autowired private PessoaRepository pessoaRepository;

  @SuppressWarnings("unchecked")
  @Transactional
  public <E extends ProdutoDto> E salvar(E dto) {
    Produto produto = produtoRepository.save(dto.toEntity());

    log.info(
        "Produto de tipo " + produto.getClass() + " com id \"" + produto.getId() + "\" criado.");

    return (E) ProdutoDto.ofProduto(produto);
  }

  @SuppressWarnings("unchecked")
  @Transactional
  public <E extends ProdutoDto> Iterable<E> salvarTodos(Iterable<E> dtos) {
    List<Produto> produtos =
        StreamSupport.stream(dtos.spliterator(), true).map(ProdutoDto::toEntity).toList();

    produtos = produtoRepository.saveAll(produtos);

    log.info("Produtos  \"" + produtos.stream().map(Produto::getId).toList() + "\" criados.");

    return (Iterable<E>) ProdutoDto.ofProdutos(produtos);
  }

  public boolean existePorId(Long id) {
    return produtoRepository.existsById(id);
  }

  public ProdutoDto buscarPorId(Long id) {
    Produto produto =
        produtoRepository.findById(id).orElseThrow(() -> excecaoPorProdutoNaoEncontrado(id));

    return ProdutoDto.ofProduto(produto);
  }

  public Iterable<? extends ProdutoDto> buscarTodos() {
    List<Produto> produtos = produtoRepository.findAll();

    return ProdutoDto.ofProdutos(produtos);
  }

  public Iterable<? extends ProdutoDto> buscarPorDono(Long idDono) {
    if (!pessoaRepository.existsById(idDono)) {
      throw excecaoPorPessoaNaoEncontrada(idDono);
    }

    List<Produto> produtos = produtoRepository.findByDonoId(idDono);
    return ProdutoDto.ofProdutos(produtos);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!produtoRepository.existsById(id)) {
      throw excecaoPorProdutoNaoEncontrado(id);
    }

    produtoRepository.deleteById(id);

    log.info("Produto \"" + id + "\" deletado.");
  }
}
