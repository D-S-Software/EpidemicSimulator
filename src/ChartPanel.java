import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChartPanel extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart;
    private int count;

    public ChartPanel(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart = c;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(count == 99)
        {
            xychart.updateXYSeries("healthy", gui.getStats().getTimeList(), gui.getStats().getHealthyList(),null );
            xychart.updateXYSeries("sick", gui.getStats().getTimeList(), gui.getStats().getSickList(),null );
            xychart.updateXYSeries("recovered",gui.getStats().getTimeList(), gui.getStats().getRecoveredList(),null );
            xychart.updateXYSeries("dead",gui.getStats().getTimeList(), gui.getStats().getDeadList(),null ); //Maybe updateXYSeries has to be in Chart class

            repaint();
            count = 0;
        }
        count++;
    }
}
