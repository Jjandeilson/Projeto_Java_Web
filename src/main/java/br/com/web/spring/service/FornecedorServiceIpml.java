package br.com.web.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.spring.model.Fornecedor;
import br.com.web.spring.repository.FornecedorRepository;

@Service
public class FornecedorServiceIpml implements FornecedorService{

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Override
	public void save(Fornecedor fornecedor) {
		fornecedorRepository.save(fornecedor);		
	}

	@Override
	public List<Fornecedor> listarFornecedor() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		return fornecedores;
	}

	@Override
	public void removerFornecedor(Long id) {
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		
		fornecedorRepository.delete(fornecedor);
		
	}

	@Override
	public void alterarFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.save(fornecedor);
		
	}

	@Override
	public Fornecedor buscar(Long id) {
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		return fornecedor;
	}

	@Override
	public List<Fornecedor> pesquisarFornecedor(String nome) {
		List<Fornecedor> fornecedores = fornecedorRepository.findByNomeFornecedorIgnoreCase(nome);
		return fornecedores;
	}
	
	
}
