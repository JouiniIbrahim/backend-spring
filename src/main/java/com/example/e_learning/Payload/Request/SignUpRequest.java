package com.example.e_learning.Payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignUpRequest {
   @NotBlank
    private String username;
   @NotBlank
   @Email
    private String  email;

   @NotBlank
    private String password;
    private String  role;
    private Boolean activated=false;
    private String isAccepted="waiting";


    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }



    public SignUpRequest() {
    }

    public SignUpRequest(String username, String email, String password,  String role,  String isAccepted) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccepted = isAccepted;

    }
}
