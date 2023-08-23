package com.felepe.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felepe.livraria.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
