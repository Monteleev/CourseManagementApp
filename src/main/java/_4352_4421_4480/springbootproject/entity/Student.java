package _4352_4421_4480.springbootproject.entity;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_registration")
    private int yearOfRegistration;

    @Column(name = "semester")
    private int semester;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    List<CourseRating> ratings;


    public Student() {

    }

    public Student(Long id, String name, int yearOfRegistration) {
        this.name = name;
        this.id = id;
        this.yearOfRegistration = yearOfRegistration;
        ratings = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfRegistration() {
        return yearOfRegistration;
    }

    public int getYearsOfStudies() {
        return Period.between(LocalDate.of(yearOfRegistration, 10, 1),
                LocalDate.of(LocalDate.now().getYear(), 10, 1)).getYears();
    }

    public int getSemester() {
        return getYearsOfStudies()*2;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfRegistration(int yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    public void registerGrade(CourseRating courseRating){
        ratings.add(courseRating);
    }

    public void registerStudent(Course course){ courses.add(course);}

    public List<CourseRating> getRegisterStudentsGrades(){
        return ratings;
    }


    public String getExamCourseGrade(Course course) {
        Long courseId = course.getId();
        for (CourseRating courseRate : ratings) {
            if (courseRate.getCourse().getId().equals(courseId)) {
                return courseRate.getExamRating();
            }
        }
        return "";
    }

    public String getCourseGrade(Course course) {
        Long courseId = course.getId();
        for (CourseRating courseRate : ratings) {
            if (courseRate.getCourse().getId().equals(courseId)) {
                return courseRate.getRating();
            }
        }
        return "";
    }

    public String getProjectCourseGrade(Course course) {
        Long courseId = course.getId();
        for (CourseRating courseRate : ratings) {
            if (courseRate.getCourse().getId().equals(courseId)) {
                return courseRate.getProjectRating();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name= " + name  +
                ", yearOfRegistration='" + yearOfRegistration + '\'' +
                ", semester=" + semester +
//                ", courses=" + courses +
                ", ratings=" + ratings +
                '}';
    }
}

