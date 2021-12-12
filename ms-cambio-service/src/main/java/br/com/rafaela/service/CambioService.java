package br.com.rafaela.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.rafaela.model.Cambio;
import br.com.rafaela.repository.CambioRepository;

@Service
public class CambioService {

	@Autowired
	private CambioRepository repository;
	@Autowired
	private Environment enviroment;

	public Cambio getCambio(String from, String to, BigDecimal amount) {
		Cambio cambio = repository.findByFromAndTo(from, to);
		
		if(cambio == null) {
			throw new RuntimeException("Currency Unsupported");
		}

		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		
		cambio.setEnviroment(enviroment.getProperty("local.server.port"));
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		return cambio;
	}
	
}
