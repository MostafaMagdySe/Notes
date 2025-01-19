package com.example.demo.Controller;

import com.example.demo.DTO.UsernameRequest;
import com.example.demo.DTO.loginRequest;
import com.example.demo.Entities.NotesContent;
import com.example.demo.Repository.NotesRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Services.NotesServices;
import com.example.demo.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HomePageController {

    private final UserRepo userRepo;
    private final NotesRepo notesrepo;
    private final UserService userService;
    private final NotesServices notesServices;


    public HomePageController(NotesRepo notesrepo, UserRepo userRepo, UserService userService, NotesServices notesServices ) {

       this.notesrepo = notesrepo;
    this.userRepo = userRepo;
    this.userService = userService;
    this.notesServices=notesServices;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody loginRequest loginrequest ) {

        return ResponseEntity.ok(userService.verify(loginrequest));
    }
    // this HomePage is only for logged in users via log in page
@GetMapping ("/")
    public List<NotesContent> ViewAllNotes( @RequestParam(defaultValue = "1") int PageNo,
                                            @RequestParam(defaultValue = "5") int PageSize ){



return  notesServices.getAllNotes(PageNo-1,PageSize);



    }
    @PatchMapping ("/updateProfile")
public ResponseEntity updateProfile(@RequestBody UsernameRequest username){

userService.updateUserProfile(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
