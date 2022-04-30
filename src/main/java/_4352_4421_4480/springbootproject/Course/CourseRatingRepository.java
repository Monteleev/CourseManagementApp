package _4352_4421_4480.springbootproject.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long>
{
    Optional<CourseRating> findCourseRatingById(CourseGrade courseGrade);
}
