package edu.ifgoiano.trabalho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifgoiano.trabalho.dto.DadosBancariosDto;
import edu.ifgoiano.trabalho.service.DadosBancariosService;

@RestController
@RequestMapping("/v1/dados_bancarios")
public class DadosBancariosController {

	@Autowired
	private DadosBancariosService dadosBancariosService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DadosBancariosDto salvar(@RequestBody DadosBancariosDto dto) {
		return dadosBancariosService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<DadosBancariosDto> salvarTodos(@RequestBody List<DadosBancariosDto> dtos) {
		return dadosBancariosService.salvarTodos(dtos);
	}

	@GetMapping
	public List<DadosBancariosDto> buscarTodos() {
		return dadosBancariosService.buscarTodos();
	}

	@GetMapping("/{id}")
	public DadosBancariosDto buscarPorId(@PathVariable Long id) {
		return dadosBancariosService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public DadosBancariosDto atualizarCompletamente(@PathVariable Long id, @RequestBody DadosBancariosDto dto) {
		return dadosBancariosService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public DadosBancariosDto atualizarParcialmente(@PathVariable Long id, @RequestBody DadosBancariosDto dto) {
		return dadosBancariosService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		dadosBancariosService.deletarPorId(id);
	}

}
