package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student kennedy = new Student(
                    "Kennedy",
                    "kennedy@kennedy.com",
                    LocalDate.of(1999, APRIL, 13)
            );
            Student kelvin = new Student(
                    "Kelvin",
                    "kelvin@kelvin.com",
                    LocalDate.of(1995, JANUARY, 27)
            );
            studentRepository.saveAll(
                    List.of(kennedy, kelvin)
            );
        };
    }
}
