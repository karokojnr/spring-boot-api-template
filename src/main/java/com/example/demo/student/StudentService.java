package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * THIS IS THE Service LAYER
 */
@Service //A class that has to be instantiated i.e Bean
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository
                .findByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
//        studentRepository.findById(studentID);
        boolean found = studentRepository.existsById(studentID);
        if (!found){
            throw  new IllegalStateException("Student with id "+ studentID +" does not exist!");
        }
        studentRepository.deleteById(studentID);
    }
    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).
                orElseThrow(() -> new IllegalStateException(
                        "student with ID "+ studentID + " does not exist!"
                ));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }
}
