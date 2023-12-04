package edu.ifgoiano.trabalho.service;

import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorArquivoNaoEncontrado;
import static edu.ifgoiano.trabalho.util.RecursoNaoEncontradoExceptionProvider.excecaoPorProdutoNaoEncontrado;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import edu.ifgoiano.trabalho.config.FileStorageConfig;
import edu.ifgoiano.trabalho.exception.ArmazenamentoDeAquivoException;
import edu.ifgoiano.trabalho.exception.TipoDeArquivoNaoSuportado;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArquivoService {

  private final ProdutoService produtoService;
  private final Path caminhoArmazenamento;

  @Autowired
  public ArquivoService(FileStorageConfig fileStorageConfig, ProdutoService produtoRepository) {
    this.produtoService = produtoRepository;

    this.caminhoArmazenamento =
        Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.caminhoArmazenamento);
    } catch (Exception e) {
      throw new ArmazenamentoDeAquivoException(
          "Não foi possivel criar o diretório para upload de arquivos.");
    }
  }

  public String salvarArquivo(MultipartFile arquivo, Long idProduto) {
    if (!produtoService.existePorId(idProduto)) {
      throw excecaoPorProdutoNaoEncontrado(idProduto);
    }

    String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
    if (!nomeArquivo.endsWith(".png"))
      throw new TipoDeArquivoNaoSuportado();
    
    nomeArquivo = String.valueOf(idProduto) + ".png";

    try {
      Path caminhoDestino = this.caminhoArmazenamento.resolve(nomeArquivo);

      Files.copy(arquivo.getInputStream(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
      log.info("Arquivo " + caminhoDestino + " criado.");
      return nomeArquivo;
    } catch (Exception e) {
      throw new ArmazenamentoDeAquivoException(
          "Não foi possivel armazenar o arquivo %s.".formatted(nomeArquivo));
    }
  }

  public Resource buscarArquivo(String nomeArquivo) {
    try {
      Path filePath = this.caminhoArmazenamento.resolve(nomeArquivo).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists())
        return resource;
      else
        throw excecaoPorArquivoNaoEncontrado(nomeArquivo);
    } catch (Exception e) {
      throw excecaoPorArquivoNaoEncontrado(nomeArquivo);
    }
  }
  
  public Resource buscarArquivo(Long idProduto) {
    return buscarArquivo(String.valueOf(idProduto) + ".png");
  }

  public void deletarArquivoSeExistir(String nomeArquivo) {
    try {
      Path filePath = this.caminhoArmazenamento.resolve(nomeArquivo).normalize();

      if (Files.exists(filePath)) {
        Files.delete(filePath);
        log.info("Arquivo " + filePath.getFileName() + " deletado.");
      } else {
        log.warn("Tentativa de deletar arquivo inexistente: {}", filePath);
      }
    } catch (NoSuchFileException e) {
      log.warn("Arquivo não encontrado: {}", e.getFile());
    } catch (IOException e) {
        throw new ArmazenamentoDeAquivoException(
            "Não foi possível deletar o arquivo %s.".formatted(nomeArquivo));
    } 
    
  }
  
  public void deletarArquivoSeExistir(Long idProduto) {
    deletarArquivoSeExistir(idProduto + ".png");
  }

}
