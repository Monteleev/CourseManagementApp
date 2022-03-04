package ServiceLayer;


public interface CourseService {

    public List<Course> findCourseByInstructorLogin(String instructorLogin);

    public  void delete(int id);

    public void save(Course course);

    public void update(Course course);

}
