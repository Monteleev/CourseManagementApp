package ServiceLayer;

public interface StudenRegistrationDAO {

    public List<Course> findCourseByInstructorLogin(String instructorLogin);

    public  void delete(int id);

    public void save(Course course);

    public void update(Course course);

}
