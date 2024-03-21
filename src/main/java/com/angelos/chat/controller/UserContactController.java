package com.angelos.chat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.angelos.chat.entity.User;
import com.angelos.chat.entity.UserContact;
import com.angelos.chat.model.UserContactModel;
import com.angelos.chat.repository.UserContactRepository;
import com.angelos.chat.repository.UserRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class UserContactController {
	@Autowired
	private UserContactRepository userContactRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/users/{userId}/userContacts")
	public ResponseEntity<UserContactModel> createUserContact(@PathVariable(value = "userId") long userId,
			@RequestBody UserContactModel userContactRequest) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No user found with id = " + userId));
		UserContact userContact = userContactRepository.save(new UserContact(user, userContactRequest.getPhoneNumber(),
				userContactRequest.getEmailId()));
		UserContactModel userContactModel = new UserContactModel(userContact.getId(), userId,
				userContact.getPhoneNumber(), userContact.getEmailId(), userContact.getCreatedAt(),
				userContact.getUpdatedAt());
		return new ResponseEntity<>(userContactModel, HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}/userContacts")
	public ResponseEntity<List<UserContactModel>> getUserContactsByUserId(@PathVariable(value = "userId") long userId)
			throws ResourceNotFoundException {
		userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		List<UserContactModel> contacts = userContactRepository.findUserContactByUserId(userId).stream()
				.map(userContact -> {
					return new UserContactModel(userContact.getId(), userId, userContact.getPhoneNumber(),
							userContact.getEmailId(), userContact.getCreatedAt(), userContact.getUpdatedAt());
				}).collect(Collectors.toList());
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	@GetMapping("/userContacts/{id}")
	public ResponseEntity<UserContactModel> getUserContactById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		UserContact userContact = userContactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserContact not found for this id :: " + id));
		UserContactModel userContactModel = new UserContactModel(id, userContact.getUser().getId(),
				userContact.getPhoneNumber(), userContact.getEmailId(), userContact.getCreatedAt(),
				userContact.getUpdatedAt());
		return new ResponseEntity<>(userContactModel, HttpStatus.OK);
	}

	@PutMapping("/userContacts/{id}")
	public ResponseEntity<UserContactModel> updateUserContact(@PathVariable(value = "id") long userContactId,
			@Valid @RequestBody UserContactModel userContactRequest) throws ResourceNotFoundException {
		UserContact userContacts = userContactRepository.findById(userContactId).stream().findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(
						"UserContact not found for this userContactId :: " + userContactId));
		userContacts.setPhoneNumber(userContactRequest.getPhoneNumber());
		userContacts.setEmailId(userContactRequest.getEmailId());
		userContacts.setUpdatedAt(new Date());
		final UserContact updatedUserContact = userContactRepository.save(userContacts);
		UserContactModel userContactModel = new UserContactModel(userContactId, updatedUserContact.getUser().getId(),
				updatedUserContact.getPhoneNumber(), updatedUserContact.getEmailId(), updatedUserContact.getCreatedAt(),
				updatedUserContact.getUpdatedAt());
		return ResponseEntity.ok(userContactModel);
	}

	@DeleteMapping("/userContacts/{id}")
	public Map<String, Boolean> deleteUserContactById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		UserContact userContact = userContactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserContact not found for this id :: " + id));

		userContactRepository.delete(userContact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/users/{userId}/userContacts")
	public Map<String, Boolean> deleteAllUserContactsOfUser(@PathVariable(value = "userId") Long userId)
			throws ResourceNotFoundException {
		if (userContactRepository.findUserContactByUserId(userId).isEmpty()) {
			throw new ResourceNotFoundException("userContact not found for the userId = " + userId);
		}

		userContactRepository.deleteByUserId(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
