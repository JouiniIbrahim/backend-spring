package com.example.e_learning.repositories;

import com.example.e_learning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);
    Optional<User> findByUsername(String username);
}
