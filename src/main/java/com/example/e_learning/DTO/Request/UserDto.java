package com.example.e_learning.DTO.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "lastname is required")
    private String lastname;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @Email
    private String email;


}
