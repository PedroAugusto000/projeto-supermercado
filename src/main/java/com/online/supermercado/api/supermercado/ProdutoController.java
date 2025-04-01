package com.online.supermercado.api.supermercado;

import java.util.List;

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
		List<ProdutoDTO> produtos = produtoService.getProdutos();
		return ResponseEntity.ok(produtos);
	}
}
