package _4352_4421_4480.springbootproject.student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {
    @Id

    private int id;
    private String name;
    private int yearOfRegistration;
    private int semester;

    public Student() {

    }

    public Student(int id, String name, int yearOfRegistration) {
        this.name = name;
        this.id = id;
        this.yearOfRegistration = yearOfRegistration;
    }

    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfRegistration(int yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }
}

