package _4352_4421_4480.springbootproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String syllabus;
    private String description;
    private Integer year;
    private Integer semester;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "enrolled_students",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> enrolledStudents;

    @OneToMany(mappedBy = "course")
    private List<CourseRating> ratings;

    public Course() {
    }

    public Course(Long id, String name, String syllabus,String description, Integer year, Integer semester) {
        this.id = id;
        this.name = name;
        this.syllabus = syllabus;
        this.description = description;
        this.year = year;
        this.semester = semester;
        ratings = new ArrayList<>();
        enrolledStudents = new ArrayList<>();
    }

    public Course(String name) {
        this.name = name;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getEnrolledStudents(){
        return enrolledStudents;
    }

    public Integer getNumberOfEnrolledStudents(){
        return enrolledStudents.size();
    }

    public void enrollStudent(Student student){
        enrolledStudents.add(student);
    }

    public void deleteStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void registerGrade(CourseRating courseRating){
        ratings.add(courseRating);
    }

    public List<CourseRating> getRegisterStudentsGrades(){
        return ratings;
    }


    @Override
    public String toString() {
        return "Course {\n" +
                "   id= " + id + "\n" +
                "   name= " + name + "\n" +
                "   syllabus= " + syllabus + "\n" +
                "   year= " + year + "\n" +
                "   semester= " + semester + "\n" +
                "   enrolledStudents= " + enrolledStudents + "\n" +
                "   ratings= " + ratings + "\n" +
                '}';
    }
}
