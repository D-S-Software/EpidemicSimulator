import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart;
    private int graphDelay;
    private int count;

    public XYChartPanel(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart = c;
    }

    public void setGraphDelay(int delay)
    {
        graphDelay = delay;
        count = delay;
    }

    public void resetXY()
    {
        boolean show = isVisible();
        setVisible(false);
        xychart.updateXYSeries("healthy", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("sick", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("recovered", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        xychart.updateXYSeries("dead", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        setVisible(show);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(count == graphDelay)
        {
            xychart.updateXYSeries("healthy", gui.getStats().getTimeList(), gui.getStats().getHealthyList(),null );
            xychart.updateXYSeries("sick", gui.getStats().getTimeList(), gui.getStats().getSickList(),null );
            xychart.updateXYSeries("recovered",gui.getStats().getTimeList(), gui.getStats().getRecoveredList(),null );
            xychart.updateXYSeries("dead",gui.getStats().getTimeList(), gui.getStats().getDeadList(),null ); //Maybe updateXYSeries has to be in Chart class

            repaint();
            count = 0;
        }
        count++;
        if(gui.getStats().getNumSick() == 0)
            count = graphDelay+1;
    }
}
