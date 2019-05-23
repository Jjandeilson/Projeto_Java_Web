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

import br.com.web.spring.model.Fornecedor;
import br.com.web.spring.service.FornecedorService;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	
	@RequestMapping(value="/fornecedores", method = RequestMethod.GET)
	public ModelAndView principalProduto() {
		ModelAndView model = new ModelAndView("fornecedor/principalFornecedor");
		List<Fornecedor> fornecedores = fornecedorService.listarFornecedor();
		model.addObject("fornecedores", fornecedores);
		return model;
	}
	
	@RequestMapping("/cadastrarFornecedor")
	public String cadastraProduto(@ModelAttribute("fornecedor") Fornecedor fornecedor, ModelMap model ) {
		return "fornecedor/cadastrarFornecedor";
	}
	
	@RequestMapping(value="/cadastrarFornecedor/salvar", method = RequestMethod.POST)
	public ModelAndView salvarProduto(@Valid @ModelAttribute("fornecedor") Fornecedor fornecedor, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("fornecedor/cadastrarFornecedor");
		}
		fornecedorService.save(fornecedor);
		attri.addFlashAttribute("messagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedores");
	}
	
	@RequestMapping(value="/fornecedores/{id}", method = RequestMethod.GET)
	public String deletar(@PathVariable("id") Long id, RedirectAttributes attri) {
		Fornecedor fornecedor = fornecedorService.buscar(id);
		
		fornecedorService.removerFornecedor(fornecedor.getIdFornecedor());
		attri.addFlashAttribute("messagem", "Removido com sucesso!");
		return "redirect:/fornecedores";
	}
	
	@RequestMapping(value="/fornecedor/alterar/{id}", method = RequestMethod.GET)
	public ModelAndView buscaAlterar(@PathVariable("id") Long id, ModelMap model) {
		Fornecedor fornecedor = fornecedorService.buscar(id);
		model.addAttribute("fornecedor", fornecedor);
		return new ModelAndView("fornecedor/editarFornecedor", model);
	}
	
	@RequestMapping(value="/fornecedor/alterar", method = RequestMethod.POST)
	public ModelAndView buscaAlterar(@ModelAttribute("fornecedor") @Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("fornecedor/editarFornecedor") ;
		}
		fornecedorService.save(fornecedor);
		attri.addFlashAttribute("messagem", "Alterado com sucesso!");
		return new ModelAndView("redirect:/fornecedores");
	}
	
	@RequestMapping(value="/fornecedores/nomeFonecedor", method = RequestMethod.GET)
	public ModelAndView buscarFornecedor(@ModelAttribute("fornecedor") Fornecedor fornecedor, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorService.pesquisarFornecedor(fornecedor.getNomeFornecedor()));
		return new ModelAndView("fornecedor/pesquisaFornecedor", model);
	}
}
