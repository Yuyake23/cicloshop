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

import edu.ifgoiano.trabalho.dto.PessoaJuridicaDto;
import edu.ifgoiano.trabalho.service.PessoaJuridicaService;
import edu.ifgoiano.trabalho.service.PessoaService;

@RestController
@RequestMapping("/v1/pessoa_juridica")
public class PessoaJuridicaController {
	
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaJuridicaDto salvar(@RequestBody PessoaJuridicaDto dto) {
		return pessoaService.salvar(dto);
	}
	
	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<PessoaJuridicaDto> salvarTodos(@RequestBody List<PessoaJuridicaDto> dtos) {
		return pessoaService.salvarTodos(dtos);
	}
	
	@GetMapping
	public List<? extends PessoaJuridicaDto> buscarTodos() {
		return pessoaJuridicaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public PessoaJuridicaDto buscarPorId(@PathVariable Long id) {
		return pessoaJuridicaService.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public PessoaJuridicaDto atualizarCompletamente(@PathVariable Long id, @RequestBody PessoaJuridicaDto dto) {
		return pessoaJuridicaService.atualizarCompletamente(dto, id);
	}
	
	@PatchMapping("/{id}")
	public PessoaJuridicaDto atualizarParcialmente(@PathVariable Long id, @RequestBody PessoaJuridicaDto dto) {
		return pessoaJuridicaService.atualizarParcialmente(dto, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		pessoaService.deletarPorId(id);
	}
	
}
