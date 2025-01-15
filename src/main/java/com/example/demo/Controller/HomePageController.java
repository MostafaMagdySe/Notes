package com.example.demo.Controller;

import com.example.demo.DTO.loginRequest;
import com.example.demo.Entities.NotesContent;
import com.example.demo.Repository.NotesRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Services.CreateUserService;
import com.example.demo.Services.NotesServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HomePageController {

    private final UserRepo userRepo;
    private final NotesRepo notesrepo;
    private final CreateUserService createUserService;
    private final NotesServices notesServices;


    public HomePageController(NotesRepo notesrepo,UserRepo userRepo, CreateUserService createUserService, NotesServices notesServices ) {

       this.notesrepo = notesrepo;
    this.userRepo = userRepo;
    this.createUserService=createUserService;
    this.notesServices=notesServices;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody loginRequest loginrequest ) {

        return ResponseEntity.ok(createUserService.verify(loginrequest));
    }
    // this HomePage is only for logged in users via log in page
@GetMapping ("/{PageNo}/{PageSize}")
    public List<NotesContent> ViewAllNotes(@PathVariable int PageNo, @PathVariable int PageSize ){


return  notesServices.getAllNotes(PageNo,PageSize);


    }



}
