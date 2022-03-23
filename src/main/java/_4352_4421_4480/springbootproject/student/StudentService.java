package _4352_4421_4480.springbootproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isPresent())
        {
            throw new IllegalStateException("This student ID already exists");
        }
        studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
