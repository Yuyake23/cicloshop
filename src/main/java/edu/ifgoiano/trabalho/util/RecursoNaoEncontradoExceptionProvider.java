package edu.ifgoiano.trabalho.util;

import edu.ifgoiano.trabalho.exception.ArquivoNaoEncontradoException;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;

public class RecursoNaoEncontradoExceptionProvider {

  public static RecursoNaoEncontradoException excecaoPorPessoaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException("Pessoa com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorPessoaFisicaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException(
        "PessoaFisica com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorPessoaJuridicaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException(
        "PessoaJuridica com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorFornecedorNaoEncontrado(Long id) {
    return new RecursoNaoEncontradoException("Fornecedor com id=%d não encontrado.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorProdutoNaoEncontrado(Long id) {
    return new RecursoNaoEncontradoException("Produto com id=%d não encontrado.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorPecaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException("Peca com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorBicicletaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException("Bicicleta com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorContratoFuncionarioNaoEncontrado(Long id) {
    return new RecursoNaoEncontradoException(
        "ContratoFuncionario com id=%d não encontrado.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorPrecoProdutoNaoEncontrado(Long id) {
    return new RecursoNaoEncontradoException(
        "PrecoProduto com id=%d não encontrado.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorDadosBancariosNaoEncontrados(Long id) {
    return new RecursoNaoEncontradoException(
        "DadosBancarios com id=%d não encontrado.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorVendaNaoEncontrada(Long id) {
    return new RecursoNaoEncontradoException("Venda com id=%d não encontrada.".formatted(id));
  }

  public static RecursoNaoEncontradoException excecaoPorServicoNaoEncontrado(Long id) {
    return new RecursoNaoEncontradoException("Servico com id=%d não encontrado.".formatted(id));
  }
  
  public static ArquivoNaoEncontradoException excecaoPorArquivoNaoEncontrado(String nomeArquivo) {
    return new ArquivoNaoEncontradoException("Arquivo %s não encontrado.".formatted(nomeArquivo));
  }
}
