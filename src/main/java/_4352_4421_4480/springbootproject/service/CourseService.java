package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourse()
    {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course)
    {
        Optional<Course> courseOptional =
                courseRepository.findById(course.getId());
        if(courseOptional.isPresent())
        {
            throw new IllegalStateException("Id Taken. Try again!");
        }

        courseRepository.save(course);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course getCourseById(Long id)
    {
        return courseRepository.findById(id).get();
    }

    public Course updateCourse(Course course)
    {
        return courseRepository.save(course);
    }
}
