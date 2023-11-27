package edu.ifgoiano.trabalho.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.PrecoProdutoDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PrecoProduto;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.repository.FornecedorRepository;
import edu.ifgoiano.trabalho.model.repository.PrecoProdutoRepository;
import edu.ifgoiano.trabalho.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class PrecoProdutoService {

	@Autowired
	private PrecoProdutoRepository precoProdutoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Transactional
	public PrecoProdutoDto salvar(PrecoProdutoDto dto) {
		Produto produto = buscarProduto(dto.produtoId);
		Fornecedor pessoa = buscarFornecedor(dto.fornecedorId);

		PrecoProduto precoProduto = precoProdutoRepository.save(dto.toEntity(produto, pessoa));
		return PrecoProdutoDto.ofPrecoProduto(precoProduto);
	}

	@Transactional
	public List<PrecoProdutoDto> salvarTodos(Iterable<PrecoProdutoDto> dtos) {
		List<PrecoProduto> precosProduto = StreamSupport.stream(dtos.spliterator(), true).map(dto -> {
			Produto produto = buscarProduto(dto.produtoId);
			Fornecedor pessoa = buscarFornecedor(dto.fornecedorId);
			return dto.toEntity(produto, pessoa);
		}).toList();

		precosProduto = precoProdutoRepository.saveAll(precosProduto);
		return PrecoProdutoDto.ofPrecosProdutos(precosProduto);
	}

	@Transactional
	public PrecoProdutoDto atualizarCompletamente(PrecoProdutoDto dto, Long id) {
		if (!precoProdutoRepository.existsById(id)) {
			throw excecaoPorPrecoProdutoNaoEncontrado(id);
		}
		Produto produto = buscarProduto(dto.produtoId);
		Fornecedor fornecedor = buscarFornecedor(dto.fornecedorId);

		PrecoProduto precoProduto = dto.toEntity(produto, fornecedor);
		precoProduto.setId(id);

		precoProduto = precoProdutoRepository.save(precoProduto);
		return PrecoProdutoDto.ofPrecoProduto(precoProduto);
	}

	@Transactional
	public PrecoProdutoDto atualizarParcialmente(PrecoProdutoDto dto, Long id) {
		PrecoProduto precoProduto = precoProdutoRepository.findById(id)
				.orElseThrow(() -> excecaoPorPrecoProdutoNaoEncontrado(id));

		atualizarParcialmente(precoProduto, dto);

		precoProduto = precoProdutoRepository.save(precoProduto);
		return PrecoProdutoDto.ofPrecoProduto(precoProduto);
	}

	public boolean existePorId(Long id) {
		return precoProdutoRepository.existsById(id);
	}

	public PrecoProdutoDto buscarPorId(Long id) {
		PrecoProduto precoProduto = precoProdutoRepository.findById(id)
				.orElseThrow(() -> excecaoPorPrecoProdutoNaoEncontrado(id));

		return PrecoProdutoDto.ofPrecoProduto(precoProduto);
	}

	public List<PrecoProdutoDto> buscarTodos() {
		List<PrecoProduto> precoProduto = precoProdutoRepository.findAll();

		return PrecoProdutoDto.ofPrecosProdutos(precoProduto);
	}

	public List<PrecoProdutoDto> buscarPorProduto(Long produtoId) {
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
		if (dto.produtoId != null)
			precoProduto.setProduto(buscarProduto(dto.produtoId));
		if (dto.fornecedorId != null)
			precoProduto.setFornecedor(buscarFornecedor(dto.fornecedorId));
		if (dto.preco != null)
			precoProduto.setPreco(dto.preco);
		if (dto.porcentagemLucro != null)
			precoProduto.setPorcentagemLucro(dto.porcentagemLucro);
	}

	private Produto buscarProduto(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> excecaoPorProdutoNaoEncontrado(produtoId));
	}

	private Fornecedor buscarFornecedor(Long fornecedorId) {
		return fornecedorRepository.findById(fornecedorId)
				.orElseThrow(() -> excecaoPorFornecedorNaoEncontrado(fornecedorId));
	}

	private RecursoNaoEncontradoException excecaoPorPrecoProdutoNaoEncontrado(Long id) {
		return new RecursoNaoEncontradoException("PrecoProduto com id=%d não encontrado.".formatted(id));
	}

	private RecursoNaoEncontradoException excecaoPorFornecedorNaoEncontrado(Long id) {
		return new RecursoNaoEncontradoException("Fornecedor com id=%d não encontrado.".formatted(id));
	}

	private RecursoNaoEncontradoException excecaoPorProdutoNaoEncontrado(Long id) {
		return new RecursoNaoEncontradoException("Produto com id=%d não encontrado.".formatted(id));
	}

}
