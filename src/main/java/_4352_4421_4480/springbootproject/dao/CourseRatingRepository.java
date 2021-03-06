package _4352_4421_4480.springbootproject.dao;

import _4352_4421_4480.springbootproject.entity.RatingId;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long>
{
    Optional<CourseRating> findCourseRatingById(RatingId ratingId);

    void deleteCourseRatingById(RatingId ratingId);
}
