package _4352_4421_4480.springbootproject.student;

import _4352_4421_4480.springbootproject.Course.Course;
import _4352_4421_4480.springbootproject.Course.CourseRating;
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
        this.ratings = ratings;
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

    public int getYearOfStudies() {
        return Period.between(LocalDate.of(yearOfRegistration, 10, 1),
                LocalDate.of(LocalDate.now().getYear(), 10, 1)).getYears();
    }

    public int getSemester() {
        return getYearOfStudies()*2;
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
}

