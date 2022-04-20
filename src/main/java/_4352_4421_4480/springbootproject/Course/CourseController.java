package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;
import _4352_4421_4480.springbootproject.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
@Controller
public class CourseController {
    private final CourseService courseService;
    //private final StudentService studentService;

    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        //this.studentService = studentService;
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
                               @ModelAttribute("course") Course course,
                               Model model) {

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
            Model model
    ) {
        model.addAttribute("studentId", studentId);
        Course course = courseService.getCourseById(courseId);
        Student student = courseService.getStudentRepository().findById(studentId).get();
        //CourseGrade courseGrade = new CourseGrade(courseService.getCourseById(courseId).getId(), studentService.getStudentById(studentId).getId());
        CourseRating courseRating = new CourseRating(course, student, 5);
        List<CourseRating> pekino = course.getRegisterStudentsGrades();
        course.registerGradeStudent(courseRating);
        course.enrollStudent(student);
        courseService.updateCourse(course);
        System.out.println(pekino.toString() + " h lista");
        System.out.println(courseRating + " to antikeimeno");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "redirect:/courses/students/" + courseId;
    }
}