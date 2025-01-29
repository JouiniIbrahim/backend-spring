package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.RoleDto;

import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.models.Role;
import com.example.e_learning.models.User;


import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleResponseDto ToDto(Role role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getName());
//            responseDto.setUserIds(role.getUsers().stream()
//                    .map(User::getId)
//                    .collect(Collectors.toList()));
        return responseDto;
    }

    public static RoleResponseDto ToDtoDisplay(Role role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getName());
        responseDto.setUserIds(role.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }


    public static Role ToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }
}
