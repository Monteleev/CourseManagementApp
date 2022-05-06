package _4352_4421_4480.springbootproject.entity;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class RatingId implements Serializable {

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "student_id")
    private Long studentId;

    public RatingId() {

    }

    public RatingId(Long courseId, Long studentId){
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getStudentId() {
        return studentId;
    }
}
