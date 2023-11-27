package edu.ifgoiano.trabalho.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import edu.ifgoiano.trabalho.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@SuppressWarnings("unchecked")
	@Transactional
	public <E extends ProdutoDto> E salvar(E dto) {
		Pessoa dono = buscarDono(dto.donoId);

		Produto produto = produtoRepository.save(dto.toEntity(dono));
		return (E) ProdutoDto.ofProduto(produto);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <E extends ProdutoDto> List<E> salvarTodos(Iterable<E> dtos) {
		List<Produto> produtos = StreamSupport.stream(dtos.spliterator(), true).map(dto -> {
			Pessoa dono = ProdutoService.this.buscarDono(dto.donoId);
			return dto.toEntity(dono);
		}).toList();

		produtos = produtoRepository.saveAll(produtos);
		return (List<E>) ProdutoDto.ofProdutos(produtos);
	}
	
	public boolean existePorId(Long id) {
		return produtoRepository.existsById(id);
	}

	public ProdutoDto buscarPorId(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(
				() -> excecaoPorPessoaNaoEncontrada(id));

		return ProdutoDto.ofProduto(produto);
	}

	public List<? extends ProdutoDto> buscarTodos() {
		List<Produto> produtos = produtoRepository.findAll();
		
		return ProdutoDto.ofProdutos(produtos);
	}

	public List<? extends ProdutoDto> buscarPorDono(Long idDono) {
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
	}
	
	private Pessoa buscarDono(Long idDono) {
		return pessoaRepository.findById(idDono).orElseThrow(() -> excecaoPorPessoaNaoEncontrada(idDono));
	}

	private RecursoNaoEncontradoException excecaoPorPessoaNaoEncontrada(Long id) {
		return new RecursoNaoEncontradoException("Pessoa com id=%d não encontrado.".formatted(id));
	}

	private RecursoNaoEncontradoException excecaoPorProdutoNaoEncontrado(Long id) {
		return new RecursoNaoEncontradoException("Produto com id=%d não encontrado.".formatted(id));
	}

}
