package _4352_4421_4480.springbootproject.Course;

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
            throw new IllegalStateException("Id Taken");
        }

        courseRepository.save(course);
        System.out.println(course);
    }

    public void deleteCourse(Long courseId) {
        boolean cId = courseRepository.existsById(courseId);
        if(!cId)
        {
            throw new IllegalStateException("Course with id " + courseId + " does not exist!");
        }

        courseRepository.deleteById((courseId));
    }

    @Transactional
    public void updateCourse(Long courseId, String name, String syllabus, Integer year, Integer semester)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new IllegalStateException(
                        "Course with id " + courseId + " does not exist!"));

        if(name != null && name.length() >0)
        {
            course.setName(name);
        }

        if(syllabus != null && syllabus.length() >0)
        {
            course.setSyllabus(syllabus);
        }

        if(year != null && year <= 5 && year>0)
        {
            course.setYear(year);
        }

        if(semester != null && semester <= 10 && semester>0)
        {
            course.setSemester(semester);
        }
    }
}
