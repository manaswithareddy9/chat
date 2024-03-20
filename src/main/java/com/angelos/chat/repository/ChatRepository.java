package com.angelos.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.angelos.chat.entity.Chat;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findChatByFromUserContactId(Long fromUserContactId);

    List<Chat> deleteByFromUserContactId(Long fromUserContactId);
}
