package edu.ifgoiano.trabalho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.PecaDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.PecaRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PecaService {

	@Autowired
	private PecaRepository pecaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public PecaDto atualizarCompletamente(PecaDto dto, Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw excecaoPorPessoaNaoEncontrada(id);
		}
		Pessoa dono = buscarDono(dto.donoId);
		
		Peca peca = dto.toEntity(dono);
		peca.setId(id);
		
		peca = pecaRepository.save(peca);
		return PecaDto.ofPeca(peca);
	}
	
	@Transactional
	public PecaDto atualizarParcialmente(PecaDto dto, Long id) {
		Peca peca = pecaRepository.findById(id).orElseThrow(
				() -> excecaoPorPessoaNaoEncontrada(id)); 
		
		atualizarParcialmente(peca, dto);
		
		peca = pecaRepository.save(peca);
		return PecaDto.ofPeca(peca);
	}
	
	public boolean existePorId(Long id) {
		return pecaRepository.existsById(id);
	}

	public PecaDto buscarPorId(Long id) {
		Peca peca = pecaRepository.findById(id).orElseThrow(
				() -> excecaoPorPessoaNaoEncontrada(id));

		return PecaDto.ofPeca(peca);
	}

	public List<PecaDto> buscarTodos() {
		List<Peca> pecas = pecaRepository.findAll();
		
		return PecaDto.ofPecas(pecas);
	}

	public List<PecaDto> buscarPorDono(Long idDono) {
		if (!pessoaRepository.existsById(idDono)) {
			throw excecaoPorPessoaNaoEncontrada(idDono);
		}

		List<Peca> pecas = pecaRepository.findByDonoId(idDono);
		return PecaDto.ofPecas(pecas);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!pecaRepository.existsById(id)) {
			throw excecaoPorProdutoNaoEncontrado(id);
		}

		pecaRepository.deleteById(id);
	}
	
	private void atualizarParcialmente(Peca peca, PecaDto dto) {
		if(dto.marca != null)
			peca.setMarca(dto.marca);
		if(dto.valor != null)
			peca.setValor(dto.valor);
		if(dto.donoId != null) {
			Pessoa dono = buscarDono(dto.donoId);
			peca.setDono(dono);
		}
		if(dto.nome != null)
			peca.setNome(dto.nome);
		if(dto.quantidade != null)
			peca.setQuantidade(dto.quantidade);
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
