package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
  RoleResponseDto AddRole(RoleDto roleDto);
    RoleResponseDto UpdateRole(RoleDto updateRoleDto);
    void DeleteRole(Long id);
    List<RoleResponseDto> GetAllRoles();
    RoleResponseDto GetRoleById(Long id);


}
