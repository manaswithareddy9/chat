package com.manaswitha.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manaswitha.chat.entity.UserContact;

@Repository
@Transactional
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
    List<UserContact> findUserContactByUserId(Long userId);

    List<UserContact> deleteByUserId(Long userId);
}
