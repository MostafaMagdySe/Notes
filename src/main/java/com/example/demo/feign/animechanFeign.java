package com.example.demo.feign;

import com.example.demo.DTO.animechanIO.MainRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "animechan",
        url = "https://animechan.io/api/v1")
public interface animechanFeign {


@GetMapping("//quotes/random")
MainRequest getRandomQuote();

}



