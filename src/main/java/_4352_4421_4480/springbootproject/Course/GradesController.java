package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;
import _4352_4421_4480.springbootproject.student.StudentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class GradesController {
    private final StudentService studentService;
    private final CourseService courseService;


    public GradesController(StudentService studentService, CourseService courseService, Course course) {
        this.studentService = studentService;
        this.courseService = courseService;

    }

    @PostMapping("/courses/students/{id}")
    public String addGrade(@PathVariable("student") Student student,
                           @PathVariable("course") Course course,
                           @RequestParam(value = "grade") int grade,
                           Model model){
        model.addAttribute("grade", grade);
        CourseRating courseRating = new CourseRating(course, student, grade);
        course.registerGradeStudent(courseRating);
        studentService.updateStudent(student);
        courseService.updateCourse(course);
        return "add_grade";
    }

}
