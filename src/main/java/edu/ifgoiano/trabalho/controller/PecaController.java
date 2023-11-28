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

import edu.ifgoiano.trabalho.dto.PecaDto;
import edu.ifgoiano.trabalho.service.PecaService;
import edu.ifgoiano.trabalho.service.ProdutoService;

@RestController
@RequestMapping("/v1/peca")
public class PecaController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PecaService pecaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PecaDto salvar(@RequestBody PecaDto dto) {
		return produtoService.salvar(dto);
	}
	
	@PostMapping("/varias")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<PecaDto> salvarTodos(@RequestBody Iterable<PecaDto> dtos) {
		return produtoService.salvarTodos(dtos);
	}
	
	@GetMapping
	public List<PecaDto> buscarTodos() {
		return pecaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public PecaDto buscarPorId(@PathVariable Long id) {
		return pecaService.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public PecaDto atualizarCompletamente(@PathVariable Long id, @RequestBody PecaDto dto) {
		return pecaService.atualizarCompletamente(dto, id);
	}
	
	@PatchMapping("/{id}")
	public PecaDto atualizarParcialmente(@PathVariable Long id, @RequestBody PecaDto dto) {
		return pecaService.atualizarParcialmente(dto, id);
	}
	
//	TODO
//	@GetMapping("/{id}/dono")
//	public PessoaDto buscarDonoDoProdutoComId(Long id){
//		return pessoaService.buscarPorId(id);
//	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		produtoService.deletarPorId(id);
	}
}
