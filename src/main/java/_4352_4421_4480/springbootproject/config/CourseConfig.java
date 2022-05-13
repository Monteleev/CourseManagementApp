package _4352_4421_4480.springbootproject.config;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.dao.CourseRepository;
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
                    1L,"Software Engineering ","This is the syllabus for the Software Engineering Course",4,8
            );
            Course SoftwareDevel = new Course(
                    2L,"Software Development II","This is the syllabus for the Software Development II Course",3,5
            );

            repository.saveAll(
                    List.of(SoftwareEng ,SoftwareDevel)
            );
        };
    }
}
