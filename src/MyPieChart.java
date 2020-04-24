import Library.CustomColor;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.Styler;


public class MyPieChart {

    private PieChart pieChart;
//TODO Change the font size for the pie chart
    public PieChart getMyPieChart()
    {
        pieChart = new PieChartBuilder().width(400).height(400).title("Percent per Condition").build();
        pieChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        pieChart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        pieChart.getStyler().setChartBackgroundColor(CustomColor.JET);
        pieChart.getStyler().setLegendBackgroundColor(CustomColor.JET);
        pieChart.getStyler().setChartFontColor(CustomColor.WHITE);
        pieChart.getStyler().setPlotBackgroundColor(CustomColor.JET);
        pieChart.getStyler().setPlotBorderColor(CustomColor.JET);

        PieSeries healthySeries = pieChart.addSeries("Healthy", 0);
        healthySeries.setFillColor(CustomColor.HEALTHY);

        PieSeries sickSeries = pieChart.addSeries("Sick",0);
        sickSeries.setFillColor(CustomColor.SICK);

        PieSeries recoveredSeries = pieChart.addSeries("Recovered", 0);
        recoveredSeries.setFillColor(CustomColor.RECOVERED);

        PieSeries deadSeries = pieChart.addSeries("Dead",0);
        deadSeries.setFillColor(CustomColor.DEAD);

        return pieChart;
    }
}
