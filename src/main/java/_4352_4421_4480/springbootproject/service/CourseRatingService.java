package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.RatingId;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import _4352_4421_4480.springbootproject.dao.CourseRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseRatingService {
    private final CourseRatingRepository courseRatingRepository;

    @Autowired
    public CourseRatingService(CourseRatingRepository courseRatingRepository) {
        this.courseRatingRepository = courseRatingRepository;
    }

    public CourseRating getRatingById(RatingId id) {
        return courseRatingRepository.findCourseRatingById(id).get();
    }

    public void addNewCourseRating(CourseRating courseRating) {
        RatingId ratingId = new RatingId(courseRating.getCourse().getId(), courseRating.getStudent().getId());
        Optional<CourseRating> courseRatingOptional = courseRatingRepository.findCourseRatingById(ratingId);
        if (courseRatingOptional.isPresent())
        {
            throw new IllegalStateException("This student is already enrolled in this course!");
        }
        courseRatingRepository.save(courseRating);
    }

    public void registerGrade(CourseRating courseRating) {
        courseRating.getCourse().registerGrade(courseRating);
        courseRating.getStudent().registerGrade(courseRating);
    }

    public CourseRating updateCourseRating(CourseRating courseRating) {
        return courseRatingRepository.save(courseRating);
    }


    public void deleteRating(RatingId ratingId) {
        CourseRating courseRating = courseRatingRepository.findCourseRatingById(ratingId).get();
        courseRating.deleteRating(ratingId);
        courseRating.getCourse().deleteStudent(courseRating.getStudent());
        courseRatingRepository.deleteCourseRatingById(ratingId);
    }

}
