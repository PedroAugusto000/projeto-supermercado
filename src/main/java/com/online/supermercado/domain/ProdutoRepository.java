package com.online.supermercado.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByCategoria(String categoria);
}
