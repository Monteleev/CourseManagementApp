package _4352_4421_4480.springbootproject.config;

import _4352_4421_4480.springbootproject.entity.Student;
import _4352_4421_4480.springbootproject.dao.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunnerStudent (StudentRepository repository)
    {
        return args ->
        {
            Student student1 = new Student(4352L, "Dimitropoulos Dimitrios", 2018, 8);
            Student student2 = new Student(4421L, "Messinis Spiridon", 2018, 8);
            Student student3 = new Student(4480L, "Poulos Grigorios", 2018, 8);
            Student student4 = new Student(2L, "Zarras Apostolos", 2004, 10);
            Student student5 = new Student(1L, "Vassiliadis Panagiotis", 2002, 14);
            repository.saveAll(List.of(student1, student2, student3, student4, student5));
        };
    }
}
