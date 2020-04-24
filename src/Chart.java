import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Chart{

    private XYChart xychart;

    public XYChart getXYChart()
    {
        // Create Chart
        xychart = new XYChartBuilder().width(400).height(300).title("Population Breakdown vs TIme").xAxisTitle("Time").yAxisTitle("% of Pop").build();

        // Customize Chart
        xychart.getStyler().setLegendPosition(LegendPosition.InsideNE);

        // Series
        xychart.addSeries("healthy", new double[]{0}, new double[]{0});
        xychart.addSeries("sick", new double[]{0}, new double[]{0});
        xychart.addSeries("dead", new double[]{0}, new double[]{0});
        xychart.addSeries("recovered", new double[]{0}, new double[]{0});

        return xychart;
    }
}
