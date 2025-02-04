package com.example.e_learning.security.services;

import com.example.e_learning.models.User;
import com.example.e_learning.repositories.UserRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.securityEcommerce.models.User;
//import com.securityEcommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 @Autowired
 UserRepo userrepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userrepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    //return UserDetailsImpl.build(user);
      return UserDetailsImpl.build(user);
  }

}
