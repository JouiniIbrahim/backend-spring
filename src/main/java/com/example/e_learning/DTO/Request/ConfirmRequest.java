package com.example.e_learning.DTO.Request;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmRequest {
    @Email
    private String email;
    private String password;
    private String activation_key;
}
