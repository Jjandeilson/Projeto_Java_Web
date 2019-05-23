package br.com.web.spring.controller;


import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{


	@RequestMapping(value="/login")
	public ModelAndView login(@AuthenticationPrincipal @Valid User user) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("usuario", user);
		return model;
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView usuario(@AuthenticationPrincipal User user) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("usuario", user);
		System.out.println(user.getUsername()+" "+user.getAuthorities());
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logout() {
		return "login";
	}
	
}
