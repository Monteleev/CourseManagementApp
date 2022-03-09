package _4352_4421_4480.springbootproject.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository
        extends JpaRepository<Course,Long>
{

}
