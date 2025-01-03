package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity

public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer    id;
    @NotNull
    private String role_id;

}
