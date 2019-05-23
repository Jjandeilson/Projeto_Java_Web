package br.com.web.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.spring.model.Funcionario;
import br.com.web.spring.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceIpml implements FuncionarioService{

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		
	}

	@Override
	public List<Funcionario> listarFuncionario() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return funcionarios;
	}

	@Override
	public void removerFuncionario(Long id) {
		Funcionario funionario = funcionarioRepository.getOne(id);
		
		funcionarioRepository.delete(funionario);
		
	}

	@Override
	public void alterarFuncionario(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		
	}

	@Override
	public Funcionario buscar(Long id) {
		Funcionario funcionario = funcionarioRepository.getOne(id);
		return funcionario;
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(String nome) {
		List<Funcionario> funcionarios = funcionarioRepository.findByNomeFuncionario(nome);
		return funcionarios;
	}

}
