package com.example.e_learning.security.services;


//import com.securityEcommerce.exception.TokenRefreshException;

import com.example.e_learning.models.RefrechToken;
import com.example.e_learning.repositories.RefrechTokenRepo;
import com.example.e_learning.repositories.UserRepo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
  @Value("${app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefrechTokenRepo refreshTokenRepository;

  @Autowired
  private UserRepo userRepository;

  public Optional<RefrechToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefrechToken createRefreshToken(Long userId) {
    RefrechToken refreshToken = new RefrechToken();

    refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefrechToken verifyExpiration(RefrechToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
     // throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
      throw new RuntimeException("failed");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}
