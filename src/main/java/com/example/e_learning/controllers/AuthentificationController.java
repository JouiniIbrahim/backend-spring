package com.example.e_learning.controllers;

import com.example.e_learning.Payload.Request.LoginRequest;
import com.example.e_learning.Payload.Response.JwtResponse;
import com.example.e_learning.models.RefrechToken;
import com.example.e_learning.models.User;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.security.jwt.JwtUtils;
import com.example.e_learning.security.services.RefreshTokenService;
import com.example.e_learning.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthentificationController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    AuthenticationManager  authenticationManager;

    @PostMapping("/SignIn")
    public ResponseEntity<?> authenticateUser(@Valid  LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("step 1");
        Optional<User> user = userRepo.findByUsername(loginRequest.getUsername());
        System.out.println("step 2");
        if (user.get().isActivated() == true) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println("step 3");

            String jwt = jwtUtils.generateJwtToken(userDetails);
            System.out.println("step 4");
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            System.out.println("step 5");
            RefrechToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
            System.out.println("step 6");
            return ResponseEntity.ok(
                    new JwtResponse(
                            jwt,
                            "Bearer",
                            refreshToken.getToken(),
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            roles));
            //  (String token, String type, String refreshToken, Long id, String username, String email, List<String> roles)
        } else {
            return ResponseEntity.ok("User not confirmed");
//            throw new RuntimeException("user not confirmed");

        }
    }
        @GetMapping ("/SignOut")
                public ResponseEntity<?> signout()
        {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            Long userId=userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
            return ResponseEntity.ok("user is out");
        }

    }









