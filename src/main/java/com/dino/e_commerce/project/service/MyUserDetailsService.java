package com.dino.e_commerce.project.service;

import com.dino.e_commerce.project.model.UserPrincipal;
import com.dino.e_commerce.project.model.Users;
import com.dino.e_commerce.project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user = repo.findByUsername(username);
       if (user == null){
           System.out.println("user not found");
           throw new UsernameNotFoundException("No user found with this credential");
       }
       return new UserPrincipal(user);
    }
}
