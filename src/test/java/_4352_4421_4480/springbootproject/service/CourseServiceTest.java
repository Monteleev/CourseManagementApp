package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.dao.CourseRepository;
import _4352_4421_4480.springbootproject.entity.*;
import org.mockito.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {


    List<Course> courses = new ArrayList<>();
    Course course1 = new Course(1L,"SoftEng","courses syllabus",3,6);
    Course course2 = new Course(2L,"SoftDev","courses syllabus",3,6);
    Course course3 = new Course(3L,"Math","courses syllabus",3,6);



    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;


    @Test
    public void getCourseTest(){
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> courseList = courseService.getCourse();
        assertEquals(3, courseList.size());
        verify(courseRepository,times(1)).findAll();

    }

    @Test
    public void addCourseTest(){

        Course course4 = new Course(4L,"DB","courses syllabus",3,6);
        courseService.addNewCourse(course4);

        verify(courseRepository,times(1)).save(course4);
    }


    @Test
    public void deleteCourseByIdTest(){

        courseService.deleteCourseById(1L);
        verify(courseRepository,times(1)).deleteById(1L);

    }

    @Test
    public void getCourseByIdTest()
    {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        Course course = courseService.getCourseById(1L);


        assertEquals(1L, course.getId());
        assertEquals("SoftEng", course.getName());
        assertEquals("courses syllabus", course.getSyllabus());
        assertEquals(3,course.getYear());
        assertEquals(6,course.getSemester());
    }

    @Test
    public void updateCourseTest(){
        when(courseRepository.save(course1)).thenReturn(new Course(1L,"Java","courses syllabus",1,2));
        Course course = courseService.updateCourse(course1);

        assertEquals(1L, course.getId());
        assertEquals("Java", course.getName());
        assertEquals("courses syllabus", course.getSyllabus());
        assertEquals(1,course.getYear());
        assertEquals(2,course.getSemester());
    }



}
