package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseGrade;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.service.CourseRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseRatingController {
    private CourseRatingService courseRatingService;


    public CourseRatingController(CourseRatingService courseRatingService) {
        this.courseRatingService = courseRatingService;

    }

    @PostMapping("/courses/students/addgrade/{id}")
    public String addNewGrade(@PathVariable("id") Long courseId,
                        @RequestParam(value = "newGrade") Integer newGrade,
                        Model model){
        model.addAttribute("newGrade", newGrade);

//        CourseGrade ratingId = new CourseGrade(courseId, studentId);
//        CourseRating courseRating = new CourseRating(ratingId, course, student, newGrade);
        System.out.print(newGrade);

        return "redirect:/courses/students/" + courseId;
    }

}
