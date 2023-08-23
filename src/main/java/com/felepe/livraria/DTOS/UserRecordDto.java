package com.felepe.livraria.DTOS;

import java.util.ArrayList;
import java.util.List;
public record UserRecordDto(String name, String email, String password, List<Long> idLoans) {
	  public UserRecordDto(String name, String email, String password) {
	        this(name, email, password, new ArrayList<>());	    }

	  public UserRecordDto(String name, String email, String password, List<Long> idLoans) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.idLoans = new ArrayList<>(idLoans);
	  }
	  
	}

