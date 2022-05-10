package _4352_4421_4480.springbootproject.service;

import _4352_4421_4480.springbootproject.entity.Course;

import java.util.Map;

public interface IStatisticStrategy {

    Map<String,Double> calculateCourseStatistics(Course course);
}
