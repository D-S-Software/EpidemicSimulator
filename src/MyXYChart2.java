import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class MyXYChart2 {

    private XYChart xychart2;
    String unit;

    public XYChart getXYChart()
    {
        // Create Chart
        xychart2 = new XYChartBuilder().width(400).height(300).title("Total Cases vs Time").xAxisTitle("Time (.01s)").yAxisTitle("Population (#)").build();

        // Customize Chart
        xychart2.getStyler().setLegendPosition(LegendPosition.OutsideS);

        // Series TODO Add colors for the line graph
        //TODO Change the front size for the line graph
        xychart2.addSeries("cases", new double[]{0}, new double[]{0});

        return xychart2;
    }
}
