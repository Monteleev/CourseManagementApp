package ServiceLayer;

import java.util.*;


public class CourseServiceImpl implements CourseService {

    private List<StatisticStrategy> statCalculationStrategies;
    private CourseDAO courseDAO;

    public CourseServiceImpl(){}
    @Override
    public List<Course> findCourseByInstructorLogin(String instructorLogin) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save(Course course) {

    }

    @Override
    public void update(Course course) {

    }

    public Map<String,Double> getCourseStatistics(){
        return null;
    }

    public List<StatisticStrategy> getStatCalculationStrategies(){
        return null;
    }

    public void setStatCalculationStrategies(List<StatisticStrategy>  statCalculationStrategies){
        this.statCalculationStrategies = statCalculationStrategies;
    }
}
