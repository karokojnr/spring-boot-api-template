package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * THIS IS THE Data Access LAYER
 */

/**
 * T - Type of object that this repository will work with
 * Long - The id type
 */
@Repository //Responsible for data access
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    // SELECT * from student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1 ")
    Optional<Student> findByEmail(String email);
}
