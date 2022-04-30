package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseRatingRepository courseRatingRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         StudentRepository studentRepository,
                         CourseRatingRepository courseRatingRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseRatingRepository = courseRatingRepository;
    }

    public List<Course> getCourse()
    {
        return courseRepository.findAll();
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public CourseRatingRepository getCourseRatingRepository() {
        return courseRatingRepository;
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
