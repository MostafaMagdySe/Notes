package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNoteRequest {
    @NotBlank(message = "You cannot Save Empty Content, Write Something!")
    private String content;
    private int user_id;

}
