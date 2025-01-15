package com.example.demo.Repository;

import com.example.demo.Entities.Notes;
import com.example.demo.Entities.NotesContent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Notes,Integer> {


   // List<Notes> findAllByuserId (int id);
   List<NotesContent> findByUserId (int id, Pageable pageable);
}
