package com.felepe.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felepe.livraria.entities.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
	static void insertBook(Books obj){		
	}
	static void updateBook (Books obj) {		
	}
}
