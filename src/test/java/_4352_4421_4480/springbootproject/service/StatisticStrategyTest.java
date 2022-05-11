package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.entity.RatingId;
import _4352_4421_4480.springbootproject.entity.Student;

import org.junit.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.Assert.assertEquals;

public class StatisticStrategyTest {

    Course course = new Course(1L,"SoftEng","courses syllabus",3,6);
    Student student1 = new Student(1L, "Giwrgos", 2015);
    Student student2 = new Student(2L, "Anna", 2010);
    Student student3 = new Student(3L, "Petros", 2019);
    Student student4 = new Student(4L, "Maria", 2018);

    RatingId ratingId1 = new RatingId(course.getId(), student1.getId());
    RatingId ratingId2 = new RatingId(course.getId(), student2.getId());
    RatingId ratingId3 = new RatingId(course.getId(), student3.getId());
    RatingId ratingId4 = new RatingId(course.getId(), student4.getId());


    CourseRating courseRating1 = new CourseRating(ratingId1, course, student1, "5","5");
    CourseRating courseRating2 = new CourseRating(ratingId2, course, student2, "6","6");
    CourseRating courseRating3 = new CourseRating(ratingId3, course, student3, "7","7");
    CourseRating courseRating4 = new CourseRating(ratingId4, course, student4, "8","8");

    StatisticStrategy statisticStrategy = new StatisticStrategy();

    @Test

    public void calculateCourseStatisticsTest() {

        course.registerGrade(courseRating1);
        course.registerGrade(courseRating2);
        course.registerGrade(courseRating3);
        course.registerGrade(courseRating4);
        Map<String, Double> res = statisticStrategy.calculateCourseStatistics(course);

        Map<String, Double> expectedRes = Map.ofEntries(entry("Mean",6.5),entry("Min",5.0),
                entry("25th Percentile",5.25),entry("Max",8.0),entry("75th Percentile",7.75),
                entry("Skewness",0.0),entry("Standard Deviation",1.2909944487358056),entry("Median",6.5),
                entry("Kurtosis",-1.1999999999999993),entry("50th Percentile",6.5),entry("Variance",1.6666666666666667));

        assertEquals(expectedRes,res);

    }

}
