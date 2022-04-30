package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;
import _4352_4421_4480.springbootproject.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseRatingController {
    private CourseRatingService courseRatingService;
    private CourseService courseService;
    private StudentService studentService;


    public CourseRatingController(CourseRatingService courseRatingService,
                                  CourseService courseService,
                                  StudentService studentService) {
        this.courseRatingService = courseRatingService;
        this.courseService = courseService;
        this.studentService = studentService;

    }

    @PutMapping("/print/{id}")
    public String print(@PathVariable Long id){
        System.out.print(id);
        Course course = courseService.getCourseById(1L);
        Student student = studentService.getStudentById(1L);
        CourseGrade courseGrade = new CourseGrade(1L, 1L);
        CourseRating courseRating = new CourseRating(courseGrade, course, student, 5);
        System.out.print(courseRating);
        courseRatingService.addNewCourseRating(courseRating);

        return "redirect:/";
    }

}
