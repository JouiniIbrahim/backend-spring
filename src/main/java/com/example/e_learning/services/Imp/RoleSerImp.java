package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.Mapper.RoleMapper;
import com.example.e_learning.domain.Role;
import com.example.e_learning.repositories.RoleRepo;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.RoleMapper.*;


@Service
public class RoleSerImp implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public RoleResponseDto AddRole(RoleDto roleDto) {

//        List<User> users = userRepo.findAllById(roleDto.getUserIds());

        boolean roleExists = GetAllRoles().stream()
                .anyMatch(role ->( role.getName()).equals(roleDto.getName()));

        if (!roleExists) {

            Role role = ToEntity(roleDto);
//      role.setUsers(users);
            // Save the role
            Role savedRole = roleRepo.save(role);
            return   ToDto(savedRole);
        }
return null;
    }

    @Override
    public RoleResponseDto UpdateRole(RoleDto updateRoleDto) {

        Role existingRole = roleRepo.findById(updateRoleDto.getId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        existingRole.setName(updateRoleDto.getName());

        Role updatedRole = roleRepo.save(existingRole);
        return ToDto(updatedRole);
    }

    @Override
    public void DeleteRole(Long id) {
        if (!roleRepo.existsById(id)) {
            throw new RuntimeException("Role Not Found with id: " + id);
        }
        roleRepo.deleteById(id);
    }

    @Override
    public List<RoleResponseDto> GetAllRoles() {

        return roleRepo.findAll().stream()
                .map(RoleMapper::ToDtoDisplay)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto GetRoleById(Long id) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Role Not Found with id: " + id));
        return ToDtoDisplay(role);
    }
}