package br.com.dash.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.dash.app.service.UsrService;

@Controller
public class PageController {
	@Autowired
	public UsrService usrService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}

	@GetMapping("/usuarios")
	public String listarUsr(Model model) {
		model.addAttribute("usuarios", usrService.checarUsr());
		return "usuarios";
	}

}
