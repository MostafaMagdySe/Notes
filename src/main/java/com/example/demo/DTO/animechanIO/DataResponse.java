package com.example.demo.DTO.animechanIO;

import lombok.Data;

@Data
public class DataResponse {
    private String content;
    private animeNameResponse anime;
    private characterNameResponse character;
}
