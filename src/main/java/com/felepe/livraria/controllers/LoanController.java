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

import com.felepe.livraria.DTOS.LoanDto;
import com.felepe.livraria.entities.Books;
import com.felepe.livraria.entities.Loan;
import com.felepe.livraria.entities.User;
import com.felepe.livraria.repositories.BooksRepository;
import com.felepe.livraria.repositories.LoanRepository;
import com.felepe.livraria.repositories.UserRepository;
import com.felepe.livraria.services.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/loans")
public class LoanController {
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BooksRepository booksRepository;

	@PostMapping
	public ResponseEntity<Loan> addLoan(@RequestBody Loan obj) {
		User user = userRepository.findById(obj.getUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Books book = booksRepository.findById(obj.getBook().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		obj.setUser(user);
		obj.setBook(book);
		loanRepository.save(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteLoanById(@PathVariable Long id){
		loanRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Loan has been deleted!");
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getLoanById (@PathVariable Long id){
		Optional<Loan> loanOptional = loanRepository.findById(id);
		if (loanOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found");
		}
		 Loan loan = loanOptional.get();
		    
		    LoanDto dto = new LoanDto();
		    
		    dto.setLoanDate(loan.getLoanDate());
		    dto.setReturnDate(loan.getReturnDate());
		    
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
