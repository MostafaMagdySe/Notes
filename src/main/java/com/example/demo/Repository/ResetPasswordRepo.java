package com.example.demo.Repository;

import com.example.demo.Entities.resetpassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordRepo extends JpaRepository<resetpassword,Integer> {
    resetpassword findByemail(String email);
}
