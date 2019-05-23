package br.com.web.spring.controller;

import java.util.List;

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

import br.com.web.spring.model.Funcionario;
import br.com.web.spring.service.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@RequestMapping(value="/funcionarios", method = RequestMethod.GET)
	public ModelAndView principalProduto() {
		ModelAndView model = new ModelAndView("funcionario/funcionarioPrincipal");
		List<Funcionario> funcionarios = funcionarioService.listarFuncionario();
		model.addObject("funcionarios", funcionarios);
		return model;
	}
	
	@RequestMapping("/cadastrarFuncionario")
	public String cadastraFuncionario(@ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {
		return "funcionario/funcionarioCadastrar";
	}
	
	@RequestMapping(value="/cadastrarFuncionario", method = RequestMethod.POST)
	public ModelAndView salvarFuncionario(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("funcionario/funcionarioCadastrar");
		}
		funcionarioService.save(funcionario);
		attri.addFlashAttribute("messagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/funcionarios");
	}
	
	@RequestMapping(value="/funcionarios/{id}", method = RequestMethod.GET)
	public String deletar(@PathVariable("id") Long id, RedirectAttributes attri) {
		
		Funcionario funcionario = funcionarioService.buscar(id);
		
		funcionarioService.removerFuncionario(funcionario.getIdFuncionario());
		attri.addFlashAttribute("messagem", "Removido com sucesso!");
		
		return "redirect:/funcionarios";
	}
	
	@RequestMapping(value="/funcionarios/alterar/{id}", method = RequestMethod.GET)
	public ModelAndView buscaAlterar(@PathVariable("id") Long id, ModelMap model) {
		Funcionario funcionario = funcionarioService.buscar(id);
		model.addAttribute("funcionario", funcionario);
		return new ModelAndView("funcionario/funcionarioEditar", model);
	}
	
	@RequestMapping(value="/funcionario/alterar", method = RequestMethod.POST)
	public ModelAndView buscaAlterar(@ModelAttribute("funcionario") @Valid Funcionario funcionario, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()){
			
			return new ModelAndView("funcionario/funcionarioEditar");
		}
		funcionarioService.save(funcionario);
		attri.addFlashAttribute("messagem", "Alteração com realizado com sucesso!");
		return new ModelAndView("redirect:/funcionarios");
	}
	
	@RequestMapping(value="/funcionarios/nomeFuncionario", method = RequestMethod.GET)
	public ModelAndView buscarFornecedor(@ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.pesquisarFuncionario(funcionario.getNomeFuncionario()));
		return new ModelAndView("funcionario/pesquisaFuncionario", model);
	}
}
