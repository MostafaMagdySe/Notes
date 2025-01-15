package com.example.demo.DTO.animechanIO;

import lombok.Data;

@Data
//@JsonIgnoreProperties // Ignore any fields not explicitly defined
public class animeNameResponse {


    private int id;
    private String name;
    private String altName;



}
