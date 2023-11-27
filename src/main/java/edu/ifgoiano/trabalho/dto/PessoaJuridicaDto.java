package edu.ifgoiano.trabalho.dto;

import java.time.LocalDate;
import java.util.List;

import edu.ifgoiano.trabalho.model.entity.Fornecedor;
import edu.ifgoiano.trabalho.model.entity.PessoaJuridica;
import edu.ifgoiano.trabalho.model.enums.TipoPessoa;

public sealed class PessoaJuridicaDto extends PessoaDto permits FornecedorDto {

	public final String cnpj;
	public final String razaoSocial;
	public final String nomeFantasia;
	public final LocalDate dataCriacao;

	public PessoaJuridicaDto(Long id, TipoPessoa tipoPessoa, String telefone, String endereco, String observacoes,
			String cnpj, String razaoSocial, String nomeFantasia, LocalDate dataCriacao) {
		super(id, tipoPessoa, telefone, endereco, observacoes);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.dataCriacao = dataCriacao;
	}

	public PessoaJuridicaDto(PessoaJuridica pessoaJuridica) {
		super(pessoaJuridica);
		this.cnpj = pessoaJuridica.getDocumento();
		this.razaoSocial = pessoaJuridica.getNome();
		this.nomeFantasia = pessoaJuridica.getNomeFantasia();
		this.dataCriacao = pessoaJuridica.getDataGenese();
	}

	@Override
	public PessoaJuridica toEntity() {
		return new PessoaJuridica(id, cnpj, razaoSocial, nomeFantasia, telefone, endereco, dataCriacao, observacoes);
	}

	public static PessoaJuridicaDto ofPessoaJuridica(PessoaJuridica pessoaJuridica) {
		if (pessoaJuridica instanceof Fornecedor fornecedor) {
			return FornecedorDto.ofFornecedor(fornecedor);
		} else {
			return new PessoaJuridicaDto(pessoaJuridica);
		}
	}

	public static List<? extends PessoaJuridicaDto> ofPessoasJuridicas(List<PessoaJuridica> pessoasJuridicas) {
		return pessoasJuridicas.stream().map(PessoaJuridicaDto::ofPessoaJuridica).toList();
	}

}
