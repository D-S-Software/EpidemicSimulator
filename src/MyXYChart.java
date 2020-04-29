import Library.CustomColor;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;

public class MyXYChart {

    private XYChart xychart;
    String unit;

    /**Creates the line graph of all the data for the chart panel
     *
     * @return Returns the xyChart
     */
    public XYChart getXYChart()
    {
        // Create Chart
        xychart = new XYChartBuilder().width(400).height(300).title("Population Breakdown vs Time").xAxisTitle("Time (s)").yAxisTitle("Number of People").build();

        // Customize Chart //TODO: Organize
        xychart.getStyler().setLegendPosition(LegendPosition.OutsideS).setLegendLayout(Styler.LegendLayout.Horizontal);
        xychart.getStyler().setLegendBackgroundColor(CustomColor.JET);
        xychart.getStyler().setPlotBorderColor(CustomColor.JET).setPlotBackgroundColor(CustomColor.JET);
        xychart.getStyler().setPlotBackgroundColor(CustomColor.JET);
        xychart.getStyler().setChartFontColor(CustomColor.CHART_LABEL);
        xychart.getStyler().setChartBackgroundColor(CustomColor.JET);
        xychart.getStyler().setAnnotationsFontColor(CustomColor.CHART_LABEL);
        xychart.getStyler().setAxisTickMarksColor(CustomColor.CHART_LABEL);
        xychart.getStyler().setXAxisTickLabelsColor(CustomColor.CHART_LABEL);
        xychart.getStyler().setYAxisTickLabelsColor(CustomColor.CHART_LABEL);

        // Series
        //TODO Change the front size for the line graph
        XYSeries healthy = xychart.addSeries("Healthy",new double[]{0}, new double[]{0});
        healthy.setLineColor(CustomColor.HEALTHY);
        healthy.setMarkerColor(CustomColor.HEALTHY);

        XYSeries sick = xychart.addSeries("Sick", new double[]{0}, new double[]{0});
        sick.setLineColor(CustomColor.SICK);
        sick.setMarkerColor(CustomColor.SICK);

        XYSeries dead = xychart.addSeries("Dead", new double[]{0}, new double[]{0});
        dead.setLineColor(CustomColor.DEAD);
        dead.setMarkerColor(CustomColor.DEAD);

        XYSeries recovered = xychart.addSeries("Recovered", new double[]{0}, new double[]{0});
        recovered.setLineColor(CustomColor.RECOVERED);
        recovered.setMarkerColor(CustomColor.RECOVERED);

        return xychart;
    }
}
