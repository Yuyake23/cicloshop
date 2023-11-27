package edu.ifgoiano.trabalho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.PessoaFisica;
import edu.ifgoiano.trabalho.model.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Transactional
	public PessoaFisicaDto atualizarCompletamente(PessoaFisicaDto dto, Long id) {
		if (!pessoaFisicaRepository.existsById(id)) {
			throw excecaoPorPessoaFisicaNaoEncontrada(id);
		}

		PessoaFisica pessoa = dto.toEntity();

		pessoa = pessoaFisicaRepository.save(pessoa);
		return PessoaFisicaDto.ofPessoaFisica(pessoa);
	}

	@Transactional
	public PessoaFisicaDto atualizarParcialmente(PessoaFisicaDto dto, Long id) {
		PessoaFisica pessoa = pessoaFisicaRepository.findById(id)
				.orElseThrow(() -> excecaoPorPessoaFisicaNaoEncontrada(id));

		atualizarParcialmente(pessoa, dto);

		pessoa = pessoaFisicaRepository.save(pessoa);
		return PessoaFisicaDto.ofPessoaFisica(pessoa);
	}

	public boolean existePorId(Long id) {
		return pessoaFisicaRepository.existsById(id);
	}

	public PessoaFisicaDto buscarPorId(Long id) {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id)
				.orElseThrow(() -> excecaoPorPessoaFisicaNaoEncontrada(id));

		return PessoaFisicaDto.ofPessoaFisica(pessoaFisica);
	}

	public List<PessoaFisicaDto> buscarTodos() {
		List<PessoaFisica> pessoaFisicas = pessoaFisicaRepository.findAll();

		return PessoaFisicaDto.ofPessoasFisicas(pessoaFisicas);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!pessoaFisicaRepository.existsById(id)) {
			throw excecaoPorPessoaFisicaNaoEncontrada(id);
		}

		pessoaFisicaRepository.deleteById(id);
	}

	private void atualizarParcialmente(PessoaFisica pessoa, PessoaFisicaDto dto) {
		if (dto.nome != null)
			pessoa.setNome(dto.nome);
		if (dto.cpf != null)
			pessoa.setDocumento(dto.cpf);
		if (dto.telefone != null)
			pessoa.setTelefone(dto.telefone);
		if (dto.endereco != null)
			pessoa.setEndereco(dto.endereco);
		if (dto.dataNascimento != null)
			pessoa.setDataGenese(dto.dataNascimento);
		if (dto.observacoes != null)
			pessoa.setObservacoes(dto.observacoes);
	}

	private RecursoNaoEncontradoException excecaoPorPessoaFisicaNaoEncontrada(Long id) {
		return new RecursoNaoEncontradoException("PessoaFisica com id=%d não encontrado.".formatted(id));
	}

}
