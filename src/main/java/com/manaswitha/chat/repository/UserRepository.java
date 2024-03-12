package com.manaswitha.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manaswitha.chat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameContaining(String userName);

}
