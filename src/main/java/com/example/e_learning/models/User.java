package com.example.e_learning.models;


import jakarta.persistence.*;
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

    @ManyToMany
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name="roleId"))
    private List<Role> roles;


}
