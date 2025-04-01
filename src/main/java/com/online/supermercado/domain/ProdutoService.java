package com.online.supermercado.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.supermercado.domain.dto.ProdutoDTO;

@Service //Anotação pra dizer que essa classe vai ser utilizada como o nosso serviço
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> getProdutos() {
		List<ProdutoDTO> list = produtoRepository.findAll().stream().map(ProdutoDTO::create).collect(Collectors.toList());// Vamos destrinchar...
		//findAll: cata todos os registros da tabela produto
		//stream: transforma a lista num stream. Meio que um rio de dados que a gente pode trabalhar em cima (filtrar, mapear, transformar)
		//map: Transforma cada item da lista
		/*ProdutoDTO::Create seria -> produto -> ProdutoDTO.create(produto): É ele quem quem pega cada produto da stream 
		e chama o ProdutoDTO.create(produto) pra ele virar DTO produto vira produtoDTO*/
		//collect: junta tudo
		//Collectors.toList: Juntou tudo e colocou de volta na lista
		//Resultado disso tudo na List<ProduDTO> list, em específico na variável list. Todos os ProdutoDTO estão guardados bonitinhos nela
		//return list devolve a lista mil grau
		return list;
	}
}
