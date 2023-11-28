package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorVendaNaoEncontrada;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.VendaDto;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Venda;
import edu.ifgoiano.trabalho.model.repository.VendaRepository;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Transactional
	public VendaDto salvar(VendaDto dto) {
		Venda venda = vendaRepository.save(dto.toEntity());
		return VendaDto.ofVenda(venda);
	}

	@Transactional
	public List<VendaDto> salvarTodos(Iterable<VendaDto> dtos) {
		List<Venda> contratosFuncionario = StreamSupport.stream(dtos.spliterator(), true).map(VendaDto::toEntity)
				.toList();

		contratosFuncionario = vendaRepository.saveAll(contratosFuncionario);
		return VendaDto.ofVendas(contratosFuncionario);
	}

	@Transactional
	public VendaDto atualizarCompletamente(VendaDto dto, Long id) {
		if (!vendaRepository.existsById(id)) {
			throw excecaoPorVendaNaoEncontrada(id);
		}

		Venda venda = dto.toEntity();
		venda.setId(id);

		venda = vendaRepository.save(venda);
		return VendaDto.ofVenda(venda);
	}

	@Transactional
	public VendaDto atualizarParcialmente(VendaDto dto, Long id) {
		Venda venda = vendaRepository.findById(id).orElseThrow(() -> excecaoPorVendaNaoEncontrada(id));

		atualizarParcialmente(venda, dto);

		venda = vendaRepository.save(venda);
		return VendaDto.ofVenda(venda);
	}

	public boolean existePorId(Long id) {
		return vendaRepository.existsById(id);
	}

	public VendaDto buscarPorId(Long id) {
		Venda venda = vendaRepository.findById(id).orElseThrow(() -> excecaoPorVendaNaoEncontrada(id));

		return VendaDto.ofVenda(venda);
	}

	public List<VendaDto> buscarTodos() {
		List<Venda> contratosFuncionario = vendaRepository.findAll();

		return VendaDto.ofVendas(contratosFuncionario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!vendaRepository.existsById(id)) {
			throw excecaoPorVendaNaoEncontrada(id);
		}

		vendaRepository.deleteById(id);
	}

	private void atualizarParcialmente(Venda venda, VendaDto dto) {
		if (dto.produtosId != null)
			venda.setProdutos(dto.produtosId.stream().map(Produto::new).toList());
		if (dto.compradorId != null)
			venda.setComprador(new Pessoa(dto.compradorId));
		if (dto.contratoFuncionarioId != null)
			venda.setFuncionario(new ContratoFuncionario(dto.contratoFuncionarioId));
		if (dto.dataVenda != null)
			venda.setDataVenda(dto.dataVenda);
		if (dto.valor != null)
			venda.setValor(dto.valor);
		if (dto.observacoes != null)
			venda.setObservacoes(dto.observacoes);

	}

}
