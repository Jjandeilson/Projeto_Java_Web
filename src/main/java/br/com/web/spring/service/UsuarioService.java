package br.com.web.spring.service;

import java.util.List;
import java.util.Optional;

import br.com.web.spring.model.Usuario;

public interface UsuarioService {

	void save(Usuario usuario);
	List<Usuario> listarUsuario();
	void removerUsuario(Long id);
	void alterarUsuario(Usuario usuario);
	Usuario buscar(Long id);
	Optional<Usuario> buscar(String nome);
}
