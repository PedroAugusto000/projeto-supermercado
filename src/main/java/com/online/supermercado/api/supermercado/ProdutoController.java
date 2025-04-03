package com.online.supermercado.api.supermercado;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.online.supermercado.domain.Produto;
import com.online.supermercado.domain.ProdutoRepository;
import com.online.supermercado.domain.ProdutoService;
import com.online.supermercado.domain.dto.ProdutoDTO;

@RestController
@RequestMapping("api/projeto/v1/supermercado")
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
	
	@PostMapping
	public ResponseEntity post(@RequestBody Produto produto) { //@RequestBody pega o json da requisição e transforma em um objeto Produto

		ProdutoDTO produtoDTO = produtoService.insert(produto);
		
		URI location = getUri(produtoDTO.getId()); //Aqui ele vai chamar o método getUri que vai catar o id da minha produtoDTO
		// monta a URL onde o novo produto pode ser acessado -> http://localhost:8080/api/v1/produtos/{idProdutoNovo}
		return ResponseEntity.created(location).build(); //Responde com status 201 Created -> cabeçalho Location vai ter a nova  URI do novo produto
		//Pra ficar claro, o location está retornando a URL -> http://localhost:8080/api/v1/produtos/{idNovo}
		//build()
	}
	
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
    //ServletUriComponentsBuilder.fromCurrentRequest(): Isso aqui pega a URL atual da requisição http://localhost:8080/api/v1/produtos, ele vai trabalhar com essa URL - vai usar o HttpServletRequest por baixo dos panos pra saber de onde veio a chamada
    //.path("/{id}"): extendendo a URL /api/v1/produtos junto com o id /api/v1/produtos/{id}
    //.buildAndExpand(id): Aqui ele faz o replace do {id} pelo valor real, tipo 42 -> que vira: /api/v1/produtos/42
    //.toUri(): Transforma tudo que tu montou ali em cima numa URI de verdade, do tipo java.net.URI, pronta pra ser jogada no ResponseEntity.created

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Produto produto) {
    	
    	produto.setId(id);
    	
    	ProdutoDTO produtoDTO = produtoService.update(produto, id);
    	
    	return produtoDTO != null ?
                ResponseEntity.ok(produtoDTO) :
                ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
    	
    	produtoService.delete(id);
    	
    	return ResponseEntity.ok().build();
    }
}
