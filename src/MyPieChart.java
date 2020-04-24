import Library.CustomColor;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;

public class MyPieChart {

    private PieChart pieChart;
//TODO Change the font size for the pie chart
    public PieChart getMyPieChart()
    {
        pieChart = new PieChartBuilder().width(400).height(400).title("Percent per Condition").build();
        pieChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        pieChart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        PieSeries healthySeries = pieChart.addSeries("Healthy", 0);
        healthySeries.setFillColor(CustomColor.SAVOY_BLUE);

        PieSeries sickSeries = pieChart.addSeries("Sick",0);
        sickSeries.setFillColor(CustomColor.CARMINE);

        PieSeries recoveredSeries = pieChart.addSeries("Recovered", 0);
        recoveredSeries.setFillColor(CustomColor.SILVER);

        PieSeries deadSeries = pieChart.addSeries("Dead",0);
        deadSeries.setFillColor(CustomColor.DIM_GRAY);

        return pieChart;
    }
}
