package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.services.Imp.RoleSerImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Role")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleController {
    @Autowired
    private RoleSerImp roleSerImp;

    @PostMapping("/AddRole")
    public RoleResponseDto addRole(@Valid @RequestBody RoleDto roleDto) {
        return roleSerImp.AddRole(roleDto);
    }

    @GetMapping("/AllRoles")
    public List<RoleResponseDto> getAllRoles() {
        return roleSerImp.GetAllRoles();
    }

    @PutMapping("/UpdateRole")
    public RoleResponseDto updateRole(@Valid @RequestBody RoleDto roleDto) {
        return roleSerImp.UpdateRole(roleDto);
    }


    @DeleteMapping("/DeleteRole")
    public ResponseEntity<String> deleteRole(@Valid @RequestBody RoleDto roleDto) {
        roleSerImp.DeleteRole(roleDto.getId());
        return ResponseEntity.ok("deleted successfully");
    }

    @GetMapping("/OneRole")
    public RoleResponseDto getOneRole(@Valid @RequestBody RoleDto roleDto) {
        return roleSerImp.GetRoleById(roleDto.getId());
    }

}
