package _4352_4421_4480.springbootproject.entity;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CourseTest {

    Course course = new Course(1L,"SoftEng","courses syllabus",3,6);
    Student student1 = new Student();
    Student student2 = new Student();
    RatingId ratingId1 = new RatingId(1L,1L);
    RatingId ratingId2 = new RatingId(1L,2L);
    CourseRating courseRating1 = new CourseRating(ratingId1,course,student1,"5", "7");
    CourseRating courseRating2 = new CourseRating(ratingId2,course,student2,"9", "9");



    @Test
    public void nameTest() {

        assertEquals(course.getName(), "SoftEng");

        course.setName("SoftDev");
        assertEquals(course.getName(), "SoftDev");
    }

    @Test
    public void syllabusTest(){


        assertEquals(course.getSyllabus(),"courses syllabus");

        course.setSyllabus("new syllabus");
        assertEquals(course.getSyllabus(),"new syllabus");

    }

    @Test
    public void yearTest(){

        assertEquals((int)course.getYear(),3);

        course.setYear(5);
        assertEquals((int)course.getYear(),5);

    }

    @Test
    public void semesterTest(){

        assertEquals((int)course.getSemester(),6);

        course.setSemester(2);
        assertEquals((int)course.getSemester(),2);

    }

    @Test
    public void idTest(){

        assertEquals((long)course.getId(),1L);

        course.setId(3L);
        assertEquals((long)course.getId(),3L);

    }

    @Test
    public void enrolledStudents(){

        int numOfEnrolledStudents;
        List<Student> enrolledStudents = new ArrayList<>();

        enrolledStudents.add(student1);
        enrolledStudents.add(student2);

        course.enrollStudent(student1);
        course.enrollStudent(student2);

        List<Student> expectedEnrolledStudents = course.getEnrolledStudents();
        assertEquals(enrolledStudents, expectedEnrolledStudents);

        numOfEnrolledStudents = course.getNumberOfEnrolledStudents();
        assertEquals(numOfEnrolledStudents,2);

        course.deleteStudent(student2);
        numOfEnrolledStudents = course.getNumberOfEnrolledStudents();
        assertEquals(numOfEnrolledStudents,1);

    }

    @Test
    public void registerGradeTest(){

        List<CourseRating> courseRatings = new ArrayList<>();

        courseRatings.add(courseRating1);
        courseRatings.add(courseRating2);

        course.registerGrade(courseRating1);
        course.registerGrade(courseRating2);

        List<CourseRating> expectedCourseRatings = course.getRegisterStudentsGrades();
        assertEquals(expectedCourseRatings,courseRatings);

    }
}