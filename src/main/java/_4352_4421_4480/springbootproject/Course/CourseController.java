package _4352_4421_4480.springbootproject.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@RestController
@RequestMapping(path = "api/v1/course")
@Controller
public class CourseController {

    @Autowired
    private CourseRepository eRepo;

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourse() {
        return courseService.getCourse();
    }

    @GetMapping({"/showCourses", "/", "/list"})
    public ModelAndView showCourses(Model model) {
        ModelAndView mav = new ModelAndView("list-courses");
        List<Course> listOfCourses = eRepo.findAll();
        mav.addObject("courses", listOfCourses);
        return mav;
    }

    @GetMapping("/addCourseForm")
    public ModelAndView addCourse() {
        ModelAndView mav = new ModelAndView("add-course-form");
        Course newCourse = new Course();
        mav.addObject("course", newCourse);
        return mav;
    }

    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute Course course) {
        eRepo.save(course);
        return "redirect:/list";
    }
}
