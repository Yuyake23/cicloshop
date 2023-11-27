package edu.ifgoiano.trabalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifgoiano.trabalho.dto.PessoaDto;
import edu.ifgoiano.trabalho.dto.ProdutoDto;
import edu.ifgoiano.trabalho.service.PessoaService;
import edu.ifgoiano.trabalho.service.ProdutoService;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public Iterable<? extends ProdutoDto> buscarTodos(){
		return produtoService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ProdutoDto buscarPorId(@PathVariable Long id){
		return produtoService.buscarPorId(id);
	}
	
	@GetMapping("/{id}/dono")
	public PessoaDto buscarDonoDoProdutoComId(@PathVariable Long id){
		return pessoaService.buscarPorId(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		produtoService.deletarPorId(id);
	}
	
}
