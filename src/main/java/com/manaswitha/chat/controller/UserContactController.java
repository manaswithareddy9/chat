package com.manaswitha.chat.controller;

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

import com.manaswitha.chat.ResourceNotFoundException;
import com.manaswitha.chat.entity.User;
import com.manaswitha.chat.entity.UserContact;
import com.manaswitha.chat.model.UserContactModel;
import com.manaswitha.chat.repository.UserContactRepository;
import com.manaswitha.chat.repository.UserRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class UserContactController {
	@Autowired
	private UserContactRepository userContactRepository;

	@Autowired
	private UserRepository userRepository;

	// to do: query param tpo fetch cpontacts by userid

	@GetMapping("/userContacts")
	public List<UserContact> getAllUserContacts() {
		return userContactRepository.findAll();
	}

	@GetMapping("/users/{userId}/userContacts")
	public ResponseEntity<List<UserContact>> getUserContactsByUserId(@PathVariable(value = "userId") long userId)
			throws ResourceNotFoundException {
		userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		List<UserContact> contacts = userContactRepository.findByUserId(userId);
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

	@PostMapping("/users/{userId}/userContacts")
	public ResponseEntity<UserContactModel> createUserContact(@PathVariable(value = "userId") Long userId,
			@RequestBody UserContact userContactRequest) throws ResourceNotFoundException {
		UserContact userContact = userRepository.findById(userId).map(user -> {
			userContactRequest.setUser(user);
			return userContactRepository.save(userContactRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("No user found with id = " + userId));
		UserContactModel userContactModel = new UserContactModel(userContact.getUser().getId(), userId,
				userContact.getPhoneNumber(), userContact.getEmailId(), userContact.getCreatedAt(),
				userContact.getUpdatedAt());
		// userContact.setUserId(userId);
		return new ResponseEntity<>(userContactModel, HttpStatus.CREATED);
	}

	@PutMapping("/userContacts/{userId}")
	public ResponseEntity<UserContactModel> updateUserContact(@PathVariable(value = "userId") long userId,
			@Valid @RequestBody UserContact userContactDetails) throws ResourceNotFoundException {
		UserContact userContacts = userContactRepository.findByUserId(userId).stream().findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("UserContact not found for this userId :: " + userId));

		userContacts.setUser(userContactDetails.getUser());
		userContacts.setPhoneNumber(userContactDetails.getPhoneNumber());
		userContacts.setEmailId(userContactDetails.getEmailId());
		userContacts.setUpdatedAt(new Date());
		// userContacts.setUserId(userId);
		final UserContact updatedUserContact = userContactRepository.save(userContacts);
		UserContactModel userContactModel = new UserContactModel(updatedUserContact.getUser().getId(), userId,
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
