package com.example.e_learning.DTO.Request;



import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class RoleDto {
    private Long id;
    private String name;

    private List<Long> userIds;
}