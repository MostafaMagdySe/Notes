package com.example.demo.Controller;

import com.example.demo.DTO.CreateNoteRequest;
import com.example.demo.DTO.EditNoteRequest;
import com.example.demo.Entities.Notes;
import com.example.demo.Repository.NotesRepo;
import com.example.demo.Services.NotesServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotesController {

    private final NotesServices notesServices;
    private final NotesRepo notesrepo;



    public NotesController(NotesServices notesServices, NotesRepo notesrepo) {
        this.notesServices = notesServices;
        this.notesrepo = notesrepo;

    }


    @PostMapping("/note")

    public ResponseEntity createNotes(@Valid @RequestBody CreateNoteRequest createNoteRequest) {

        notesServices.createNote(createNoteRequest);

       /* if (createNoteRequest.getContent().startsWith("a")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else*/
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public Notes getNotes(@PathVariable("id") int id) {
        return notesrepo.findById(id).get();

    }

    @PatchMapping("/note/{id}")
    public ResponseEntity editNotes(@PathVariable("id") int id, @RequestBody EditNoteRequest editNoteRequest)
    {

        try {
            notesServices.editNote(id,editNoteRequest );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/note/{id}")
    public ResponseEntity deleteNotes (@PathVariable("id") int id){
        try {
            notesServices.deleteNote(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }





}