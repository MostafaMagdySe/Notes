package com.example.demo.DTO;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String content;
    private int user_id;

}
