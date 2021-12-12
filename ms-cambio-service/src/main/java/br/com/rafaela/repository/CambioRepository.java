package br.com.rafaela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaela.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{
	Cambio findByFromAndTo(String from, String to);
}
