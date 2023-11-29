package edu.ifgoiano.trabalho.dto;

import edu.ifgoiano.trabalho.model.entity.Bicicleta;
import edu.ifgoiano.trabalho.model.entity.Pessoa;
import java.math.BigDecimal;
import java.util.List;

public final class BicicletaDto extends ProdutoDto {

  public final String codigoSerial;
  public final String modelo;
  public final String cor;

  public BicicletaDto(
      Long id,
      Long donoId,
      String marca,
      BigDecimal valor,
      String codigoSerial,
      String modelo,
      String cor) {
    super(id, donoId, marca, valor);
    this.codigoSerial = codigoSerial;
    this.modelo = modelo;
    this.cor = cor;
  }

  public BicicletaDto(Bicicleta bicicleta) {
    super(bicicleta);
    this.codigoSerial = bicicleta.getCodigoSerial();
    this.modelo = bicicleta.getCodigoSerial();
    this.cor = bicicleta.getCor();
  }

  @Override
  public Bicicleta toEntity() {
    return new Bicicleta(donoId, new Pessoa(donoId), marca, valor, codigoSerial, modelo, cor);
  }

  public static BicicletaDto ofBicicleta(Bicicleta bicicleta) {
    return new BicicletaDto(bicicleta);
  }

  public static List<BicicletaDto> ofBicicletas(List<Bicicleta> pecas) {
    return pecas.stream().map(BicicletaDto::ofBicicleta).toList();
  }
}
