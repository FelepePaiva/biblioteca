package com.felepe.livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felepe.livraria.entities.Loan;
import com.felepe.livraria.entities.enums.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Long>{	
	 @Query("SELECT l.status FROM Loan l WHERE l.book.id = :id")
	    Optional<LoanStatus> findStatusByBookId(@Param("id") Long id);
}
