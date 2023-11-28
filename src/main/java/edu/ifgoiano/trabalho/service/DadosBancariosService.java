package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorDadosBancariosNaoEncontrados;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.DadosBancariosDto;
import edu.ifgoiano.trabalho.model.entity.DadosBancarios;
import edu.ifgoiano.trabalho.model.repository.DadosBancariosRepository;
import jakarta.transaction.Transactional;

@Service
public class DadosBancariosService {

	@Autowired
	private DadosBancariosRepository dadosBancariosRepository;

	@Transactional
	public DadosBancariosDto salvar(DadosBancariosDto dto) {
		DadosBancarios dadosBancarios = dadosBancariosRepository.save(dto.toEntity());
		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	@Transactional
	public List<DadosBancariosDto> salvarTodos(Iterable<DadosBancariosDto> dtos) {
		List<DadosBancarios> dadosBancarios = StreamSupport.stream(dtos.spliterator(), true)
				.map(DadosBancariosDto::toEntity).toList();

		dadosBancarios = dadosBancariosRepository.saveAll(dadosBancarios);
		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	@Transactional
	public DadosBancariosDto atualizarCompletamente(DadosBancariosDto dto, Long id) {
		if (!dadosBancariosRepository.existsById(id)) {
			throw excecaoPorDadosBancariosNaoEncontrados(id);
		}

		DadosBancarios dadosBancarios = dto.toEntity();
		dadosBancarios.setId(id);

		dadosBancarios = dadosBancariosRepository.save(dadosBancarios);
		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	@Transactional
	public DadosBancariosDto atualizarParcialmente(DadosBancariosDto dto, Long id) {
		DadosBancarios dadosBancarios = dadosBancariosRepository.findById(id)
				.orElseThrow(() -> excecaoPorDadosBancariosNaoEncontrados(id));

		atualizarParcialmente(dadosBancarios, dto);

		dadosBancarios = dadosBancariosRepository.save(dadosBancarios);
		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	public boolean existePorId(Long id) {
		return dadosBancariosRepository.existsById(id);
	}

	public DadosBancariosDto buscarPorId(Long id) {
		DadosBancarios dadosBancarios = dadosBancariosRepository.findById(id)
				.orElseThrow(() -> excecaoPorDadosBancariosNaoEncontrados(id));

		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	public List<DadosBancariosDto> buscarTodos() {
		List<DadosBancarios> dadosBancarios = dadosBancariosRepository.findAll();

		return DadosBancariosDto.ofDadosBancarios(dadosBancarios);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!dadosBancariosRepository.existsById(id)) {
			throw excecaoPorDadosBancariosNaoEncontrados(id);
		}

		dadosBancariosRepository.deleteById(id);
	}

	private void atualizarParcialmente(DadosBancarios dadosBancarios, DadosBancariosDto dto) {
		if (dto.nomeBanco != null)
			dadosBancarios.setNomeBanco(dto.nomeBanco);
		if (dto.numeroAgencia != null)
			dadosBancarios.setNumeroAgencia(dto.numeroAgencia);
		if (dto.tipoConta != null)
			dadosBancarios.setTipoConta(dto.tipoConta);
		if (dto.numeroConta != null)
			dadosBancarios.setNumeroConta(dto.numeroConta);
		if (dto.operacao != null)
			dadosBancarios.setOperacao(dto.operacao);
	}

}
