package _4352_4421_4480.springbootproject.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model)
    {
        model.addAttribute("courses",courseService.getCourse());
        return "courses";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model)
    {
        Course course = new Course();
        model.addAttribute("course",course);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveStudent(@ModelAttribute("course") Course course) {
        courseService.addNewCourse(course);
        return "redirect:/courses";
    }
    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model)
    {
        model.addAttribute("course",courseService.getCourseById(id));
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
        existingCourse.setYear(course.getYear());
        existingCourse.setSemester(course.getSemester());


        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

}