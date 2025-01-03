package com.example.demo.Services;

import com.example.demo.Config.UserRoles;
import com.example.demo.DTO.CreateNoteRequest;
import com.example.demo.DTO.EditNoteRequest;
import com.example.demo.Entities.Notes;
import com.example.demo.Entities.Users;
import com.example.demo.Repository.NotesRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesServices {


    private final NotesRepo notesrepo;

    private final UserRepo userRepo ;
    public NotesServices (NotesRepo notesrepo,UserRepo userRepo ){
        this.userRepo=userRepo;
        this.notesrepo=notesrepo;
    }

//@Transactional(isolation = Isolation.SERIALIZABLE)
    public void createNote(CreateNoteRequest createNoteRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRoles userRoles = (UserRoles) auth.getPrincipal();
        String username = userRoles.getUsername();
       Users user = userRepo.findByusername(username);

            Notes notes = new Notes();
            notes.setContent(createNoteRequest.getContent());

        notes.setUserId(user.getId());

            notesrepo.save(notes);


    }

    public  void editNote(int id, EditNoteRequest editNoteRequest) throws Exception {

        Optional<Notes> notes = notesrepo.findById(id);
      if (notes.isPresent()){
       Notes not= notes.get();
        not.setContent(editNoteRequest.getContent());
        notesrepo.save(not);}
        else{
    throw new Exception("User with id " + id + " not found");
    }

    }
    public void deleteNote(int id) throws Exception {
Optional <Notes> notes = notesrepo.findById(id);

if (notes.isPresent()){
    notesrepo.deleteById(id);
}
else
    throw new Exception("Deletion Failed, User with id " + id + " not found");
    }
   // public Page<Notes> findAll(Pageable pageable) {
      //  return productRepository.findAll(pageable);
    }




