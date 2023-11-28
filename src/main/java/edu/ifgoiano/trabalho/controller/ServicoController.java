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

import edu.ifgoiano.trabalho.dto.ServicoDto;
import edu.ifgoiano.trabalho.service.ServicoService;

@RestController
@RequestMapping("/v1/servico")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoDto salvar(@RequestBody ServicoDto dto) {
		return servicoService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ServicoDto> salvarTodos(@RequestBody List<ServicoDto> dtos) {
		return servicoService.salvarTodos(dtos);
	}

	@GetMapping
	public List<ServicoDto> buscarTodos() {
		return servicoService.buscarTodos();
	}

	@GetMapping("/{id}")
	public ServicoDto buscarPorId(@PathVariable Long id) {
		return servicoService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public ServicoDto atualizarCompletamente(@PathVariable Long id, @RequestBody ServicoDto dto) {
		return servicoService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public ServicoDto atualizarParcialmente(@PathVariable Long id, @RequestBody ServicoDto dto) {
		return servicoService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		servicoService.deletarPorId(id);
	}

}
