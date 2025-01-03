package com.example.demo.Services;

import com.example.demo.DTO.CreateUserRequest;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.UserRepo;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class CreateUserService {


    private final UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public CreateUserService(UserRepo userRepo)
    {
    this.userRepo= userRepo;
    }

    public void CreateUser (CreateUserRequest createUserRequest){
        Users user = new Users() ;
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(encoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setPhone(createUserRequest.getPhone());
        userRepo.save(user);



    }



}
