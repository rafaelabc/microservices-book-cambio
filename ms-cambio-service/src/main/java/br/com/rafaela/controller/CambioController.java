package br.com.rafaela.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaela.model.Cambio;
import br.com.rafaela.service.CambioService;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	
	
	@Autowired
	private CambioService service;
	
	@GetMapping(value="/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
							@PathVariable("from") String from,
							@PathVariable("to") String to) {
		
		
		return service.getCambio(from, to, amount);
	}
}
