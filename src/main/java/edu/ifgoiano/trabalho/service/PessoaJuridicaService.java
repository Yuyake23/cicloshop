package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaJuridicaNaoEncontrada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.PessoaJuridicaDto;
import edu.ifgoiano.trabalho.model.entity.PessoaJuridica;
import edu.ifgoiano.trabalho.model.repository.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Transactional
	public PessoaJuridicaDto atualizarCompletamente(PessoaJuridicaDto dto, Long id) {
		if (!pessoaJuridicaRepository.existsById(id)) {
			throw excecaoPorPessoaJuridicaNaoEncontrada(id);
		}

		PessoaJuridica pessoa = dto.toEntity();

		pessoa = pessoaJuridicaRepository.save(pessoa);
		return PessoaJuridicaDto.ofPessoaJuridica(pessoa);
	}

	@Transactional
	public PessoaJuridicaDto atualizarParcialmente(PessoaJuridicaDto dto, Long id) {
		PessoaJuridica pessoa = pessoaJuridicaRepository.findById(id)
				.orElseThrow(() -> excecaoPorPessoaJuridicaNaoEncontrada(id));

		atualizarParcialmente(pessoa, dto);

		pessoa = pessoaJuridicaRepository.save(pessoa);
		return PessoaJuridicaDto.ofPessoaJuridica(pessoa);
	}

	public boolean existePorId(Long id) {
		return pessoaJuridicaRepository.existsById(id);
	}

	public PessoaJuridicaDto buscarPorId(Long id) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id)
				.orElseThrow(() -> excecaoPorPessoaJuridicaNaoEncontrada(id));

		return PessoaJuridicaDto.ofPessoaJuridica(pessoaJuridica);
	}

	public List<? extends PessoaJuridicaDto> buscarTodos() {
		List<PessoaJuridica> pessoaJuridicas = pessoaJuridicaRepository.findAll();

		return PessoaJuridicaDto.ofPessoasJuridicas(pessoaJuridicas);
	}

	private void atualizarParcialmente(PessoaJuridica pessoa, PessoaJuridicaDto dto) {
		if (dto.razaoSocial != null)
			pessoa.setNome(dto.razaoSocial);
		if (dto.cnpj != null)
			pessoa.setDocumento(dto.cnpj);
		if (dto.telefone != null)
			pessoa.setTelefone(dto.telefone);
		if (dto.endereco != null)
			pessoa.setEndereco(dto.endereco);
		if (dto.dataCriacao != null)
			pessoa.setDataGenese(dto.dataCriacao);
		if (dto.observacoes != null)
			pessoa.setObservacoes(dto.observacoes);
		if (dto.nomeFantasia != null)
			pessoa.setNomeFantasia(dto.nomeFantasia);
	}

}
