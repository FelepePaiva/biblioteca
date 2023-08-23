package com.felepe.livraria.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felepe.livraria.entities.Books;
import com.felepe.livraria.entities.enums.LoanStatus;
import com.felepe.livraria.repositories.BooksRepository;
import com.felepe.livraria.services.LoanService;
import com.felepe.livraria.services.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	@Autowired
	private BooksRepository bookRepository;
	@Autowired
	private LoanService loanService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBook(@PathVariable(value = "id") Long id) {		
		try {
	        Optional<Books> book = loanService.availableBook(id);

	        if (book.isPresent()) {
	            return ResponseEntity.status(HttpStatus.OK).body(book.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	    }	
	}
	@PostMapping
	public ResponseEntity<Books> addBook(@RequestBody Books obj){
		bookRepository.save(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	@GetMapping(value = "/status/{id}")
	public ResponseEntity<Object> getStatus(@PathVariable(value = "id") Long id) {
	    try {
	        Optional<LoanStatus> status = loanService.getStatus(id);
	        if (status.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(status.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	    }
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> removeBookById(@PathVariable Long id){
		Optional<Books> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body("Book has been deleted");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Book not found");
	}
}
