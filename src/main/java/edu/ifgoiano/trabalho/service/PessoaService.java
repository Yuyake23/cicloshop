package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@SuppressWarnings("unchecked")
	@Transactional
	public <E extends PessoaDto> E salvar(E dto) {
		Pessoa pessoa = pessoaRepository.save(dto.toEntity());
		return (E) PessoaDto.ofPessoa(pessoa);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <E extends PessoaDto> List<E> salvarTodos(Iterable<E> dtos) {
		List<Pessoa> pessoas = StreamSupport.stream(dtos.spliterator(), true).map(PessoaDto::toEntity).toList();

		pessoas = pessoaRepository.saveAll(pessoas);
		return (List<E>) PessoaDto.ofPessoas(pessoas);
	}

	public boolean existePorId(Long id) {
		return pessoaRepository.existsById(id);
	}

	public PessoaDto buscarPorId(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> excecaoPorPessoaNaoEncontrada(id));

		return PessoaDto.ofPessoa(pessoa);
	}

	public List<? extends PessoaDto> buscarTodos() {
		List<Pessoa> pessoas = pessoaRepository.findAll();

		return PessoaDto.ofPessoas(pessoas);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw excecaoPorPessoaNaoEncontrada(id);
		}

		pessoaRepository.deleteById(id);
	}

}
