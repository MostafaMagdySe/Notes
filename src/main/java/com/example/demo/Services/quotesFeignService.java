package com.example.demo.Services;

import com.example.demo.DTO.animechanIO.MainRequest;
import com.example.demo.feign.animechanFeign;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class quotesFeignService {
    private final animechanFeign animechan;

    public quotesFeignService(animechanFeign animechan) {
        this.animechan = animechan;
    }

    public MainRequest getRandomQuote() {
        return animechan.getRandomQuote();


    }
}