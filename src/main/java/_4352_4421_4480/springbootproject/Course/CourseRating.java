package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;

import javax.persistence.*;

@Entity
public class CourseRating {

    @EmbeddedId
    CourseGrade id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    int rating;

    // standard constructors, getters, and setters

}
