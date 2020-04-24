import Library.CustomColor;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.*;

import java.awt.*;

public class MyXYChart {

    private XYChart xychart;
    String unit;

    public XYChart getXYChart()
    {
        // Create Chart
        xychart = new XYChartBuilder().width(400).height(300).title("Population Breakdown vs Time").xAxisTitle("Time (s)").yAxisTitle("Number of People").build();

        // Customize Chart
        xychart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        xychart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        xychart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        xychart.getStyler().setChartBackgroundColor(CustomColor.JET);
        xychart.getStyler().setLegendBackgroundColor(CustomColor.JET);
        xychart.getStyler().setPlotBackgroundColor(CustomColor.JET);
        xychart.getStyler().setPlotBorderColor(CustomColor.JET);
        xychart.getStyler().setChartFontColor(CustomColor.WHITE);
        xychart.getStyler().setXAxisTickLabelsColor(CustomColor.WHITE);
        xychart.getStyler().setYAxisTickLabelsColor(CustomColor.WHITE);

        Color[] seriesColors = {CustomColor.SAVOY_BLUE, CustomColor.DARK_RED, CustomColor.SLATE_GRAY, CustomColor.EERIE_BLACK};
        xychart.getStyler().setSeriesColors(seriesColors);

        Marker[] markers = {new Circle(), new Diamond(), new TriangleUp(), new TriangleDown()};
        xychart.getStyler().setSeriesMarkers(markers);

        // Series
        xychart.addSeries("healthy", new double[]{0}, new double[]{0});
        xychart.addSeries("sick", new double[]{0}, new double[]{0});
        xychart.addSeries("recovered", new double[]{0}, new double[]{0});
        xychart.addSeries("dead", new double[]{0}, new double[]{0});

        return xychart;
    }
}
