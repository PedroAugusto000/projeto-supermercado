package com.online.supermercado.api.supermercado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.online.supermercado.domain.Produto;
import com.online.supermercado.domain.ProdutoService;
import com.online.supermercado.domain.dto.ProdutoDTO;

@RestController
@RequestMapping("api/projeto/supermercado")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping()
	public ResponseEntity get() {
		List<ProdutoDTO> produto = produtoService.getProdutos();
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping("{id}")
	public ResponseEntity get(@PathVariable("id")Long id){ //PathVariable pega valores direto da URL, então ele vai catar o número do ID, caso exista, quando eu digitar api/projeto/supermercado/1
		ProdutoDTO produto = produtoService.getProdutoById(id); //Aqui eu só tô chamando o método do meu service e instanciando o ProdutoDTO como produto
		
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity get(@PathVariable("categoria") String categoria) {
		List<ProdutoDTO> produto = produtoService.getProdutoByCategoria(categoria);
		return ResponseEntity.ok(produto);
	}
}
