package com.manaswitha.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manaswitha.chat.entity.Chat;
import com.manaswitha.chat.entity.UserContact;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, Long> {
    // List<Chat> findByUserId(Long userId);

    // List<Chat> findUserContactByUserId(Long userId);

    // List<Chat> deleteByUserId(Long userId);
}
