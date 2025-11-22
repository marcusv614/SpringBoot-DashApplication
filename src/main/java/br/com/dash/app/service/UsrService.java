package br.com.dash.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dash.app.entity.Usr;
import br.com.dash.app.repository.IUsr;

@Service
public class UsrService {
	public IUsr repository;

	public UsrService(IUsr repository) {
		this.repository = repository;
	}

	public Usr criarUsr(Usr usr) {
		repository.save(usr);
		return usr;
	}

	public List<Usr> checarUsr() {
		List<Usr> lista = repository.findAll();
		return lista;
	}
}
