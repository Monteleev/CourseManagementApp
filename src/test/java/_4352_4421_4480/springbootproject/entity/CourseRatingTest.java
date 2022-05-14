package _4352_4421_4480.springbootproject.entity;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CourseRatingTest {

    Course course = new Course(1L,"SoftEng","courses syllabus","course description",3,6);
    Student student = new Student(1L, "Giwrgos", 2018);
    RatingId ratingId = new RatingId(course.getId(), student.getId());
    CourseRating courseRating = new CourseRating(ratingId, course, student, "5", "7");

    @Test
    public void gettersTest(){

        Student tempStudent = new Student(1L, "Giwrgos", 2018);
        Course tempCourse = new Course(1L,"SoftEng","courses syllabus","course description",3,6);
        RatingId tempRatingId = new RatingId(course.getId(), student.getId());

        assertThat(tempStudent).usingRecursiveComparison().isEqualTo(courseRating.getStudent());
        assertThat(tempCourse).usingRecursiveComparison().isEqualTo(courseRating.getCourse());
        assertThat(tempRatingId).usingRecursiveComparison().isEqualTo(courseRating.getId());

    }

    @Test
    public void ratingTest(){

        assertEquals("6.0", courseRating.getRating());

        courseRating.setExamRating("8.0");
        assertEquals("8.0",courseRating.getExamRating());

        courseRating.setProjectRating("9.0");
        assertEquals("9.0",courseRating.getProjectRating());

        courseRating.calcRating("5.0","9.0");
        assertEquals("7.0", courseRating.getRating());

        courseRating.deleteRating(ratingId);
        assertEquals("-",courseRating.getRating());

    }

}
