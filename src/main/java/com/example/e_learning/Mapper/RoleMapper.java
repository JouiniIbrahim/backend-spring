package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.models.Role;
import com.example.e_learning.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public RoleDto ToDto(Role role) {
        RoleDto responseDto = new RoleDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getName());
        if (role.getUsers() != null) {
            List<Long> users = role.getUsers()
                    .stream()
                    .map(User::getId)
                    .collect(Collectors.toList());

            responseDto.setUsers(users);

        }
        return responseDto;
    }

    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }
}
