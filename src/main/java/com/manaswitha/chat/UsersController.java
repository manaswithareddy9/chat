package com.manaswitha.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable(value = "id") Long usersId)
            throws ResourceNotFoundException {
                Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));
		return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users")
	public Users createUsers(@Valid @RequestBody Users users) {
		return usersRepository.save(users);
	}

    @PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Long usersId,
			@Valid @RequestBody Users usersDetails) throws ResourceNotFoundException {
		Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));

		users.setFirst_name(usersDetails.getFirst_name());
		users.setLast_name(usersDetails.getLast_name());
		users.setFull_name(usersDetails.getFull_name());
        users.setUser_name(usersDetails.getUser_name());
		final Users updatedUsers = usersRepository.save(users);
		return ResponseEntity.ok(updatedUsers);
	}

    @DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUsers(@PathVariable(value = "id") Long usersId)
			throws ResourceNotFoundException {
		Users users = usersRepository.findById(usersId)
				.orElseThrow(() -> new ResourceNotFoundException("Users not found for this id :: " + usersId));

		usersRepository.delete(users);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
