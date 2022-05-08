package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.service.*;
import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.RatingId;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//@RestController
@Controller
public class CourseController {
    private final CourseService courseService;
    private StudentService studentService;
    private CourseRatingService courseRatingService;

    public CourseController(CourseService courseService, StudentService studentService, CourseRatingService courseRatingService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.courseRatingService = courseRatingService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model)
    {
        model.addAttribute("courses",courseService.getCourse());
        return "courses";
    }

    @GetMapping("/courses/syllabus/{id}")
    public String listCoursesSyllabus(@PathVariable Long id,Model model)
    {
        model.addAttribute("courses",courseService.getCourseById(id));
        return "course_syllabus";
    }

    @GetMapping("/courses/students/{id}")
    public String listStudents(@PathVariable Long id,Model model)
    {
        model.addAttribute("courses",courseService.getCourseById(id));
        return "course_students";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model)
    {
        Course course = new Course();
        model.addAttribute("course",course);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addNewCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{courseId}")
    public String editCourseForm(@PathVariable Long courseId, Model model)
    {
        model.addAttribute("course",courseService.getCourseById(courseId));
        model.addAttribute("enrolledStudents", courseService.getCourseById(courseId).getEnrolledStudents());
        return "edit_course";
    }

    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id)
    {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("course") Course course) {

        // get student from database by id
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setId(id);
        existingCourse.setName(course.getName());
        existingCourse.setSyllabus(course.getSyllabus());
        existingCourse.setYear(course.getYear());
        existingCourse.setSemester(course.getSemester());

        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }


    @PostMapping("/courses/students/{id}")
    public String enrollStudentToCourse(
            @PathVariable("id") Long courseId,
            @RequestParam(value = "studentId") Long studentId,
            @ModelAttribute("course") Course course1,
            Model model
    ) {
        model.addAttribute("studentId", studentId);
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);

        // Create an empty rating for the student enrolled
        RatingId ratingId = new RatingId(courseId, studentId);
        CourseRating courseRating = new CourseRating(ratingId, course, student, "-");
        courseRatingService.addNewCourseRating(courseRating);
        courseRatingService.registerGrade(courseRating);

        course.enrollStudent(student);
        courseService.updateCourse(course);
        studentService.updateStudent(student);

        return "redirect:/courses/students/" + courseId;
    }

    @Transactional
    @GetMapping("/courses/students/{course_id}/remove/{student_id}")
    public String removeStudentFromCourse(@PathVariable("course_id") Long courseId,
                                          @PathVariable("student_id") Long studentId) {
        Course course = courseService.getCourseById(courseId);
        RatingId ratingId = new RatingId(courseId, studentId);

        courseRatingService.deleteRating(ratingId);
        courseService.updateCourse(course);
        return "redirect:/courses/students/" + courseId;
    }

    @GetMapping("/courses/stats/{course_id}")
    public String showStatistics(@PathVariable("course_id") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        StatisticStrategy statisticStrategy = new StatisticStrategy();
        Map<String,Double> res = courseService.getResults(statisticStrategy, course);
        model.addAttribute("result", res);
        model.addAttribute("course", course);

        return "statistics";
    }

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception exception, Model model) {
        model.addAttribute("error_msg", exception.getMessage());

        return "default_exception";
    }

}