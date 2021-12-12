package br.com.rafaela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaela.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
