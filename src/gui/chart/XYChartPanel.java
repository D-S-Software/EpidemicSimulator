package gui.chart;

import gui.GUI;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart;
    private int count = 99;

    /**Creates a chart panel for the all stats line graph
     *
     * @param c The chart that is being displayed
     * @param gui The gui object that is displaying the chart panel
     */
    public XYChartPanel(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart = c;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    /**
     * Resets the line graph after the reset button is clicked
     */
    public void resetXY()
    {
        count = 99;
        boolean show = isVisible();
        setVisible(false);
        xychart.updateXYSeries("Healthy", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("Sick", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("Recovered", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("Dead", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        setVisible(show);
    }

    /**
     * Updates the data in the line graph each tick and decides when to top updating (when there is no sick people)
     * @param e event each tick
     */
    public void actionPerformed(ActionEvent e)
    {
        count++;
        if(count == 100)
        {
            xychart.updateXYSeries("Healthy", gui.getStats().getTimeList(), gui.getStats().getHealthyList(),null );
            xychart.updateXYSeries("Sick", gui.getStats().getTimeList(), gui.getStats().getSickList(),null );
            xychart.updateXYSeries("Recovered",gui.getStats().getTimeList(), gui.getStats().getRecoveredList(),null );
            xychart.updateXYSeries("Dead",gui.getStats().getTimeList(), gui.getStats().getDeadList(),null ); //Maybe updateXYSeries has to be in chart class

            repaint();
            count = 0;
        }
        if(gui.getStats().getNumSick() == 0)
            count = 101;
    }
}
