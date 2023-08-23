package com.felepe.livraria.DTOS;

import java.time.LocalDate;

public class LoanDto {
	private LocalDate loanDate;
    private LocalDate returnDate;
    
    public LoanDto() {}

	public LoanDto(LocalDate loanDate, LocalDate returnDate) {
		super();
		this.loanDate = loanDate;
		this.returnDate = returnDate;
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
    
    

}
