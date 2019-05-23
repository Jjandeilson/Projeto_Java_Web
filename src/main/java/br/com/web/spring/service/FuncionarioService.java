package br.com.web.spring.service;

import java.util.List;

import br.com.web.spring.model.Funcionario;

public interface FuncionarioService {

	void save(Funcionario funcionario);
	List<Funcionario> listarFuncionario();
	void removerFuncionario(Long id);
	void alterarFuncionario(Funcionario funcionario);
	Funcionario buscar(Long id);
	List<Funcionario> pesquisarFuncionario(String nome);
}

