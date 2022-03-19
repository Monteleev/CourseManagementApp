package _4352_4421_4480.springbootproject.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
//@Controller

public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public List<Course> getCourse() {
        return courseService.getCourse();
    }

    @PostMapping
    public void addCourse(@RequestBody Course course)
    {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId)
    {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String syllabus,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester)
    {
        courseService.updateCourse(courseId, name, syllabus, year, semester);
    }

    /* @GetMapping({"/showCourses", "/", "/list"})
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
    }*/
}