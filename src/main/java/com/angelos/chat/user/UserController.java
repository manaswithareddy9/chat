<<<<<<< HEAD:src/main/java/com/angelos/chat/controller/UserController.java
package com.angelos.chat.controller;
=======
package com.angelos.chat.user;
>>>>>>> d553a0fa00fcfe9f7f6be84cbe06bebd3befb45d:src/main/java/com/angelos/chat/user/UserController.java

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelos.chat.ResourceNotFoundException;
<<<<<<< HEAD:src/main/java/com/angelos/chat/controller/UserController.java
import com.angelos.chat.entity.User;
import com.angelos.chat.repository.UserRepository;
=======
>>>>>>> d553a0fa00fcfe9f7f6be84cbe06bebd3befb45d:src/main/java/com/angelos/chat/user/UserController.java

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User users = userRepository
				.save(user);
		return new ResponseEntity<>(users, HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User users = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		users.setFirstName(userDetails.getFirstName());
		users.setLastName(userDetails.getLastName());
		users.setFullName(userDetails.getFullName());
		users.setUserName(userDetails.getUserName());
		users.setUpdatedAt(new Date());
		final User updatedUser = userRepository.save(users);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
