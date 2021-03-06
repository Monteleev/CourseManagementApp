package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;
import _4352_4421_4480.springbootproject.entity.CourseRating;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.*;


public class StatisticStrategy implements IStatisticStrategy {

    protected DescriptiveStatistics stats = new DescriptiveStatistics();

    private List<CourseRating> ratings = new ArrayList<>();
    private Map<String, Double> results = new HashMap<>();
    private List<Double> median_list = new ArrayList<>();

    public StatisticStrategy(){}

    public Map<String,Double> calculateCourseStatistics(Course course){
        ratings = course.getRegisterStudentsGrades();
        prepareDataSet();
        calculateStatistics();
        calculateMedian();
        calculatePercentile();
        return results;
    }

    private void prepareDataSet(){
        String rating;
        for (CourseRating grade : ratings){
            rating = grade.getRating();
            if (!rating.equals("-")){
                stats.addValue(Double.valueOf(rating));
                median_list.add(Double.valueOf(rating));
            }
        }
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

        if (median_list.size() == 0)
            throw new IndexOutOfBoundsException("No students grades in this course!");

        if(median_list.size() % 2 == 0){

            medianLow = median_list.get((median_list.size()/2) - 1);
            medianHigh = median_list.get((median_list.size()/2));
            median = (medianLow + medianHigh)/2;

        }
        else if(median_list.size() == 2){
            medianLow = median_list.get(0);
            medianHigh = median_list.get(1);
            median = (medianLow + medianHigh)/2;
        }
        else {
            median = median_list.get(median_list.size() / 2);

        }
        results.put("Median", median);
    }

    public void calculatePercentile(){
        double percentile25,percentile50,percentile75;

        percentile25 = stats.getPercentile(25);
        percentile50 = stats.getPercentile(50);
        percentile75 = stats.getPercentile(75);

        results.put("25th Percentile", percentile25);
        results.put("50th Percentile", percentile50);
        results.put("75th Percentile", percentile75);
    }
}
