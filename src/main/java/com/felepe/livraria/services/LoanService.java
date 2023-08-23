	package com.felepe.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felepe.livraria.entities.Books;
import com.felepe.livraria.entities.Loan;
import com.felepe.livraria.entities.User;
import com.felepe.livraria.entities.enums.LoanStatus;
import com.felepe.livraria.repositories.BooksRepository;
import com.felepe.livraria.repositories.LoanRepository;
import com.felepe.livraria.services.exception.ResourceNotFoundException;

@Service
public class LoanService {
	private LoanRepository loanRepository;	
	private BooksRepository bookRepository;
		
	@Autowired
	public LoanService(LoanRepository loanRepository, BooksRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.bookRepository = bookRepository;
	}
	public LoanService() {	
	}
	
	public void loanBook (Books book, User user) {
		Long bookId = book.getId();
		Long userId = user.getId();
		if (!loanRepository.existsById(bookId) || !loanRepository.existsById(userId)) {
			throw new ResourceNotFoundException("Book id or User id not found!");
		}
		Optional<Loan> bookLoan = loanRepository.findById(bookId);
		Optional<Loan> userLoan = loanRepository.findById(userId); 
		
		if (bookLoan.isPresent() && userLoan.isPresent()) {
			Loan bookLoanRecord = bookLoan.get();
			Loan userLoanRecord = userLoan.get();
			
			bookLoanRecord.setStatus(LoanStatus.LOANEAD);
			loanRepository.save(bookLoanRecord);
			
			userLoanRecord.setBook(book);
			userLoanRecord.setStatus(LoanStatus.LOANEAD);
			loanRepository.save(userLoanRecord);
		}
	}
	public void returnBook(Books book, User user) {
		Long bookId = book.getId();
		Long userId = user.getId();
		if (!loanRepository.existsById(bookId) || !loanRepository.existsById(userId)) {
			throw new ResourceNotFoundException("Book id or User id not found!");
		}
		Optional<Loan> bookLoan = loanRepository.findById(bookId);
		Optional<Loan> userLoan = loanRepository.findById(userId);
		if (bookLoan.isPresent() && userLoan.isPresent()) {
			Loan bookLoanRecord = bookLoan.get();
			Loan userLoanRecord = userLoan.get();
			
			bookLoanRecord.setStatus(LoanStatus.AVALIABLE);
			loanRepository.save(bookLoanRecord);
			
			userLoanRecord.setBook(book);
			userLoanRecord.setStatus(LoanStatus.AVALIABLE);
			loanRepository.save(userLoanRecord);
		}
	}
	public Optional<Books> availableBook(Long id) {
	    Optional<Books> bookId = bookRepository.findById(id);
	    
	    if (bookId.isPresent()) {
	        Books book = bookId.get();
	        LoanStatus status = book.getStatus();
	        
	        if (status == LoanStatus.AVALIABLE) {
	            return Optional.of(book);
	        } else {
	            throw new ResourceNotFoundException("The book is not available for loan");
	        }        
	    } else {
	        throw new ResourceNotFoundException("ID not found");
	    }
	}
	public Optional<LoanStatus> getStatus(Long id) {
	    Optional<Loan> loanStatusOptional = loanRepository.findById(id);
	    
	    if (loanStatusOptional.isPresent()) {
	    	LoanStatus loanStatus = loanStatusOptional.get().getStatus();
	    	return Optional.of(loanStatus);
	    } else {
	        throw new ResourceNotFoundException(id);
	    }
	}
	
	  	
	
}
