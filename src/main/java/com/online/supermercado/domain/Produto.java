package com.online.supermercado.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //Cria um constructor sem argumentos - Lombok
@Data //Cria a porra toda: getters and setters, hashCode, toString, equals...
@Entity //JPA Hipernate: Essa classe "Produto" representa uma tabela no banco de dados
public class Produto {
	
	@Id //Chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Banco vai gerar o valor do ID pra mim e incrementar +1
	private Long id;
	
	private String nome;
	
	@Column(precision = 5, scale = 2) //Garatir que a JPA mapeia direitinho no banco com o decimal (5, 2)
	private BigDecimal preco;
	private String categoria;
	private String descricao;
	private String urlFoto;
}
