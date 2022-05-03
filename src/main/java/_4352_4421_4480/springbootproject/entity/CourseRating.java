package _4352_4421_4480.springbootproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "course_rating")
public class CourseRating {

    //@EmbeddedId to mark the primary key,
    // which is an instance of the CourseGrade class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    RatingId id;

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

    public CourseRating() {}

    public CourseRating(RatingId id, Course course, Student student, int rating){
        this.course = course;
        this.student = student;
        this.rating = rating;
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
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
