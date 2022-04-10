package _4352_4421_4480.springbootproject.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private final StudentService studentService;

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

    @GetMapping("students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setName(student.getName());
        existingStudent.setYearOfRegistration(student.getYearOfRegistration());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }


}
