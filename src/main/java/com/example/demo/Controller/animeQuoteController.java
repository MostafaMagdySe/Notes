package com.example.demo.Controller;

import com.example.demo.DTO.animechanIO.MainRequest;
import com.example.demo.Services.quotesFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class animeQuoteController {
    private final quotesFeignService quotesService;

    public animeQuoteController(quotesFeignService quotesService) {
        this.quotesService = quotesService;


    }

    @GetMapping("/quote")
    public MainRequest getRandomAnimeQuote() {
        return quotesService.getRandomQuote();


    }
}