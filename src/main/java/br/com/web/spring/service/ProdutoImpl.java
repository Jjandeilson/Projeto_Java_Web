package br.com.web.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.spring.model.Produto;
import br.com.web.spring.repository.ProdutoRepository;

@Service
public class ProdutoImpl implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void save(Produto produto) {
		produtoRepository.save(produto);		
	}

	@Override
	public List<Produto> listarProduto() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}

	@Override
	public void removerProduto(Long id) {
		Produto produto = produtoRepository.getOne(id);	
		produtoRepository.delete(produto);		
	}

	@Override
	public void alterarProduto(Produto produto) {
		produtoRepository.save(produto);		
	}

	@Override
	public Produto buscar(Long id) {
		return produtoRepository.getOne(id);
	}

	@Override
	public List<Produto> pesquisarProduto(String nome) {
		List<Produto> produtos = produtoRepository.findByNomeProduto(nome);
		return produtos;
	}

}
