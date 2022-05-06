package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.Student;
import _4352_4421_4480.springbootproject.service.CourseService;
import _4352_4421_4480.springbootproject.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    private final StudentService studentService;
    private Long currentCourseId;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getStudent());
        return "students";
    }

    @GetMapping("/students/add")
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addNewStudent(student);
        return "redirect:/students";
    }

    @GetMapping("students/{course_id}/edit/{student_id}")
    public String editStudentForm(@PathVariable("student_id") Long studentId,
                                  @PathVariable("course_id") Long courseId,
                                  Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        currentCourseId = courseId;
        return "edit_student";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @PostMapping("/students/{student_id}")
    public String updateStudent(@PathVariable("student_id") Long studentId,
                                @ModelAttribute("student") Student student,
                                Model model){

        Student existingStudent = studentService.getStudentById(studentId);
        existingStudent.setName(student.getName());
        existingStudent.setYearOfRegistration(student.getYearOfRegistration());

        studentService.updateStudent(existingStudent);
        return "redirect:/courses/students/" + currentCourseId;
    }


}
