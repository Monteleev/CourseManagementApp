package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;
import _4352_4421_4480.springbootproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

import java.util.Optional;

//@RestController
@Controller

public class CourseController {
    private final CourseService courseService;

    /* TODO: TO BE REMOVED */
    @Autowired
    StudentRepository studentRepository;

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
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addNewCourse(course);
        return "redirect:/courses";
    }
    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model)
    {
        model.addAttribute("course",courseService.getCourseById(id));
        model.addAttribute("enrolledStudents", courseService.getCourseById(id).getEnrolledStudents());
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

    @PutMapping("/courses/{courseId}/students/{studentId}")
    String enrollStudentToCourse(@PathVariable Long courseId,
                                 @PathVariable Integer studentId) {
        Course course = courseService.getCourseById(courseId);
        Student student = studentRepository.findById(studentId).get();
        course.enrollStudent(student);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @PutMapping("/change/{id}")
    void change(@PathVariable int id) throws IOException {
        File myObj = new File("filename.txt");
        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("Files in Java might be tricky, but it is fun enough!");
        myWriter.close();
        System.exit(1);
    }


}