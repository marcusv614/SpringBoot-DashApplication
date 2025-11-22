package br.com.dash.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dash.app.entity.Usr;
import br.com.dash.app.service.UsrService;

@RestController
@CrossOrigin("*")
public class UsrController {
	@Autowired
	private UsrService usrService;

	public UsrController(UsrService usrService) {
		this.usrService = usrService;
	}

	@PostMapping("/cadastro")
	public ResponseEntity<Usr> criarUsr(@RequestBody Usr usr) {
		return ResponseEntity.status(201).body(usrService.criarUsr(usr));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Usr>> checarUsr() {
		return ResponseEntity.status(201).body(usrService.checarUsr());
	}
}
