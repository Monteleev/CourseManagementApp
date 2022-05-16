package _4352_4421_4480.springbootproject.dao;

import _4352_4421_4480.springbootproject.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void testStudentDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(studentRepository);
    }

    @Test
    void testFindByIdReturnsStudent() {
        Optional<Student> optionalStudent = studentRepository.findById(1L);
        Student storedStudent = optionalStudent.get();
        Assertions.assertNotNull(storedStudent);
        Assertions.assertEquals("Vassiliadis Panagiotis", storedStudent.getName());
    }
}
