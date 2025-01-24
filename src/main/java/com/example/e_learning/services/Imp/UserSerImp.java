package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.models.User;
import com.example.e_learning.repositories.UserRepo;
import com.example.e_learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerImp  implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserResponseDto AddUser(UserDto userDto) {

    }

}
