package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;

import javax.persistence.*;

@Entity
public class CourseRating {

    //@EmbeddedId to mark the primary key,
    // which is an instance of the CourseGrade class

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

    public CourseRating() {

    }

    public CourseRating(CourseGrade id, Course course, Student student, int rating){
        this.course = course;
        this.student = student;
        this.rating = rating;
        this.id = id;
    }

    public CourseRating(Course course, Student student, int rating){
        this.course = course;
        this.student = student;
        this.rating = rating;
    }

    public CourseRating(Course course, Student student){
        this.course = course;
        this.student = student;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", student_id ='" + student.getId() + '\'' +
                ", course_id ='" + course.getId() + '\'' +
                ", rating = " + rating +
                '}';
    }

}
