package _4352_4421_4480.springbootproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_rating")
public class CourseRating {

    //@EmbeddedId to mark the primary key,
    // which is an instance of the RatingId class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private RatingId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private String projectRating;
    private String examRating;
    private String rating;

    public CourseRating() {}

    public CourseRating(RatingId id, Course course, Student student, String examRating, String projectRating){
        this.course = course;
        this.student = student;
        this.examRating = examRating;
        this.projectRating = projectRating;
        this.id = id;
        double calcRating = (Double.parseDouble(projectRating)+Double.parseDouble(examRating))/2;
        if ((calcRating*10) % 10 == 0 || (calcRating*10) % 5 == 0){
            this.rating = String.valueOf(calcRating);
        }
        else if ((calcRating*100) % 25 == 0) {
            this.rating = String.valueOf(Math.floor(calcRating) + 0.5);
        }
        else {
            this.rating = String.valueOf(Math.ceil(calcRating));
        }
    }

    public CourseRating(RatingId id, Course course, Student student, String rating){
        this.course = course;
        this.student = student;
        this.id = id;
        this.rating = rating;
    }

    public RatingId getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void deleteRating(RatingId ratingId) {
        deleteRatingFrom(course.getRegisterStudentsGrades(), ratingId);
        deleteRatingFrom(student.getRegisterStudentsGrades(), ratingId);
        setRating("-");
    }

    private void deleteRatingFrom(List<CourseRating> ratingList, RatingId ratingId) {
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingIdIsEqualToId(ratingId, ratingList, i)) {
                ratingList.remove(i);
            }
        }
    }

    private boolean ratingIdIsEqualToId(RatingId ratingId, List<CourseRating> ratingList, int index) {
        if (ratingId.getStudentId() == ratingList.get(index).getStudent().getId() &&
                ratingId.getCourseId() == ratingList.get(index).getCourse().getId()){
            return true;
        }
        return false;
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
