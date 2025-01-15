package com.example.demo.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;

@Entity
@Data

public class resetpassword {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String email;
    private String code;



}
