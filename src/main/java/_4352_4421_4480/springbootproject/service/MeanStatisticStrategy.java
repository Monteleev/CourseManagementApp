package _4352_4421_4480.springbootproject.service;

public class MeanStatisticStrategy extends StatisticStrategy{

    public MeanStatisticStrategy(){}

    @Override
    public void doActualCalculation() {

        res = stats.getMean();

    }
}
