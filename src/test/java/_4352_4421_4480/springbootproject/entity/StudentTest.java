package _4352_4421_4480.springbootproject.entity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class StudentTest {

    Student student = new Student(1L, "Giwrgos", 2018);
    Course course1 = new Course(1L,"SoftEng","softeng syllabus",3,6);
    Course course2 = new Course(2L,"SoftDev","softdev syllabus",3,6);

    @Test
    public void idTest(){

        assertEquals((long)student.getId(),1L);

        student.setId(2L);
        assertEquals((long)student.getId(),2L);

    }

    @Test
    public void nameTest(){

        assertEquals(student.getName(),"Giwrgos");

        student.setName("Giorikas");
        assertEquals(student.getName(),"Giorikas");

    }

    @Test
    public void yearOfRegistrationTest(){

        assertEquals(student.getYearOfRegistration(),2018);

        student.setYearOfRegistration(2019);
        assertEquals(student.getYearOfRegistration(),2019);

    }

    @Test
    public void yearOfStudiesTest(){

        assertEquals(student.getYearsOfStudies(),4);

    }

    @Test
    public void semesterTest(){

        assertEquals(student.getSemester(),8);

    }

    @Test
    public void coursesTest(){

        List<Course> expectedEnrolledCourses = new ArrayList<>();
        expectedEnrolledCourses.add(course1);
        expectedEnrolledCourses.add(course2);

        student.registerStudent(course1);
        student.registerStudent(course2);
        List<Course> enrolledCourses = student.getCourses();
        assertEquals(expectedEnrolledCourses,enrolledCourses);
    }




    @Test
    public void studentGradesTest(){
        RatingId ratingId1 = new RatingId(course1.getId(), student.getId());
        RatingId ratingId2 = new RatingId(course2.getId(), student.getId());
        CourseRating courseRating1 = new CourseRating(ratingId1,course1,student,"10","9");
        CourseRating courseRating2 = new CourseRating(ratingId2,course2,student,"6","8");
        student.registerGrade(courseRating1);
        student.registerGrade(courseRating2);
        List<CourseRating> courseRatings = student.getRegisterStudentsGrades();
        List<CourseRating> expectedCourseRatings = new ArrayList<>();
        expectedCourseRatings.add(courseRating1);
        expectedCourseRatings.add(courseRating2);
        assertEquals(courseRatings,expectedCourseRatings);

        String grade = student.getCourseGrade(course1);
        assertEquals(grade,courseRating1.getRating());

    }



}
