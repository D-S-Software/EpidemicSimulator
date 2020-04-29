import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel2 extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart2;
    private int count = 99;

    /**Creates a chart panel for the total cases line graph
     *
     * @param c
     * @param gui
     */
    public XYChartPanel2(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart2 = c;
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
     * @param e
     */
    public void actionPerformed(ActionEvent e)
    {
        count++;
        if(count == 100) //TODO 100 not 99?
        {
            xychart2.updateXYSeries("cases", gui.getStats().getTimeList(), gui.getStats().getCasesList(),null );

            repaint();
            count = 0;
        }
        if(gui.getStats().getNumSick() == 0 || gui.getStats().getNumHealthy() == 0)
            count = 101;
    }
}
