package com.example.e_learning.repositories;

import com.example.e_learning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    Optional<User> findByUsername(String username);

}
