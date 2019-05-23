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

import br.com.web.spring.model.Produto;
import br.com.web.spring.service.ProdutoService;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoduService;

	@RequestMapping(value="/produtos", method = RequestMethod.GET)
	public ModelAndView principalProduto() {
		ModelAndView model = new ModelAndView("produto/produtoPrincipal");
		List<Produto> produtos = produtoduService.listarProduto();
		model.addObject("produtos", produtos);
		return model;
	}
	
	@RequestMapping("/cadastrarProduto")
	public String cadastraProduto(@ModelAttribute("produto") Produto produto, ModelMap model) {
		return "produto/produtoCadastrar";
	}
	
	@RequestMapping(value="/cadastrarProduto", method = RequestMethod.POST)
	public ModelAndView salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("produto/produtoCadastrar");
		}
		produtoduService.save(produto);
		attri.addFlashAttribute("messagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping(value="/produtos/{id}", method = RequestMethod.GET)
	public String deletar(@PathVariable("id") Long id, RedirectAttributes att) {
		Produto produto = produtoduService.buscar(id);
		
		produtoduService.removerProduto(produto.getIdProduto());
		att.addFlashAttribute("messagem", "Removido com sucesso");
		return "redirect:/produtos";
	}
	
	@RequestMapping(value="/alterar/{id}", method = RequestMethod.GET)
	public ModelAndView buscaAlterar(@PathVariable("id") Long id, ModelMap model) {
		Produto produto = produtoduService.buscar(id);
		model.addAttribute("produto", produto);
		return new ModelAndView("produto/produtoEditar", model);
	}
	
	@RequestMapping(value="/alterar", method = RequestMethod.POST)
	public ModelAndView buscarAlterar(@ModelAttribute("produto") @Valid Produto produto, BindingResult result, RedirectAttributes attri) {
		if(result.hasErrors()) {
			return new ModelAndView("produto/produtoEditar");
		}
		produtoduService.save(produto);
		attri.addFlashAttribute("messagem", "Alterado com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping(value="/produtos/nomeProduto", method = RequestMethod.GET)
	public ModelAndView buscarFornecedor(@ModelAttribute("produto") Produto produto, ModelMap model) {
		model.addAttribute("produtos", produtoduService.pesquisarProduto(produto.getNomeProduto()));
		return new ModelAndView("produto/pesquisaProduto", model);
	}
	
}
