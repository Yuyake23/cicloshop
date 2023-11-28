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

import edu.ifgoiano.trabalho.dto.FornecedorDto;
import edu.ifgoiano.trabalho.service.FornecedorService;
import edu.ifgoiano.trabalho.service.PessoaService;

@RestController
@RequestMapping("/v1/fornecedor")
public class FornecedorController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FornecedorDto salvar(@RequestBody FornecedorDto dto) {
		return pessoaService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<FornecedorDto> salvarTodos(@RequestBody List<FornecedorDto> dtos) {
		return pessoaService.salvarTodos(dtos);
	}

	@GetMapping
	public List<FornecedorDto> buscarTodos() {
		return fornecedorService.buscarTodos();
	}

	@GetMapping("/{id}")
	public FornecedorDto buscarPorId(@PathVariable Long id) {
		return fornecedorService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public FornecedorDto atualizarCompletamente(@PathVariable Long id, @RequestBody FornecedorDto dto) {
		return fornecedorService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public FornecedorDto atualizarParcialmente(@PathVariable Long id, @RequestBody FornecedorDto dto) {
		return fornecedorService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		pessoaService.deletarPorId(id);
	}

}
