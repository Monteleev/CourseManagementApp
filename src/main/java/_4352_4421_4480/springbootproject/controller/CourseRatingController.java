package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.service.CourseRatingService;
import _4352_4421_4480.springbootproject.service.CourseService;
import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseGrade;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.entity.Student;
import _4352_4421_4480.springbootproject.service.StudentService;
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
