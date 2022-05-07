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
    CourseRating courseRating1 = new CourseRating(ratingId1,course,student1,"5");
    CourseRating courseRating2 = new CourseRating(ratingId2,course,student2,"9");



    @Test
    public void nameTest() {
        String expectedName;
        expectedName = course.getName();
        assertEquals(expectedName, "SoftEng");

        course.setName("SoftDev");
        expectedName = course.getName();
        assertEquals(expectedName, "SoftDev");
    }

    @Test
    public void syllabusTest(){

        String expectedSyllabus;
        expectedSyllabus = course.getSyllabus();
        assertEquals(expectedSyllabus,"courses syllabus");

        course.setSyllabus("new syllabus");
        expectedSyllabus = course.getSyllabus();
        assertEquals(expectedSyllabus,"new syllabus");

    }

    @Test
    public void yearTest(){

        int expectedYear;
        expectedYear = course.getYear();
        assertEquals(expectedYear,3);

        course.setYear(5);
        expectedYear = course.getYear();
        assertEquals(expectedYear,5);

    }

    @Test
    public void semesterTest(){

        int expectedSemester;
        expectedSemester = course.getSemester();
        assertEquals(expectedSemester,6);

        course.setSemester(2);
        expectedSemester = course.getSemester();
        assertEquals(expectedSemester,2);

    }

    @Test
    public void idTest(){

        Long expectedId;
        expectedId = course.getId();
        assertEquals((long)expectedId,1L);

        course.setId(3L);
        expectedId = course.getId();
        assertEquals((long)expectedId,3L);

    }

    @Test
    public void enrolledStudents(){

        int numOfEnrolledStudents;
        List<Student> expectedEnrolledStudents = new ArrayList<>();

        expectedEnrolledStudents.add(student1);
        expectedEnrolledStudents.add(student2);

        course.enrollStudent(student1);
        course.enrollStudent(student2);

        List<Student> enrolledStudents = course.getEnrolledStudents();
        assertEquals(expectedEnrolledStudents, enrolledStudents);

        numOfEnrolledStudents = course.getNumberOfEnrolledStudents();
        assertEquals(numOfEnrolledStudents,2);

        course.deleteStudent(student2);
        numOfEnrolledStudents = course.getNumberOfEnrolledStudents();
        assertEquals(numOfEnrolledStudents,1);

    }

    @Test
    public void registerGradeTest(){

        List<CourseRating> expectedCourseRatings = new ArrayList<>();

        expectedCourseRatings.add(courseRating1);
        expectedCourseRatings.add(courseRating2);

        course.registerGrade(courseRating1);
        course.registerGrade(courseRating2);

        List<CourseRating> courseRatings = course.getRegisterStudentsGrades();
        assertEquals(courseRatings,expectedCourseRatings);

    }
}