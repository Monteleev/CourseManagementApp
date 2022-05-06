package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.*;


public class StatisticStrategy {

    protected DescriptiveStatistics stats = new DescriptiveStatistics();

    private List<CourseRating> ratings = new ArrayList<>();
    private Map<String, Double> results = new HashMap<>();
    private List<Double> median_list = new ArrayList<>();

    public StatisticStrategy(){}

    public void calculateCourseStatistics(Course course){
        ratings = course.getRegisterStudentsGrades();
        prepareDataSet();
        calculateStatistics();
        calculateMedian();
        calculatePercantile();
    }

    private void prepareDataSet(){
        for (CourseRating grade : ratings){
            stats.addValue(Double.valueOf(grade.getRating()));
            median_list.add(Double.valueOf(grade.getRating()));
        }
    }

    public Map<String,Double> getResults(){
        return results;
    }

    public void calculateStatistics(){
        results.put("Min", stats.getMin());
        results.put("Max", stats.getMax());
        results.put("Mean", stats.getMean());
        results.put("Standard Deviation", stats.getStandardDeviation());
        results.put("Variance", stats.getVariance());
        results.put("Skewness", stats.getSkewness());
        results.put("Kurtosis", stats.getKurtosis());
    }

    public void calculateMedian(){
        double median,medianLow, medianHigh;
        Collections.sort(median_list);
        if(median_list.size() % 2 == 0){
            medianLow = median_list.get((median_list.size()/2) - 1);
            medianHigh = median_list.get((median_list.size()/2) + 1);
            median = (medianLow + medianHigh)/2;
        }
        else{
            median = median_list.get(median_list.size()/2);
        }
        results.put("Median", median);

    }

    public void calculatePercantile(){
        double percantile25,percantile50,percantile75;

        percantile25 = stats.getPercentile(25);
        percantile50 = stats.getPercentile(50);
        percantile75 = stats.getPercentile(75);

        results.put("25th Percantile", percantile25);
        results.put("50th Percantile", percantile50);
        results.put("75th Percantile", percantile75);
    }
}
