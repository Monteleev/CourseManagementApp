package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.dao.CourseRepository;
import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @InjectMocks
    CourseController courseController;

    @Mock
    CourseService courseService;

    @Test
    public void saveCourseTest(){
        Course course = new Course(1L,"DB","courses syllabus",3,6);
        String redirect = courseController.saveCourse(course);

        verify(courseService,times(1)).addNewCourse(course);
        assertEquals(redirect,"redirect:/courses");
    }
}
