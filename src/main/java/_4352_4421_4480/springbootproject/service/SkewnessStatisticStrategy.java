package _4352_4421_4480.springbootproject.service;

public class SkewnessStatisticStrategy extends StatisticStrategy{

    public SkewnessStatisticStrategy(){}

    @Override
    public void doActualCalculation() {

        res = stats.getSkewness();

    }
}
