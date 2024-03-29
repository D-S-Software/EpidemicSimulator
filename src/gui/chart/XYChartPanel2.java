package gui.chart;

import gui.GUI;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel2 extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart2;
    private int count = 99;

    /**Creates a chart panel for the total cases line graph
     *
     * @param c The chart that is being displayed
     * @param gui The gui object that is displaying the chart panel
     */
    public XYChartPanel2(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart2 = c;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    /**
     * Resets the graph when the reset button is clicked
     */
    public void resetXY()
    {
        count = 99;
        boolean show = isVisible();
        setVisible(false);

        xychart2.updateXYSeries("cases", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        setVisible(show);
    }

    /**
     * Updates the data in the graph each tick and decides when to top updating (when there is no sick people)
     * @param e event each tick
     */
    public void actionPerformed(ActionEvent e)
    {
        count++;
        if(count == 100)
        {
            xychart2.updateXYSeries("cases", gui.getStats().getTimeList(), gui.getStats().getCasesList(),null );

            repaint();
            count = 0;
        }
        if(gui.getStats().getNumSick() == 0 || gui.getStats().getNumHealthy() == 0)
            count = 101;
    }
}
