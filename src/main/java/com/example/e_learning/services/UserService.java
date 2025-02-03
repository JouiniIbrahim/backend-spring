package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Request.UserRoleDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public UserResponseDto AddUser(UserDto userDto);
    public List<UserResponseDto> GetAllUsers();
    public UserResponseDto GetUserById(Long id);
    public UserResponseDto UpdateUser(Long id,UserDto userDto);
    public ResponseEntity<String> DeleteUser(Long id);
    public ResponseEntity<String> addRoleToUser(UserRoleDto userRoleDto);

}
