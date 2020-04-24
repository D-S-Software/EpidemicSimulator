import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel2 extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart2;
    private int graphDelay;
    private int count;

    public XYChartPanel2(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart2 = c;
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

        xychart2.updateXYSeries("cases", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        setVisible(show);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(count == graphDelay)
        {
            xychart2.updateXYSeries("cases", gui.getStats().getTimeList(), gui.getStats().getCasesList(),null );

            repaint();
            count = 0;
        }
        count++;
        if(gui.getStats().getNumSick() == 0)
            count = graphDelay+1;
    }
}
