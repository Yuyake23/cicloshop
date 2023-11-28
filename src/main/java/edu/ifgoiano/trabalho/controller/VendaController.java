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

import edu.ifgoiano.trabalho.dto.VendaDto;
import edu.ifgoiano.trabalho.service.VendaService;

@RestController
@RequestMapping("/v1/venda")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendaDto salvar(@RequestBody VendaDto dto) {
		return vendaService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<VendaDto> salvarTodos(@RequestBody List<VendaDto> dtos) {
		return vendaService.salvarTodos(dtos);
	}

	@GetMapping
	public List<VendaDto> buscarTodos() {
		return vendaService.buscarTodos();
	}

	@GetMapping("/{id}")
	public VendaDto buscarPorId(@PathVariable Long id) {
		return vendaService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public VendaDto atualizarCompletamente(@PathVariable Long id, @RequestBody VendaDto dto) {
		return vendaService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public VendaDto atualizarParcialmente(@PathVariable Long id, @RequestBody VendaDto dto) {
		return vendaService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		vendaService.deletarPorId(id);
	}

}
