package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.dao.CourseRatingRepository;
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
public class CourseRatingServiceTest {

    @InjectMocks
    CourseRatingService courseRatingService;

    @Mock
    CourseRatingRepository courseRatingRepository;

    Course course = new Course(1L,"SoftEng","courses syllabus","course description",3,6);
    Student student = new Student(1L, "Dimitris", 2018, 1);
    RatingId ratingId = new RatingId(course.getId(),student.getId());
    CourseRating courseRating = new CourseRating(ratingId,course,student,"5", "5");

    @Test
    public void addNewCourseRatingTest(){

        courseRatingService.addNewCourseRating(courseRating);

        verify(courseRatingRepository,times(1)).save(courseRating);
    }


    @Test
    public void deleteRating(){

        when(courseRatingRepository.findCourseRatingById(ratingId)).thenReturn(Optional.ofNullable(courseRating));
        courseRatingService.deleteRating(ratingId);

        verify(courseRatingRepository,times(1)).deleteCourseRatingById(ratingId);

    }

    @Test
    public void getRatingByIdTest()
    {
        when(courseRatingRepository.findCourseRatingById(ratingId)).thenReturn(Optional.ofNullable(courseRating));

        CourseRating courseRating1 = courseRatingService.getRatingById(ratingId);


        assertEquals(ratingId, courseRating1.getId());
        assertEquals(course, courseRating1.getCourse());
        assertEquals(student, courseRating1.getStudent());
        assertEquals("5.0", courseRating1.getRating());
    }

    @Test
    public void updateCourseTest(){
        when(courseRatingRepository.save(courseRating)).thenReturn(new CourseRating(ratingId,course,student,"10", "9"));
        CourseRating courseRating1 = courseRatingService.updateCourseRating(courseRating);

        assertEquals(ratingId, courseRating1.getId());
        assertEquals(course, courseRating1.getCourse());
        assertEquals(student, courseRating1.getStudent());
        assertEquals("9.5", courseRating1.getRating());
    }

}
