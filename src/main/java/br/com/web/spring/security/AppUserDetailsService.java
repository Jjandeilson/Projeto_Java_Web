package br.com.web.spring.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.web.spring.model.Usuario;
import br.com.web.spring.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepopsitory;
	
	@Override
	public UserDetails loadUserByUsername(String usuarioLogin) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepopsitory.findByUsuarioLogin(usuarioLogin);
		Usuario usuario = usuarioOptional.orElseThrow(()-> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
		System.out.println(usuario.getUsuarioLogin()+" "+usuario.getSenha()+" "+usuario.getRoles().toString());
		return new User(usuarioLogin, usuario.getSenha(),getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getRoles().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao())));
		return authorities;
	}

}
