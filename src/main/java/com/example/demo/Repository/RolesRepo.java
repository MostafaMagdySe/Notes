package com.example.demo.Repository;

import com.example.demo.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Integer> {
}
