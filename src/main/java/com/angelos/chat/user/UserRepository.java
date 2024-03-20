<<<<<<< HEAD:src/main/java/com/angelos/chat/repository/UserRepository.java
package com.angelos.chat.repository;

import java.util.List;
=======
package com.angelos.chat.user;
>>>>>>> d553a0fa00fcfe9f7f6be84cbe06bebd3befb45d:src/main/java/com/angelos/chat/user/UserRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.chat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameContaining(String userName);

}
