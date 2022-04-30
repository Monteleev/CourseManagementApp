package _4352_4421_4480.springbootproject.config;

import _4352_4421_4480.springbootproject.entity.Student;
import _4352_4421_4480.springbootproject.repository.StudentRepository;
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
            Student student1 = new Student(1L, "Baggelis", 2018);
            Student student2 = new Student(2L, "Giannis", 2019);
            repository.saveAll(List.of(student1, student2));
        };
    }
}
