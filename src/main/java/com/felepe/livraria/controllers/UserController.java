package com.felepe.livraria.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felepe.livraria.DTOS.UserDto;
import com.felepe.livraria.DTOS.UserRecordDto;
import com.felepe.livraria.entities.User;
import com.felepe.livraria.repositories.UserRepository;
import com.felepe.livraria.services.UserService;
import com.felepe.livraria.services.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			UserRecordDto dto = new UserRecordDto(user.getName(), user.getEmail(), user.getPassword());
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody User obj) {
		userRepository.save(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> removeUserById(@PathVariable Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User has been deleted!");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
		try {
			User userToUpdate = new User();
			userToUpdate.setId(id);
			userToUpdate.setName(userDto.getName());
			userToUpdate.setEmail(userDto.getEmail());
			userToUpdate.setPassword(userDto.getPassword());

			userService.updateUser(id, userToUpdate);

			return ResponseEntity.ok("User updated successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
