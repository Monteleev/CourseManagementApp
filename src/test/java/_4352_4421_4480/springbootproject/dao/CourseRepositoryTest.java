package _4352_4421_4480.springbootproject.dao;

import _4352_4421_4480.springbootproject.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    void testCourseDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(courseRepository);
    }

    @Test
    void testFindByIdReturnsCourse() {
        Optional<Course> optionalCourse = courseRepository.findById(1L);
        Course storedCourse = optionalCourse.get();
        Assertions.assertNotNull(storedCourse);
        Assertions.assertEquals(4, storedCourse.getYear());
    }
}