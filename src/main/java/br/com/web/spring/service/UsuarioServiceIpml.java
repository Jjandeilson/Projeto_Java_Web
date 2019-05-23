package br.com.web.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.web.spring.model.Usuario;
import br.com.web.spring.repository.UsuarioRepository;

@Service
public class UsuarioServiceIpml implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void save(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		System.out.println(usuario.getSenha());
		usuarioRepository.save(usuario);
		
	}

	@Override
	public List<Usuario> listarUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	@Override
	public void removerUsuario(Long id) {
		Usuario usuario = usuarioRepository.getOne(id);
		
		usuarioRepository.delete(usuario);
		
	}

	@Override
	public void alterarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		
	}

	@Override
	public Usuario buscar(Long id) {
		Usuario usuario = usuarioRepository.getOne(id);
		return usuario;
	}

	@Override
	public Optional<Usuario> buscar(String nome) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuarioLogin(nome);
		return usuario;
	}

}
