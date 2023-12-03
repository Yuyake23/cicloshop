package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorServicoNaoEncontrado;

import edu.ifgoiano.trabalho.dto.ServicoDto;
import edu.ifgoiano.trabalho.model.entity.ContratoFuncionario;
import edu.ifgoiano.trabalho.model.entity.Peca;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import edu.ifgoiano.trabalho.model.entity.Servico;
import edu.ifgoiano.trabalho.model.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServicoService {

  @Autowired private ServicoRepository servicoRepository;

  @Transactional
  public ServicoDto salvar(ServicoDto dto) {
    Servico servico = servicoRepository.save(dto.toEntity());

    log.info("Serviço \"" + servico.getId() + "\" criado.");

    return ServicoDto.ofServico(servico);
  }

  @Transactional
  public Iterable<ServicoDto> salvarTodos(Iterable<ServicoDto> dtos) {
    List<Servico> servicos =
        StreamSupport.stream(dtos.spliterator(), true).map(ServicoDto::toEntity).toList();

    servicos = servicoRepository.saveAll(servicos);

    log.info("Serviços \"" + servicos.stream().map(Servico::getId).toList() + "\" criados.");

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

    log.info("Serviço \"" + servico.getId() + "\" atualizado.");

    return ServicoDto.ofServico(servico);
  }

  @Transactional
  public ServicoDto atualizarParcialmente(ServicoDto dto, Long id) {
    Servico servico =
        servicoRepository.findById(id).orElseThrow(() -> excecaoPorServicoNaoEncontrado(id));

    atualizarParcialmente(servico, dto);

    servico = servicoRepository.save(servico);

    log.info("Serviço \"" + servico.getId() + "\" atualizado.");

    return ServicoDto.ofServico(servico);
  }

  public boolean existePorId(Long id) {
    return servicoRepository.existsById(id);
  }

  public ServicoDto buscarPorId(Long id) {
    Servico servico =
        servicoRepository.findById(id).orElseThrow(() -> excecaoPorServicoNaoEncontrado(id));

    return ServicoDto.ofServico(servico);
  }

  public Iterable<ServicoDto> buscarTodos() {
    List<Servico> contratosFuncionario = servicoRepository.findAll();

    return ServicoDto.ofServicos(contratosFuncionario);
  }

  @Transactional
  public void deletarPorId(Long id) {
    if (!servicoRepository.existsById(id)) {
      throw excecaoPorServicoNaoEncontrado(id);
    }

    servicoRepository.deleteById(id);

    log.info("Serviço \"" + id + "\" atualizado.");
  }

  private void atualizarParcialmente(Servico servico, ServicoDto dto) {
    if (dto.clienteId != null) servico.setCliente(new Pessoa(dto.clienteId));
    if (dto.funcionariosId != null)
      servico.setFuncionarios(dto.funcionariosId.stream().map(ContratoFuncionario::new).toList());
    if (dto.statusServico != null) servico.setStatusServico(dto.statusServico);
    if (dto.pecasId != null) servico.setPecas(dto.pecasId.stream().map(Peca::new).toList());
    if (dto.custoMaoDeObra != null) servico.setCustoMaoDeObra(dto.custoMaoDeObra);
    if (dto.custoProdutos != null) servico.setCustoProdutos(dto.custoProdutos);
    if (dto.descricao != null) servico.setDescricao(dto.descricao);
    if (dto.observacoes != null) servico.setObservacoes(dto.observacoes);
    if (dto.dataEntrada != null) servico.setDataEntrada(dto.dataEntrada);
    if (dto.dataSaida != null) servico.setDataSaida(dto.dataSaida);
  }
}
