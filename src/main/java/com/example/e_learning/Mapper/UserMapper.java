package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.models.Role;
import com.example.e_learning.models.User;


import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {



    public static UserResponseDto ToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstname(user.getFirstname());
        responseDto.setLastname(user.getLastname());
        responseDto.setEmail(user.getEmail());
        responseDto.setUsername(user.getUsername());
        responseDto.setPassword(user.getPassword());
        List<RoleResponseDto> roleResponseDtos = user.getRoles().stream()
                .map(role -> new RoleResponseDto(role.getId(), role.getName(), role.getUsers().stream()
                        .map(User::getId)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

        responseDto.setRoles(roleResponseDtos);
        return responseDto;
    }

    public static User ToEntity(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        return user;

    }

}
