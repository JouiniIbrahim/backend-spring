package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Request.UserRoleDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.Mapper.UserMapper;

import com.example.e_learning.models.Role;
import com.example.e_learning.models.User;
import com.example.e_learning.repositories.RoleRepo;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.UserMapper.ToDto;
import static com.example.e_learning.Mapper.UserMapper.ToEntity;


@Service
public class UserSerImp  implements UserService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto AddUser(UserDto userDto) {
        // Fetch the "USER" role from the database
//        Role userRole = roleRepo.findByName("USER")
//                .orElseThrow(() -> new RuntimeException("Role 'USER' not found in the database."));


        String encodedPassword = encoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);


        User user = ToEntity(userDto);


        List<Role> roles = new ArrayList<>();
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            roles = roleRepo.findAllById(userDto.getRoles());
        }

        // Add the "USER" role if it's not already included
//        if (roles.stream().noneMatch(role -> role.getName().equals("USER"))) {
//            roles.add(userRole);
//        }

        user.setRoles(roles);

        User savedUser = userRepo.save(user);

        return ToDto(savedUser);
    }

    @Override
    public ResponseEntity<String> addRoleToUser(UserRoleDto userRoleDto) {

        User user = userRepo.findById(userRoleDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));


        Role role = roleRepo.findById(userRoleDto.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepo.save(user);
            return  ResponseEntity.ok("Role added to user");
        }else
            return ResponseEntity.ok("Role already exists");


    }
    @Override
    public UserResponseDto UpdateUser(Long id,UserDto updateUserDto) {
        // Find the existing user aor throw an exception if not found
        User existingUser = userRepo.findById(updateUserDto.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Update the user's basic information
        existingUser = ToEntity(updateUserDto);

        // Check if roleIds is null and handle it
        List<Long> roleIds = updateUserDto.getRoles();
        if (roleIds == null) {
            roleIds = Collections.emptyList(); // or throw an exception
        }

        // Fetch roles from the database based on role IDs
        List<Role> roles = roleRepo.findAllById(roleIds);

        // Set the roles for the user
        existingUser.setRoles(roles);

        // Save the updated user
        userRepo.save(existingUser);

        // Return the updated user as a DTO
        return ToDto(existingUser);
    }

    @Override
    public ResponseEntity<String> DeleteUser(Long id)
    {
        if(userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return  ResponseEntity.ok("User Deleted");
        }else return  ResponseEntity.ok("User Not Found");
    }

    @Override
    public List<UserResponseDto> GetAllUsers()
    {
        return userRepo.findAll().stream().map(UserMapper::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto GetUserById(Long id)
    {
        User user=userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found with id :"+ id));
        return ToDto(user);
    }




}
