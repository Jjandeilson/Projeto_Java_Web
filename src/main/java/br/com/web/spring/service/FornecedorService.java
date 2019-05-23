package br.com.web.spring.service;

import java.util.List;

import br.com.web.spring.model.Fornecedor;

public interface FornecedorService {

	void save(Fornecedor fornecedor);
	List<Fornecedor> listarFornecedor();
	void removerFornecedor(Long id);
	void alterarFornecedor(Fornecedor fornecedor);
	Fornecedor buscar(Long id);
	List<Fornecedor> pesquisarFornecedor(String nome);
}
