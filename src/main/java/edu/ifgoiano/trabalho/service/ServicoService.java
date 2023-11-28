package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorServicoNaoEncontrado;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.ServicoDto;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Produto;
import edu.ifgoiano.trabalho.model.entity.Servico;
import edu.ifgoiano.trabalho.model.repository.ServicoRepository;
import jakarta.transaction.Transactional;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Transactional
	public ServicoDto salvar(ServicoDto dto) {
		Servico servico = servicoRepository.save(dto.toEntity());
		return ServicoDto.ofServico(servico);
	}

	@Transactional
	public List<ServicoDto> salvarTodos(Iterable<ServicoDto> dtos) {
		List<Servico> servicos = StreamSupport.stream(dtos.spliterator(), true).map(ServicoDto::toEntity).toList();

		servicos = servicoRepository.saveAll(servicos);
		return ServicoDto.ofServicos(servicos);
	}

	@Transactional
	public ServicoDto atualizarCompletamente(ServicoDto dto, Long id) {
		if (!servicoRepository.existsById(id)) {
			throw excecaoPorServicoNaoEncontrado(id);
		}

		Servico servico = dto.toEntity();
		servico.setId(id);

		servico = servicoRepository.save(servico);
		return ServicoDto.ofServico(servico);
	}

	@Transactional
	public ServicoDto atualizarParcialmente(ServicoDto dto, Long id) {
		Servico servico = servicoRepository.findById(id).orElseThrow(() -> excecaoPorServicoNaoEncontrado(id));

		atualizarParcialmente(servico, dto);

		servico = servicoRepository.save(servico);
		return ServicoDto.ofServico(servico);
	}

	public boolean existePorId(Long id) {
		return servicoRepository.existsById(id);
	}

	public ServicoDto buscarPorId(Long id) {
		Servico servico = servicoRepository.findById(id).orElseThrow(() -> excecaoPorServicoNaoEncontrado(id));

		return ServicoDto.ofServico(servico);
	}

	public List<ServicoDto> buscarTodos() {
		List<Servico> contratosFuncionario = servicoRepository.findAll();

		return ServicoDto.ofServicos(contratosFuncionario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		if (!servicoRepository.existsById(id)) {
			throw excecaoPorServicoNaoEncontrado(id);
		}

		servicoRepository.deleteById(id);
	}

	private void atualizarParcialmente(Servico servico, ServicoDto dto) {
		if (dto.clienteId != null)
			servico.setCliente(new Pessoa(dto.clienteId));
		if (dto.funcionariosId != null)
			servico.setFuncionarios(dto.funcionariosId.stream().map(ContratoFuncionario::new).toList());
		if (dto.statusServico != null)
			servico.setStatusServico(dto.statusServico);
		if (dto.produtosId != null)
			servico.setProdutos(dto.produtosId.stream().map(Produto::new).toList());
		if (dto.custoMaoDeObra != null)
			servico.setCustoMaoDeObra(dto.custoMaoDeObra);
		if (dto.custoProdutos != null)
			servico.setCustoProdutos(dto.custoProdutos);
		if (dto.descricao != null)
			servico.setDescricao(dto.descricao);
		if (dto.observacoes != null)
			servico.setObservacoes(dto.observacoes);
		if (dto.dataEntrada != null)
			servico.setDataEntrada(dto.dataEntrada);
		if (dto.dataSaida != null)
			servico.setDataSaida(dto.dataSaida);
	}

}
