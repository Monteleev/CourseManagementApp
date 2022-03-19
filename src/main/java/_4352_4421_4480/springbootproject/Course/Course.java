package _4352_4421_4480.springbootproject.Course;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String name;
    private String syllabus;
    private Integer year;
    private Integer semester;

    public Course() {
    }

    public Course(Long id, String name, String syllabus, Integer year, Integer semester) {
        this.id = id;
        this.name = name;
        this.syllabus = syllabus;
        this.year = year;
        this.semester = semester;
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }
}
