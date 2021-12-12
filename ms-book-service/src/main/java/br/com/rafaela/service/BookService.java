package br.com.rafaela.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rafaela.model.Book;
import br.com.rafaela.proxy.CambioProxy;
import br.com.rafaela.repository.BookRepository;
import br.com.rafaela.response.Cambio;

@Service
public class BookService {

	@Autowired
	private Environment enviroment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	public Book findBook(Long id, String currency) {
		Book book = repository.getById(id);
		if(book == null) {
			throw new RuntimeException("Book not found");
		}
		
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		String port = enviroment.getProperty("local.server.port");
		book.setEnvironment("Book port: " + port + "\n Cambio port: " + cambio.getEnviroment());
		book.setPrice(cambio.getConvertedValue());
		return book;
	}
	
}
