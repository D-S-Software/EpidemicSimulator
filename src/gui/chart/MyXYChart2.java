package gui.chart;

import lib.CustomColor;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.*;

import java.awt.*;

public class MyXYChart2 {

    private XYChart xychart2;

    /**Creates the line graph for total cases for the chart panel
     *
     * @return Returns the xyChart
     */
    public XYChart getXYChart()
    {
        // Create chart
        xychart2 = new XYChartBuilder().width(400).height(300).title("Total Cases vs Time").xAxisTitle("Time (s)").yAxisTitle("Number of Cases").build();

        // Customize chart
        xychart2.getStyler().setLegendPosition(LegendPosition.OutsideS);
        xychart2.getStyler().setLegendPosition(LegendPosition.OutsideS);
        xychart2.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        xychart2.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        xychart2.getStyler().setChartBackgroundColor(CustomColor.JET);
        xychart2.getStyler().setLegendBackgroundColor(CustomColor.JET);
        xychart2.getStyler().setPlotBackgroundColor(CustomColor.JET);
        xychart2.getStyler().setPlotBorderColor(CustomColor.JET);
        xychart2.getStyler().setChartFontColor(CustomColor.CHART_LABEL);
        xychart2.getStyler().setAxisTickMarksColor(CustomColor.CHART_LABEL);
        xychart2.getStyler().setXAxisTickLabelsColor(CustomColor.CHART_LABEL);
        xychart2.getStyler().setYAxisTickLabelsColor(CustomColor.CHART_LABEL);

        Color[] seriesColors = {CustomColor.DARK_RED};
        xychart2.getStyler().setSeriesColors(seriesColors);

        Marker[] markers = {new Circle()};
        xychart2.getStyler().setSeriesMarkers(markers);

        // Series
        xychart2.addSeries("cases", new double[]{0}, new double[]{0});

        return xychart2;
    }
}
