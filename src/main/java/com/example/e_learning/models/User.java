package com.example.e_learning.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstname,lastname, username,password,email;
    private boolean activated=false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name="roleId"))
    private List<Role> roles;


    public User(@NotBlank String username, @NotBlank @Email String email, String encode, String role) {

    }
}
