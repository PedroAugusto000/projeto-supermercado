package com.online.supermercado.domain.dto;

import org.modelmapper.ModelMapper;

import com.online.supermercado.domain.Produto;

import lombok.Data;

@Data
public class ProdutoDTO {
	
    private Long id;
    private String nome;
    private String categoria;
	
    public static ProdutoDTO create(Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produto, ProdutoDTO.class);
    }//Converte qualquer entidade Produto em ProdutoDTO copiando os atributos compátiveis
}
