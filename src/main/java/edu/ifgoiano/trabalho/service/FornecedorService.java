package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorFornecedorNaoEncontrado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.trabalho.dto.FornecedorDto;
import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.repository.FornecedorRepository;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Transactional
	public FornecedorDto atualizarCompletamente(FornecedorDto dto, Long id) {
		if (!fornecedorRepository.existsById(id)) {
			throw excecaoPorFornecedorNaoEncontrado(id);
		}

		Fornecedor fornecedor = dto.toEntity();

		fornecedor = fornecedorRepository.save(fornecedor);
		return FornecedorDto.ofFornecedor(fornecedor);
	}

	@Transactional
	public FornecedorDto atualizarParcialmente(FornecedorDto dto, Long id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id)
				.orElseThrow(() -> excecaoPorFornecedorNaoEncontrado(id));

		atualizarParcialmente(fornecedor, dto);

		fornecedor = fornecedorRepository.save(fornecedor);
		return FornecedorDto.ofFornecedor(fornecedor);
	}

	public boolean existePorId(Long id) {
		return fornecedorRepository.existsById(id);
	}

	public FornecedorDto buscarPorId(Long id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id)
				.orElseThrow(() -> excecaoPorFornecedorNaoEncontrado(id));

		return FornecedorDto.ofFornecedor(fornecedor);
	}

	public List<FornecedorDto> buscarTodos() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();

		return FornecedorDto.ofFornecedores(fornecedores);
	}

	private void atualizarParcialmente(Fornecedor fornecedor, FornecedorDto dto) {
		if (dto.razaoSocial != null)
			fornecedor.setNome(dto.razaoSocial);
		if (dto.cnpj != null)
			fornecedor.setDocumento(dto.cnpj);
		if (dto.telefone != null)
			fornecedor.setTelefone(dto.telefone);
		if (dto.endereco != null)
			fornecedor.setEndereco(dto.endereco);
		if (dto.dataCriacao != null)
			fornecedor.setDataGenese(dto.dataCriacao);
		if (dto.observacoes != null)
			fornecedor.setObservacoes(dto.observacoes);
		if (dto.nomeFantasia != null)
			fornecedor.setNomeFantasia(dto.nomeFantasia);
	}

}
