package com.felepe.livraria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felepe.livraria.entities.Books;
import com.felepe.livraria.repositories.BooksRepository;
import com.felepe.livraria.services.exception.ResourceNotFoundException;

@Service
public class BooksService {
	private BooksRepository booksRepository;
	@Autowired
	public BooksService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}	
	public void insertBook (Books obj) {
		booksRepository.save(obj);
	}
	public void deleteBookById(Long id) {
		if (!booksRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		booksRepository.deleteById(id);
	}
	public void updateBook(Long id, Books obj) {
		if (!booksRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		Books existingBook = booksRepository.findById(id).orElse(null);
		if (existingBook != null) {
			existingBook.setTitle(obj.getTitle());
			existingBook.setAuthor(obj.getAuthor());
			existingBook.setPublishing(obj.getPublishing());
			existingBook.setGenre(obj.getGenre());
			existingBook.setYear(obj.getYear());			
			booksRepository.save(existingBook);
		}
		
	}
}
