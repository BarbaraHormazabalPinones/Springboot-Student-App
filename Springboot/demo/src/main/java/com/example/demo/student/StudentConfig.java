package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
/* In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC
container are called beans. A bean is an object that is instantiated, assembled, and otherwise managed
by a Spring IoC container.

Simply put, Inversion of Control (IoC) is a process in which an object defines its dependencies without creating
them.  This object delegates the job of constructing such dependencies to an IoC container.
*/
    @Bean
    /*Accesar al repositorio */
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Maria = new Student(
                    "Maria",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "maria@gmail.com"
            );
            Student Jorge = new Student(
                    "Jorge",
                    LocalDate.of(1993, Month.MARCH, 26),
                    "jorge@gmail.com"
            );
            /*se guardan los datos */
            repository.saveAll(
                    List.of(Maria, Jorge)
            );
        };
    }
}
