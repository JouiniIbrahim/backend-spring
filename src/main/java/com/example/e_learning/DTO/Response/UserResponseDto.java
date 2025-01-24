package com.example.e_learning.DTO.Response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
