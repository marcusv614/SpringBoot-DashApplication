package br.com.dash.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dash.app.entity.Usr;

public interface IUsr extends JpaRepository<Usr, Integer> {

}
