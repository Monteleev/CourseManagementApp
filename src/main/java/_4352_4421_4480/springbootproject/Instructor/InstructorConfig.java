package _4352_4421_4480.springbootproject.Instructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InstructorConfig {

    //@Bean
    CommandLineRunner commandLineRunner
            (InstructorRepository repository)
    {
        return args ->
        {
            Instructor zarras = new Instructor(
                    1L,"zarras",40,"zaras@gmail.com"
            );

            Instructor vassiliadis = new Instructor(
                    "vassiliadis",20,"vassiliadis@gmail.com"
            );

            repository.saveAll(
                    List.of(zarras ,vassiliadis)
            );
        };
    }
}
