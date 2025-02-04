package com.example.e_learning.controllers;


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
import jakarta.validation.Valid;
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


    private JavaMailSender javaMailSender;


    @GetMapping("/Confirm")
    public ResponseEntity<?> confirm( @RequestParam String email) {
        // Create new user's account
        User user = userRepo.findFirstByEmail(email);
        if(user != null){
            user.setActivated(true);
            userRepo.save(user);
            return ResponseEntity.ok("User is confirmed");
        }
        return ResponseEntity.ok("User is Not confirmed");
    }

    @PostMapping("/CreateUser1")


    public Map<String,String> adduser(@RequestParam SignUpRequest sn) throws MessagingException {

        UserDto user = new UserDto (
                sn.getUsername(),
                sn.getEmail(),
                encoder.encode(sn.getPassword()),
                sn.getRole());




        userSerImp.AddUser(user);
        // configuration email

        String to=user.getEmail();
        String from="uywt@gmail.com ";
        String content="please check your email";
        String subject="Success to create your compte";
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(" <HTML> <Body>" + content+" <a href=http://localhost:8085/User/Confirm?email="
                + user.getEmail() + ">VERIFY</a></body></HTML>", true);
        javaMailSender.send(message);
        Map result = new HashMap();
        result.put("Your compte is created ! verify your email", "Success");
//         return  ResponseEntity.ok("Your compte is created and verified your email");
        return result;
    }
    @PostMapping("/AddUser")
    public UserResponseDto addUser(@RequestBody UserDto userDto)
    {
        return userSerImp.AddUser(userDto);
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
