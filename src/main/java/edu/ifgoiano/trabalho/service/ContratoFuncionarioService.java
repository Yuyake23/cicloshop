package edu.ifgoiano.trabalho.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.ContratoFuncionarioDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.ContratoFuncionarioRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class ContratoFuncionarioService {

	@Autowired
	private ContratoFuncionarioRepository contratoFuncionarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public ContratoFuncionarioDto salvar(ContratoFuncionarioDto dto) {
		Pessoa funcionario = buscarPessoa(dto.funcionarioId);
		
		ContratoFuncionario contratoFuncionario = contratoFuncionarioRepository.save(dto.toEntity(funcionario));
		return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
	}

	@Transactional
	public List<ContratoFuncionarioDto> salvarTodos(Iterable<ContratoFuncionarioDto> dtos) {
		List<ContratoFuncionario> contratosFuncionario = StreamSupport.stream(dtos.spliterator(), true).map(dto -> {
			Pessoa funcionario = buscarPessoa(dto.funcionarioId);
			return dto.toEntity(funcionario);
		}).toList();

		contratosFuncionario = contratoFuncionarioRepository.saveAll(contratosFuncionario);
		return ContratoFuncionarioDto.ofContratosFuncionario(contratosFuncionario);
	}

	@Transactional
	public ContratoFuncionarioDto atualizarCompletamente(ContratoFuncionarioDto dto, Long id) {
		if (!contratoFuncionarioRepository.existsById(id)) {
			throw excecaoPorContratoFuncionarioNaoEncontrado(id);
		}
		Pessoa funcionario = buscarPessoa(dto.funcionarioId);

		ContratoFuncionario contratoFuncionario = dto.toEntity(funcionario);
		contratoFuncionario.setId(id);

		contratoFuncionario = contratoFuncionarioRepository.save(contratoFuncionario);
		return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
	}

	@Transactional
	public ContratoFuncionarioDto atualizarParcialmente(ContratoFuncionarioDto dto, Long id) {
		ContratoFuncionario contratoFuncionario = contratoFuncionarioRepository.findById(id)
				.orElseThrow(() -> excecaoPorContratoFuncionarioNaoEncontrado(id));

		atualizarParcialmente(contratoFuncionario, dto);

		contratoFuncionario = contratoFuncionarioRepository.save(contratoFuncionario);
		return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
	}

	public boolean existePorId(Long id) {
		return contratoFuncionarioRepository.existsById(id);
	}

	public ContratoFuncionarioDto buscarPorId(Long id) {
		ContratoFuncionario contratoFuncionario = contratoFuncionarioRepository.findById(id)
				.orElseThrow(() -> excecaoPorContratoFuncionarioNaoEncontrado(id));

		return ContratoFuncionarioDto.ofContratoFuncionario(contratoFuncionario);
	}

	public List<ContratoFuncionarioDto> buscarTodos() {
		List<ContratoFuncionario> contratosFuncionario = contratoFuncionarioRepository.findAll();

		return ContratoFuncionarioDto.ofContratosFuncionario(contratosFuncionario);
	}
	
	@Transactional
	public void deletarPorId(Long id) {
		if (!contratoFuncionarioRepository.existsById(id)) {
			throw excecaoPorContratoFuncionarioNaoEncontrado(id);
		}

		contratoFuncionarioRepository.deleteById(id);
	}

	private void atualizarParcialmente(ContratoFuncionario contratoFuncionario, ContratoFuncionarioDto dto) {
		if (dto.funcionarioId != null)
			contratoFuncionario.setFuncionario(buscarPessoa(dto.funcionarioId));
		if (dto.cargo != null)
			contratoFuncionario.setCargo(dto.cargo);
		if (dto.salario != null)
			contratoFuncionario.setSalario(dto.salario);
		if (dto.dadosBancarios != null)
			contratoFuncionario.setDadosBancarios(dto.dadosBancarios.toEntity());
		if (dto.dataEntrada != null)
			contratoFuncionario.setDataEntrada(dto.dataEntrada);
		if (dto.dataSaida != null)
			contratoFuncionario.setDataSaida(dto.dataSaida);
	}

	private Pessoa buscarPessoa(Long funcionarioId) {
		return pessoaRepository.findById(funcionarioId).orElseThrow(() -> excecaoPorPessoaNaoEncontrada(funcionarioId));
	}

	private RecursoNaoEncontradoException excecaoPorContratoFuncionarioNaoEncontrado(Long id) {
		return new RecursoNaoEncontradoException("ContratoFuncionario com id=%d não encontrado.".formatted(id));
	}

	private RecursoNaoEncontradoException excecaoPorPessoaNaoEncontrada(Long id) {
		return new RecursoNaoEncontradoException("Pessoa com id=%d não encontrada.".formatted(id));
	}

}