package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Request.UserRoleDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.services.Imp.UserSerImp;
import com.example.e_learning.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserSerImp userSerImp;
    @Autowired
    private UserService userService;


    @PostMapping("/AddUser")
    public UserResponseDto addUser(@Valid @RequestBody UserDto userDto)
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


    @GetMapping("/AllUsers")
    public List<UserResponseDto> getAllUsers() {
        return userSerImp.GetAllUsers();
    }







}
