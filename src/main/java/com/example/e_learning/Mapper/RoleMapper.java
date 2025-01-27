package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.models.Role;

public class RoleMapper {

    public RoleDto ToDto(Role role) {
        RoleDto responseDto = new RoleDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getName());
        return responseDto;
    }

    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
