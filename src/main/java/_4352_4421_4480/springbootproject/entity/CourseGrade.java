package _4352_4421_4480.springbootproject.entity;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;


@Embeddable
public class CourseGrade implements Serializable {

    @Column(name = "course_id")
    Long courseId;

    @Column(name = "student_id")
    Long studentId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation

    public CourseGrade() {

    }

    public CourseGrade(Long courseId, Long studentId){
        this.courseId = courseId;
        this.studentId = studentId;
    }



}
