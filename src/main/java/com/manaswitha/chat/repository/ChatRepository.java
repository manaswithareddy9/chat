package com.manaswitha.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manaswitha.chat.entity.Chat;
import com.manaswitha.chat.model.ChatModel;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<ChatModel> findChatByFromUserContactId(Long fromUserContactId);

    List<ChatModel> deleteByFromUserContactId(Long fromUserContactId);
}
