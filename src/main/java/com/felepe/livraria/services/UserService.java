package com.felepe.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felepe.livraria.entities.User;
import com.felepe.livraria.repositories.UserRepository;
import com.felepe.livraria.services.exception.ResourceNotFoundException;
@Service
public class UserService {
	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void insertUser(User obj) {
		userRepository.save(obj);
	}
	public void deleteUserById(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		userRepository.deleteById(id);		
	}
	public void updateUser(Long id, User obj) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			existingUser.setName(obj.getName());
			existingUser.setEmail(obj.getEmail());
			existingUser.setPassword(obj.getPassword());
			userRepository.save(existingUser);
		}
	}
	public Optional<User> getUserById (Long id){
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user;
		}
		throw new ResourceNotFoundException(id);
	}

}
