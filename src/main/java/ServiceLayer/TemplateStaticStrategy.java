package ServiceLayer;

public abstract class TemplateStaticStrategy implements  StatisticStrategy{

    public TemplateStaticStrategy(){}

    public abstract void doActualCalculation();

    private  void prepareDataSet(){}

    @Override
    public double calculateStatistic(Course course) {
        return 0;
    }
}
