package com.felepe.livraria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.felepe.livraria.entities.Books;
import com.felepe.livraria.entities.User;
import com.felepe.livraria.entities.enums.LoanStatus;
import com.felepe.livraria.services.LoanService;
@SpringBootTest
public class LoanServiceTest {
	@Autowired
    private LoanService loanService;

    @Test
    public void testLoanBook() {        
        Books book = new Books(1L, "aaa", "bbb", "ccc", 1111, "ddd", LoanStatus.PENDING);
        User user = new User(1L, "name", "email", "password");
        
        loanService.loanBook(book, user);
        System.out.println(loanService);
}
}
