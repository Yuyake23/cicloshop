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

import edu.ifgoiano.trabalho.dto.PrecoProdutoDto;
import edu.ifgoiano.trabalho.service.PrecoProdutoService;

@RestController
@RequestMapping("/v1/preco_produto")
public class PrecoProdutoController {

	@Autowired
	private PrecoProdutoService precoProdutoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PrecoProdutoDto salvar(@RequestBody PrecoProdutoDto dto) {
		return precoProdutoService.salvar(dto);
	}

	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public List<PrecoProdutoDto> salvarTodos(@RequestBody List<PrecoProdutoDto> dtos) {
		return precoProdutoService.salvarTodos(dtos);
	}

	@GetMapping
	public List<PrecoProdutoDto> buscarTodos() {
		return precoProdutoService.buscarTodos();
	}

	@GetMapping("/{id}")
	public PrecoProdutoDto buscarPorId(@PathVariable Long id) {
		return precoProdutoService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public PrecoProdutoDto atualizarCompletamente(@PathVariable Long id, @RequestBody PrecoProdutoDto dto) {
		return precoProdutoService.atualizarCompletamente(dto, id);
	}

	@PatchMapping("/{id}")
	public PrecoProdutoDto atualizarParcialmente(@PathVariable Long id, @RequestBody PrecoProdutoDto dto) {
		return precoProdutoService.atualizarParcialmente(dto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		precoProdutoService.deletarPorId(id);
	}

}
