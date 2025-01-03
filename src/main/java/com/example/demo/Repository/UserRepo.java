package com.example.demo.Repository;

import com.example.demo.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByusername(String username);
}
