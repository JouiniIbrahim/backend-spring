package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.Mapper.UserMapper;

import com.example.e_learning.models.Role;
import com.example.e_learning.models.User;
import com.example.e_learning.repositories.RoleRepo;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.UserMapper.ToDto;
import static com.example.e_learning.Mapper.UserMapper.ToEntity;


@Service
public class UserSerImp  implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserResponseDto AddUser(UserDto userDto) {

        // Save the usetr
       userDto.setRoleIds((roleRepo.findAll().stream().map(Role::getId).collect(Collectors.toList())));
        User user=ToEntity(userDto);
        user.setRoles();


        User savedUser = userRepo.save(user);

        // Map User entity to User responseDto
        return ToDto(savedUser);
    }
    @Override
    public UserResponseDto UpdateUser(UserDto updateUserDto)
    {
        User exsitingUser=userRepo.findById(updateUserDto.getId())
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        exsitingUser=ToEntity(updateUserDto);
        userRepo.save(exsitingUser);

        return ToDto(exsitingUser);
    }

    @Override
    public void DeleteUser(Long id)
    {
        userRepo.deleteById(id);
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
