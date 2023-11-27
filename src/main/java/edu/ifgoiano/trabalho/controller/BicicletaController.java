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

import edu.ifgoiano.trabalho.dto.BicicletaDto;
import edu.ifgoiano.trabalho.service.BicicletaService;
import edu.ifgoiano.trabalho.service.ProdutoService;

@RestController
@RequestMapping("/v1/bicicleta")
public class BicicletaController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private BicicletaService bicicletaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BicicletaDto salvar(@RequestBody BicicletaDto dto) {
		return produtoService.salvar(dto);
	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Iterable<BicicletaDto> salvarTodos(@RequestBody Iterable<BicicletaDto> dtos) {
//		return produtoService.salvarTodos(dtos);
//	}
	
	@GetMapping
	public List<BicicletaDto> buscarTodos() {
		return bicicletaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public BicicletaDto buscarPorId(@PathVariable Long id) {
		return bicicletaService.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public BicicletaDto atualizarCompletamente(@PathVariable Long id, @RequestBody BicicletaDto dto) {
		return bicicletaService.atualizarCompletamente(dto, id);
	}
	
	@PatchMapping("/{id}")
	public BicicletaDto atualizarParcialmente(@PathVariable Long id, @RequestBody BicicletaDto dto) {
		return bicicletaService.atualizarParcialmente(dto, id);
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
