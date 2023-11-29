package edu.ifgoiano.trabalho.model.enums;

/**
 * Caracteriza o estado de {@link edu.ifgoiano.trabalho.model.entity.Servico}s registrados na
 * oficina. O estado pode ser alterado tanto por funcionários quanto clientes.
 */
public enum StatusServico {
  APROVADA,
  FINALIZADA,
  CANCELADA,
  FAZENDO_ORCAMENTO,
  AGUARDANDO_APROVACAO;
}
