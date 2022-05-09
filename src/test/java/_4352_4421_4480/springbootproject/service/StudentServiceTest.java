package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.dao.StudentRepository;
import _4352_4421_4480.springbootproject.entity.*;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    List<Student> students = new ArrayList<>();
    Student student1 = new Student(1L, "Dimitris", 2018);
    Student student2 = new Student(2L, "Grigoris", 2018);
    Student student3 = new Student(3L, "Spyros", 2018);

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void getStudentTest(){
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> studentList = studentService.getStudent();
        assertEquals(3, studentList.size());
        verify(studentRepository,times(1)).findAll();

    }

    @Test
    public void addStudentTest(){

        Student student4 = new Student(4L, "Giorgos", 2011);
        studentService.addNewStudent(student4);

        verify(studentRepository,times(1)).save(student4);
    }

    @Test
    public void getStudentByIdTest()
    {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));

        Student student = studentService.getStudentById(1L);


        assertEquals(1L, student.getId());
        assertEquals("Dimitris", student.getName());
        assertEquals(2018,student.getYearOfRegistration());
    }

    @Test
    public void deleteStudentByIdTest(){

        studentService.deleteStudentById(1L);
        verify(studentRepository,times(1)).deleteById(1L);

    }

    @Test
    public void updateCourseTest(){
        when(studentRepository.save(student1)).thenReturn(new Student(1L, "Dimitrios", 2017));
        Student student = studentService.updateStudent(student1);


        assertEquals(1L, student.getId());
        assertEquals("Dimitrios", student.getName());
        assertEquals(2017,student.getYearOfRegistration());
    }
}
