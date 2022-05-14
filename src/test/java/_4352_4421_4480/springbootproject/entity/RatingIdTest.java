package _4352_4421_4480.springbootproject.entity;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RatingIdTest {

    Course course = new Course(1L,"SoftEng","courses syllabus","course description",3,6);
    Student student = new Student(1L, "Giwrgos", 2018);
    RatingId ratingId = new RatingId(course.getId(), student.getId());

    @Test
    public void gettersTest(){

        assertEquals((long)ratingId.getCourseId(), 1L);
        assertEquals((long)ratingId.getStudentId(), 1L);
    }
}
