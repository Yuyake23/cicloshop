package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class TipoDeArquivoNaoSuportado extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public TipoDeArquivoNaoSuportado() {
    super("Extensão de arquivo não suportada. Apenas arquivos \"png\" são suportados");
  }

  
  
}
