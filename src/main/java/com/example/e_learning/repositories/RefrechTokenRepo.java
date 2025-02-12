package com.example.e_learning.repositories;

import com.example.e_learning.domain.RefrechToken;
import com.example.e_learning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefrechTokenRepo extends JpaRepository<RefrechToken,Long> {

    Optional<RefrechToken> findByToken(String token);
    int deleteByUser(User user);
}
