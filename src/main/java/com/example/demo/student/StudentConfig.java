package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student shahriar = new Student(
                    "Shahriar",
                    LocalDate.of(1998, Month.OCTOBER, 27),
                    "shahriar.hossain997@gmail.com"
            );

            Student alex = new Student(
                    "Alex",
                    LocalDate.of(2000, Month.OCTOBER, 7),
                    "Alex@gmail.com"
            );

            Student lester = new Student(
                    "Lester",
                    LocalDate.of(2000, Month.APRIL, 7),
                    "Lester@gmail.com"
            );

            repository.saveAll(
                    List.of(shahriar, alex, lester)
            );
        };
    }
}
