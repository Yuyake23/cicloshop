package edu.ifgoiano.trabalho.controller;

import java.util.List;

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
@RequestMapping("/v1/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping
	public List<? extends PessoaDto> buscarTodos(){
		return pessoaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public PessoaDto buscarPorId(@PathVariable Long id){
		return pessoaService.buscarPorId(id);
	}
	
	@GetMapping("/{id}/produtos")
	public List<? extends ProdutoDto> buscarPorDono(@PathVariable Long id){
		return produtoService.buscarPorDono(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		produtoService.deletarPorId(id);
	}
}
