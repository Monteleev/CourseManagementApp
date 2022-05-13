package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.entity.RatingId;
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

    @PostMapping("/courses/students/{course_id}/grade/{student_id}")
    public String addNewGrade(@PathVariable("course_id") Long courseId,
                              @PathVariable("student_id") Long studentId,
                        @RequestParam(value = "newExamGrade") Double newExamGrade,
                        @RequestParam(value = "newProjectGrade") Double newProjectGrade,
                        Model model){
        model.addAttribute("newExamGrade", newExamGrade);
        model.addAttribute("newProjectGrade", newProjectGrade);

        CourseRating existingRating =
                courseRatingService.getRatingById(new RatingId(courseId, studentId));
        existingRating.setExamRating(String.valueOf(newExamGrade));
        existingRating.setProjectRating(String.valueOf(newProjectGrade));
        existingRating.calcRating(String.valueOf(newExamGrade),String.valueOf(newProjectGrade));
        courseRatingService.updateCourseRating(existingRating);

        return "redirect:/courses/students/" + courseId;
    }

}
