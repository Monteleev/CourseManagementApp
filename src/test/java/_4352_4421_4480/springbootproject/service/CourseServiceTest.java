package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.dao.CourseRepository;
import _4352_4421_4480.springbootproject.entity.*;
import org.mockito.*;


import static java.util.Map.entry;
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

    @Test
    public void showCourseStatisticsTest(){
        Course course4 = new Course(4L,"Eng","courses syllabus",3,6);

        Student student1 = new Student(1L, "Giwrgos", 2015);
        Student student2 = new Student(2L, "Anna", 2010);
        Student student3 = new Student(3L, "Petros", 2019);
        Student student4 = new Student(4L, "Maria", 2018);

        RatingId ratingId1 = new RatingId(course4.getId(), student1.getId());
        RatingId ratingId2 = new RatingId(course4.getId(), student2.getId());
        RatingId ratingId3 = new RatingId(course4.getId(), student3.getId());
        RatingId ratingId4 = new RatingId(course4.getId(), student4.getId());


        CourseRating courseRating1 = new CourseRating(ratingId1, course4, student1, "5", "5");
        CourseRating courseRating2 = new CourseRating(ratingId2, course4, student2, "6", "6");
        CourseRating courseRating3 = new CourseRating(ratingId3, course4, student3, "7", "7");
        CourseRating courseRating4 = new CourseRating(ratingId4, course4, student4, "8", "8");

        course4.registerGrade(courseRating1);
        course4.registerGrade(courseRating2);
        course4.registerGrade(courseRating3);
        course4.registerGrade(courseRating4);

        Map<String, Double> expectedRes = Map.ofEntries(entry("Mean",6.5),entry("Min",5.0),
                entry("25th Percentile",5.25),entry("Max",8.0),entry("75th Percentile",7.75),
                entry("Skewness",0.0),entry("Standard Deviation",1.2909944487358056),entry("Median",6.5),
                entry("Kurtosis",-1.1999999999999993),entry("50th Percentile",6.5),entry("Variance",1.6666666666666667));

        Map<String, Double> res = courseService.calculateCourseStatistics(course4);
        assertEquals(res,expectedRes);

    }



}
