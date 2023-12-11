package edu.ifgoiano.trabalho.util;

import edu.ifgoiano.trabalho.exception.ContratoFuncionarioInvalidoException;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;

public class ContratoFuncionarioUtil {

  public static void verificaContrato(ContratoFuncionario funcionario) {
    if (funcionario.getDataSaida() != null) {
      throw new ContratoFuncionarioInvalidoException(
          "O contrato de funcionario não está mais ativo. Id=%d".formatted(funcionario.getId()));
    }
  }
  
}
