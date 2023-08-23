package com.felepe.livraria.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.felepe.livraria.entities.enums.LoanStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "tb_loans")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate loanDate;
	private LocalDate returnDate;
	@Enumerated(EnumType.STRING)
	private LoanStatus status;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
    @JoinColumn(name = "book_id")
    private Books book;
	
	public Loan() {}

	public Loan(Long id, LocalDate loanDate, LocalDate returnDate, LoanStatus status, User user, Books book) {
		super();
		this.id = id;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.status = status;
		this.user = user;
		this.book = book;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		return Objects.equals(id, other.id);
	}

	
	
	

}
