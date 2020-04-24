import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Chart{

    private Statistics stats;
    private XYChart xychart;
    private GUI gui;

    public Chart()
    {
        //this.gui = gui;
    }

   // public void setStats(Statistics stats) //TODO: Not needed?
    {
      //  this.stats = stats;
    }


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


    public XYChart buildXYChart()
    {
        return xychart;
    }


    //double[] arrTemp = {0,1,2};

    //XYChart chart = new XYChartBuilder().width(600).height(400).title("Chart").xAxisTitle("Time").yAxisTitle("% of Pop").build();

    //XYChart myChart = QuickChart.getChart("Title","x","y","y(x)",arrTemp,arrTemp);


    //private SwingWrapper<XYChart> sw = new SwingWrapper<>(myChart);

}
