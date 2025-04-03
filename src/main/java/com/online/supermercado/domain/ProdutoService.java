package com.online.supermercado.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.online.supermercado.api.exception.ObjectNotFoundException;
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
	
	public ProdutoDTO getProdutoById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.map(ProdutoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
	}
	/*Optional: Famoso pra controlar o NullPointerException, é uma classe do java que representa um valor que existe ou não
	 Pra mim: List: Pode ter vários | Optional: Ou tem um ou tem porra nenhuma
	 */
	//map(ProdutoDTO::create): o map() transforma ele em ProdutoDTO, usando aquele da modelmapper
	//O resto é só tratando com a exceção usando expressão lambda
	
	public List<ProdutoDTO> getProdutoByCategoria(String categoria) {
		List<ProdutoDTO> list = produtoRepository.findByCategoria(categoria).stream().map(ProdutoDTO::create).collect(Collectors.toList());
		return list;
	}
	
	public ProdutoDTO insert (Produto produto){
		Assert.isNull(produto.getId(), "Não foi possíveis inserir o registro!");
		
		return ProdutoDTO.create(produtoRepository.save(produto));
		
		//Assert.isNull eu to garantido que o id do produto é nulo, se não ele lança uma exceção
		//produtoRepository.save(produto) chama o método do repositório pra salvar 
		//return ProdutoDTO.create to chamando o meu método create lá da ProdutoDTO
	}
	
	public ProdutoDTO update (Produto produto, Long id) {
		Assert.notNull(id,"Não foi possível atualizar o registro"); //ID for nulo já trava tudo aqui
		
		Optional<Produto> optional = produtoRepository.findById(id); //Acha o produto pelo id
		if (optional.isPresent()) { //Se tiver presente...
			
			Produto db = optional.get(); //Pega o produto de dentro do optional
			
			db.setNome(produto.getNome()); //Pega os dados antigos e já atualiza com os novos
			db.setCategoria(produto.getCategoria()); //Pega os dados antigos e já atualiza com os novos
			System.out.println("Produto id " + db.getId());
			
			produtoRepository.save(db);
			
			return ProdutoDTO.create(db);
			
		} else {
			return null;
			//throw new RuntimeErrorException("Não foi possível atualizar o registro");
		}
	}
	
	public void delete (Long id) {
		produtoRepository.deleteById(id);
	}
}
