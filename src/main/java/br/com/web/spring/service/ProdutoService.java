package br.com.web.spring.service;

import java.util.List;

import br.com.web.spring.model.Produto;


public interface ProdutoService {

	void save(Produto produto);
	List<Produto> listarProduto();
	void removerProduto(Long id);
	void alterarProduto(Produto produto);
	Produto buscar(Long id);
	List<Produto> pesquisarProduto(String nome);
}
