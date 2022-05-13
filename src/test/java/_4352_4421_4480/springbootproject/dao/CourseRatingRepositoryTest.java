package _4352_4421_4480.springbootproject.dao;


import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class CourseRatingRepositoryTest {

    @Autowired
    CourseRatingRepository courseRatingRepository;

    @Test
    void testCourseRatingDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(courseRatingRepository);
    }

}
