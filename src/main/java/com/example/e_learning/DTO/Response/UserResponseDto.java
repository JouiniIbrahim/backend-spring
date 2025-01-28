package com.example.e_learning.DTO.Response;


import com.example.e_learning.DTO.Request.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    private List<RoleDto> roleIds;


}
