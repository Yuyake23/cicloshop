package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorBicicletaNaoEncontrada;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorPessoaNaoEncontrada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.BicicletaDto;
import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.repository.BicicletaRepository;
import edu.ifgoiano.trabalho.model.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class BicicletaService {

	@Autowired
	private BicicletaRepository bicicletaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public BicicletaDto atualizarCompletamente(BicicletaDto dto, Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw excecaoPorPessoaNaoEncontrada(id);
		}

		Bicicleta bicicleta = dto.toEntity();
		bicicleta.setId(id);

		bicicleta = bicicletaRepository.save(bicicleta);
		return BicicletaDto.ofBicicleta(bicicleta);
	}

	@Transactional
	public BicicletaDto atualizarParcialmente(BicicletaDto dto, Long id) {
		Bicicleta bicicleta = bicicletaRepository.findById(id).orElseThrow(() -> excecaoPorBicicletaNaoEncontrada(id));

		atualizarParcialmente(bicicleta, dto);

		bicicleta = bicicletaRepository.save(bicicleta);
		return BicicletaDto.ofBicicleta(bicicleta);
	}

	public boolean existePorId(Long id) {
		return bicicletaRepository.existsById(id);
	}

	public BicicletaDto buscarPorId(Long id) {
		Bicicleta bicicleta = bicicletaRepository.findById(id).orElseThrow(() -> excecaoPorBicicletaNaoEncontrada(id));

		return BicicletaDto.ofBicicleta(bicicleta);
	}

	public List<BicicletaDto> buscarTodos() {
		List<Bicicleta> bicicletas = bicicletaRepository.findAll();

		return BicicletaDto.ofBicicletas(bicicletas);
	}

	public List<BicicletaDto> buscarPorDono(Long idDono) {
		if (!pessoaRepository.existsById(idDono)) {
			throw excecaoPorPessoaNaoEncontrada(idDono);
		}

		List<Bicicleta> bicicletas = bicicletaRepository.findByDonoId(idDono);
		return BicicletaDto.ofBicicletas(bicicletas);
	}

	private void atualizarParcialmente(Bicicleta bicicleta, BicicletaDto dto) {
		if (dto.marca != null)
			bicicleta.setMarca(dto.marca);
		if (dto.valor != null)
			bicicleta.setValor(dto.valor);
		if (dto.donoId != null) {
			Pessoa dono = buscarDono(dto.donoId);
			bicicleta.setDono(dono);
		}
		if (dto.codigoSerial != null)
			bicicleta.setCodigoSerial(dto.codigoSerial);
		if (dto.modelo != null)
			bicicleta.setModelo(dto.modelo);
		if (dto.cor != null)
			bicicleta.setCor(dto.cor);
	}

	private Pessoa buscarDono(Long idDono) {
		return pessoaRepository.findById(idDono).orElseThrow(() -> excecaoPorPessoaNaoEncontrada(idDono));
	}

}
