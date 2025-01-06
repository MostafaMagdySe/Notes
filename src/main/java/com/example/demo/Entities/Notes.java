package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@DynamicInsert
public class Notes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int id;

    private String content;
    @Column(name = "user_id")
    private int userId;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false) // Maps the foreign key column 'user_id'
//    private Users userid;





}
