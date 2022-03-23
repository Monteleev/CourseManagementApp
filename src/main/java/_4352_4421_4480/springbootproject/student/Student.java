package _4352_4421_4480.springbootproject.student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student {
    @Id

    private int id;
    private String name;

    public Student() {

    }

    public Student(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

