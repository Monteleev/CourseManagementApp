package _4352_4421_4480.springbootproject.Course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunner
            (CourseRepository repository)
    {
        return args ->
        {
            Course SoftwareEng = new Course(
                    "SoftwareEng"
            );

            Course SoftwareDevel = new Course(
                    "SoftwareDevel"
            );

            repository.saveAll(
                    List.of(SoftwareEng ,SoftwareDevel)
            );
        };
    }
}
