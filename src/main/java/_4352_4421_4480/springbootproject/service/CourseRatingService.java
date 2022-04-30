package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.CourseGrade;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.dao.CourseRatingRepository;
import _4352_4421_4480.springbootproject.dao.CourseRepository;
import _4352_4421_4480.springbootproject.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseRatingService {
    private final CourseRatingRepository courseRatingRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @Autowired
    public CourseRatingService(CourseRatingRepository courseRatingRepository,
                               CourseRepository courseRepository,
                               StudentRepository studentRepository) {
        this.courseRatingRepository = courseRatingRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public void addNewCourseRating(CourseRating courseRating) {
        CourseGrade courseGrade = new CourseGrade(courseRating.getCourse().getId(), courseRating.getStudent().getId());
        Optional<CourseRating> courseRatingOptional = courseRatingRepository.findCourseRatingById(courseGrade);
        if (courseRatingOptional.isPresent())
        {
            throw new IllegalStateException("This student already has a grade assigned");
        }
        courseRatingRepository.save(courseRating);
    }

    public void registerGrade(CourseRating courseRating) {
        courseRating.getCourse().registerGrade(courseRating);
        courseRating.getStudent().registerGrade(courseRating);
    }

}
