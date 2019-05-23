package br.com.web.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.web.spring.model.Usuario;
import br.com.web.spring.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
	public ModelAndView principalUsuario() {
		ModelAndView model = new ModelAndView("usuario/usuarioPrincipal");
		List<Usuario> usuarios = usuarioService.listarUsuario();
		model.addObject("usuarios", usuarios);
		return model;
	}
	
	@RequestMapping("/cadastrarUsuario")
	public String cadastraProduto(@ModelAttribute("usuario") Usuario usuario, ModelMap model ) {
		return "usuario/usuarioCadastrar";
	}
	
	@RequestMapping(value="/cadastrarUsuario/salvar", method = RequestMethod.POST)
	public ModelAndView salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("usuario/usuarioCadastrar");
		}
		usuarioService.save(usuario);
		attri.addFlashAttribute("messagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value="/usuarios/{id}", method = RequestMethod.GET)
	public String deletar(@PathVariable("id") Long id, RedirectAttributes attri) {
		Usuario usuario = usuarioService.buscar(id);
		
		usuarioService.removerUsuario(usuario.getIdUsuario());
		attri.addFlashAttribute("messagem", "Removido com sucesso!");
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value="/usuario/alterar/{id}", method = RequestMethod.GET)
	public ModelAndView buscaAlterar(@PathVariable("id") Long id, ModelMap model) {
		Usuario usuario = usuarioService.buscar(id);
		model.addAttribute("usuario", usuario);
		return new ModelAndView("usuario/usuarioEditar", model);
	}
	
	@RequestMapping(value="/usuario/alterar", method = RequestMethod.POST)
	public ModelAndView buscaAlterar(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("usuario/usuarioEditar") ;
		}
		usuarioService.save(usuario);
		attri.addFlashAttribute("messagem", "Alterado com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(value="/usuarios/nomeUsuario", method = RequestMethod.GET)
	public ModelAndView buscarUsuario(@ModelAttribute("usuario") Usuario usuario, ModelMap model) {
		Optional<Usuario> u = usuarioService.buscar(usuario.getUsuarioLogin());
		if(!u.isPresent() ) {
			return new ModelAndView("redirect:/usuarios");
		}
		Usuario u2 = new Usuario();
		u2.setIdUsuario(u.get().getIdUsuario());
		u2.setUsuarioLogin(u.get().getUsuarioLogin());
		u2.setSenha(u.get().getSenha());
		u2.setTipo(u.get().getTipo());
		model.addAttribute("usuarios", u2);
		return new ModelAndView("/usuario/pesquisaUsuario", model);
	}
}
