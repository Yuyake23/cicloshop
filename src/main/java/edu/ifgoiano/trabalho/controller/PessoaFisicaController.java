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

import edu.ifgoiano.trabalho.dto.PessoaFisicaDto;
import edu.ifgoiano.trabalho.service.PessoaFisicaService;
import edu.ifgoiano.trabalho.service.PessoaService;

@RestController
@RequestMapping("/v1/pessoa_fisica")
public class PessoaFisicaController {
	
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaFisicaDto salvar(@RequestBody PessoaFisicaDto dto) {
		return pessoaService.salvar(dto);
	}
	
	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<PessoaFisicaDto> salvarTodos(@RequestBody List<PessoaFisicaDto> dtos) {
		return pessoaService.salvarTodos(dtos);
	}
	
	@GetMapping
	public List<PessoaFisicaDto> buscarTodos() {
		return pessoaFisicaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public PessoaFisicaDto buscarPorId(@PathVariable Long id) {
		return pessoaFisicaService.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public PessoaFisicaDto atualizarCompletamente(@PathVariable Long id, @RequestBody PessoaFisicaDto dto) {
		return pessoaFisicaService.atualizarCompletamente(dto, id);
	}
	
	@PatchMapping("/{id}")
	public PessoaFisicaDto atualizarParcialmente(@PathVariable Long id, @RequestBody PessoaFisicaDto dto) {
		return pessoaFisicaService.atualizarParcialmente(dto, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		pessoaService.deletarPorId(id);
	}
	
}
