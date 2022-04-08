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
                    1L,"SoftwareEng","llabddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddusSYY",4,8
            );
            Course SoftwareDevel = new Course(
                    2L,"SoftwareDevel","Syllabuuus",2,3
            );

            repository.saveAll(
                    List.of(SoftwareEng ,SoftwareDevel)
            );
        };
    }
}
