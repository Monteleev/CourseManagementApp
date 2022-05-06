package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class StatisticStrategy implements IStatisticStrategy {

    protected DescriptiveStatistics stats = new DescriptiveStatistics();
    private List<CourseRating> ratings = new ArrayList<>();
    private Map<String,Double> results = new HashMap<>();
    double res;

    public StatisticStrategy(){}

    public double calculateStatistic(Course course){
        ratings = course.getRegisterStudentsGrades();
        prepareDataSet();
        doActualCalculation();
        saveResults(course.getName(), res);
        return res;
    }

    private void prepareDataSet(){
        for (CourseRating grade : ratings){
            stats.addValue(Double.valueOf(grade.getRating()));
        }
    }

    public void saveResults(String course, double result){
        results.put(course, result);
    }

    public Map<String,Double> getResults(){
        return results;
    }

    public abstract void doActualCalculation();
}
