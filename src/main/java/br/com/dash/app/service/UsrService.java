package br.com.dash.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dash.app.entity.Usr;
import br.com.dash.app.repository.IUsr;

@Service
public class UsrService {

	private final IUsr repository;
	private final PasswordEncoder passwordEncoder;

	public UsrService(IUsr repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public Usr criarUsr(Usr usr) {
		String encoderSenha = this.passwordEncoder.encode(usr.getPwd());
		usr.setPwd(encoderSenha);
		return repository.save(usr);
	}

	@Transactional
	public Usr alterarUsr(Usr usr) {
		String encoderSenha = this.passwordEncoder.encode(usr.getPwd());
		usr.setPwd(encoderSenha);
		return repository.save(usr);
	}

	public List<Usr> checarUsr() {
		return repository.findAll();
	}

	public boolean validarSenha(String username, String pwd) {
		Optional<Usr> optional = repository.findByUsername(username);
		if (optional.isEmpty()) {
			return false; // ou lançar UsernameNotFoundException dependendo do fluxo
		}
		String senhaHash = optional.get().getPwd();
		return passwordEncoder.matches(pwd, senhaHash);
	}

	@Transactional
	public Optional<Usr> deleteUsr(Integer id) {
		Optional<Usr> usrOptional = repository.findById(id);
		usrOptional.ifPresent(u -> repository.deleteById(id));
		return usrOptional;
	}
}
