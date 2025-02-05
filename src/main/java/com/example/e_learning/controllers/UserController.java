package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Request.ConfirmRequest;
import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Request.UserRoleDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.Payload.Request.SignUpRequest;
import com.example.e_learning.models.User;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.services.Imp.UserSerImp;
import com.example.e_learning.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserSerImp userSerImp;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;


    @Autowired
    private JavaMailSender javaMailSender;


    @PutMapping("/Confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmRequest confirmRequest) {
 System.out.println(confirmRequest);
        User user = userRepo.findUserByEmail(confirmRequest.getEmail());
        System.out.println(user);

        Map<String, String> response = new HashMap<>();


        if (user.getActivation_key().equals(confirmRequest.getActivation_key()) ) {
            user.setActivated(true);
            user.setActivation_key(null);
            user.setPassword(encoder.encode(confirmRequest.getPassword()));
            userRepo.save(user);

            response.put("message", "Account is activated");
            return ResponseEntity.ok(response);
        }

        response.put("message", "Account is not activated");
        return ResponseEntity.ok(response);
    }


    //    @PostMapping("/CreateUser1")
//    public Map<String, String> adduser(@RequestBody SignUpRequest sn) throws MessagingException {
//
//        UserDto user = new UserDto(
//                sn.getUsername(),
//                sn.getEmail(),
//                sn.getPassword(),
//                sn.getRole()
//
//        );
//
//
//        userSerImp.AddUser(user);
//
//        // Send the confirmation email
//        String to = user.getEmail();
//        String from = "uywt@gmail.com";
//        String content = "Please check your email for verification!";
//        String subject = "Success! Account Created";
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
//        messageHelper.setFrom(from);
//        messageHelper.setTo(to);
//        messageHelper.setSubject(subject);
//        messageHelper.setText("<HTML><Body>" + content +
//                "<a href=http://localhost:8085/User/Confirm?email=" + user.getEmail() + ">VERIFY</a></body></HTML>", true);
//
//        // Send the email
//        javaMailSender.send(message);
//
//        // Return a response map with the success message
//        Map<String, String> result = new HashMap<>();
//        result.put("message", "Your account has been created! Please verify your email.");
//
//        return result;
//    }
    @PostMapping("/AddUser")
    public Map<String, String> addUser(@RequestBody UserDto userDto) throws MessagingException
    {

          userSerImp.AddUser(userDto);

        String to = userDto.getEmail();
        String Act_Key = userDto.getActivation_key();
        String from = "uywt@gmail.com";
        String content = "Please check your email for verification!    ";
        String subject = "Success! Account Created";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
//        messageHelper.setText("<HTML><Body>" + content +
//                "<a href=http://localhost:8085/User/Confirm?email=" + userDto.getEmail() +    ">VERIFY</a></body></HTML>", true);
        messageHelper.setText("<HTML><Body>" + content +
                "<a href=http://localhost:4200/ConfirmAccount?act_key=" + userDto.getActivation_key()+ "> VERIFY</a></body></HTML>", true);
        // Send the email
        javaMailSender.send(message);


        // Return a response map with the success message
        Map<String, String> result = new HashMap<>();
        result.put("message", "Your account has been created! Please verify your email.");
        return  result;
    }


    @PostMapping("/AssignRole")
    public ResponseEntity<String> addRoleToUser(@RequestBody UserRoleDto userRoleDto) {
        try {

           return userSerImp.addRoleToUser(userRoleDto);


        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (id == null || userDto == null) {
            throw new IllegalArgumentException("UserDto or ID must not be null");
        }

        UserResponseDto updatedUser = userService.UpdateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/AllUsers")
    public List<UserResponseDto> getAllUsers() {
        return userSerImp.GetAllUsers();
    }


    @DeleteMapping("/DeleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto) {
        return userSerImp.DeleteUser(userDto.getId());
    }

    @GetMapping("/OneUser/{id}")
    public UserResponseDto getOneUser(@PathVariable Long id) {

        return userSerImp.GetUserById(id);
    }







}
