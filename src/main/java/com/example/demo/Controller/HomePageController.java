package com.example.demo.Controller;

import com.example.demo.Config.UserRoles;
import com.example.demo.Entities.NotesContent;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.NotesRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class HomePageController {

    private final UserRepo userRepo;
    private final NotesRepo notesrepo;



    public HomePageController(NotesRepo notesrepo,UserRepo userRepo ) {

       this.notesrepo = notesrepo;
    this.userRepo = userRepo;
    }

@GetMapping ("")
    public List<NotesContent> ViewAllNotes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRoles userRoles = (UserRoles) auth.getPrincipal();
        String username = userRoles.getUsername();
        Users user = userRepo.findByusername(username);
    if (user == null)
        throw new RuntimeException("User not found");

return  notesrepo.findByUserId(user.getId());


    }



}
