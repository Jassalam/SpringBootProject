package com.example.project2.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRespository respository){
        return args -> {
            Student jasveer = new Student(
                "Jasveer",
                "jasveer@gmail.com",
                LocalDate.of(2000, Month.FEBRUARY, 4)
            );
            Student alex = new Student(
                "Alex",
                "alex@gmail.com",
                LocalDate.of(2001, Month.FEBRUARY, 7)
            );
            Student chole = new Student(
                "Chole",
                "chole@gmail.com",
                LocalDate.of(1990, Month.DECEMBER, 9)
            );
            respository.saveAll(List.of(jasveer, alex, chole));
        };
    }
}
