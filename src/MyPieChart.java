import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieChart;

public class MyPieChart {

    private PieChart pieChart;

    public PieChart getMyPieChart()
    {
        pieChart = new PieChartBuilder().width(400).height(400).title("Percent per Condition").build();

        pieChart.addSeries("Healthy", 0);
        pieChart.addSeries("Sick",0);
        pieChart.addSeries("Recovered", 0);
        pieChart.addSeries("Dead",0);

        return pieChart;
    }
}
