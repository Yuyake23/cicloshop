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

import edu.ifgoiano.trabalho.dto.ContratoFuncionarioDto;
import edu.ifgoiano.trabalho.service.ContratoFuncionarioService;

@RestController
@RequestMapping("/v1/contrato_funcionario")
public class ContratoFuncionarioController {

	@Autowired
	private ContratoFuncionarioService contratoFuncionarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContratoFuncionarioDto salvar(@RequestBody ContratoFuncionarioDto dto) {
		return contratoFuncionarioService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ContratoFuncionarioDto> salvarTodos(@RequestBody List<ContratoFuncionarioDto> dtos) {
		return contratoFuncionarioService.salvarTodos(dtos);
	}

	@GetMapping
	public List<ContratoFuncionarioDto> buscarTodos() {
		return contratoFuncionarioService.buscarTodos();
	}

	@GetMapping("/{id}")
	public ContratoFuncionarioDto buscarPorId(@PathVariable Long id) {
		return contratoFuncionarioService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public ContratoFuncionarioDto atualizarCompletamente(@PathVariable Long id,
			@RequestBody ContratoFuncionarioDto dto) {
		return contratoFuncionarioService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public ContratoFuncionarioDto atualizarParcialmente(@PathVariable Long id,
			@RequestBody ContratoFuncionarioDto dto) {
		return contratoFuncionarioService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		contratoFuncionarioService.deletarPorId(id);
	}

}
