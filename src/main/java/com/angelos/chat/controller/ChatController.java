package com.angelos.chat.controller;

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
import com.angelos.chat.entity.Chat;
import com.angelos.chat.entity.UserContact;
import com.angelos.chat.model.ChatModel;
import com.angelos.chat.repository.ChatRepository;
import com.angelos.chat.repository.UserContactRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class ChatController {
	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserContactRepository userContactRepository;

	@PostMapping("/chats")
	public ResponseEntity<ChatModel> createUserChat(@RequestBody ChatModel chatRequest)
			throws ResourceNotFoundException {
		UserContact fromUserContact = userContactRepository.findById(chatRequest.getFromUserContactId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"No from-userContact found with UserContactid = " + chatRequest.getFromUserContactId()));
		UserContact toUserContact = userContactRepository.findById(chatRequest.getToUserContactId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"No to-userContact found with UserContactid = " + chatRequest.getToUserContactId()));

		Chat chat = chatRepository.save(new Chat(fromUserContact, toUserContact, chatRequest.getText()));

		ChatModel chatModel = new ChatModel(chat.getId(), fromUserContact.getId(),
				toUserContact.getId(), chat.getText(), chat.getCreatedAt(),
				chat.getUpdatedAt());
		return new ResponseEntity<>(chatModel, HttpStatus.CREATED);
	}

	// TODO: query param to fetch contacts by userid
	@GetMapping("/chats")
	public List<Chat> getAllUserChats() {
		return chatRepository.findAll();
	}

	@GetMapping("/userContacts/{fromUserContactId}/chats")
	public ResponseEntity<List<ChatModel>> getChatsByFromUserContactId(
			@PathVariable(value = "fromUserContactId") long fromUserContactId)
			throws ResourceNotFoundException {
		userContactRepository.findById(fromUserContactId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"UserContacts not found from this id :: " + fromUserContactId));
		List<ChatModel> chats = chatRepository.findChatByFromUserContactId(fromUserContactId).stream()
				.map(chat -> {
					return new ChatModel(chat.getId(), chat.getFromUserContact().getId(),
							chat.getToUserContact().getId(), chat.getText(), chat.getCreatedAt(),
							chat.getUpdatedAt());
				}).toList();
		return new ResponseEntity<>(chats, HttpStatus.OK);
	}

	@GetMapping("/chats/{id}")
	public ResponseEntity<ChatModel> getChatById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		Chat chat = chatRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chat not found for this id :: " + id));
		ChatModel chatModel = new ChatModel(id,
				chat.getFromUserContact().getId(), chat.getToUserContact().getId(), chat.getText(), chat.getCreatedAt(),
				chat.getUpdatedAt());
		return new ResponseEntity<>(chatModel, HttpStatus.OK);
	}

	@PutMapping("/chats/{id}")
	public ResponseEntity<ChatModel> updateChat(@PathVariable(value = "id") long chatId,
			@Valid @RequestBody ChatModel chatRequest) throws ResourceNotFoundException {
		Chat chat = chatRepository.findById(chatId).stream().findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(
						"Chat not found for this Id :: " + chatId));
		// we will update chat text & updatedAt
		chat.setText(chatRequest.getText());
		chat.setUpdatedAt(new Date());

		final Chat updatedChat = chatRepository.save(chat);
		ChatModel chatModel = new ChatModel(updatedChat.getId(), updatedChat.getFromUserContact().getId(),
				updatedChat.getToUserContact().getId(), updatedChat.getText(), updatedChat.getCreatedAt(),
				updatedChat.getUpdatedAt());
		return ResponseEntity.ok(chatModel);
	}

	@DeleteMapping("/chats/{id}")
	public Map<String, Boolean> deleteChatById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		Chat chat = chatRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chat not found for this id :: " + id));

		chatRepository.delete(chat);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/userContacts/{fromUserContactId}/chats")
	public Map<String, Boolean> deleteAllUserContactsOfUser(
			@PathVariable(value = "fromUserContactId") Long fromUserContactId)
			throws ResourceNotFoundException {
		if (chatRepository.findChatByFromUserContactId(fromUserContactId).isEmpty()) {
			throw new ResourceNotFoundException("Chat not found for the fromUserContactId = "
					+ fromUserContactId);
		}

		chatRepository.deleteByFromUserContactId(fromUserContactId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
