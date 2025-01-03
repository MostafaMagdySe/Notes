package com.example.demo.Services;

import com.example.demo.Config.UserRoles;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    //public static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    private final UserRepo userRepo;
    public CustomUserDetailsService (UserRepo userRepo){
        this.userRepo=userRepo;
    }


    @Override
    public UserRoles loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByusername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserRoles(user);
    }



    }
