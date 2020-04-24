import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel2 extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart2;
    private int count = 99;
    private boolean slowGraph;

    public XYChartPanel2(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart2 = c;
    }

    public void isSlowGraph(boolean slowGraph)
    {
        this.slowGraph = slowGraph;
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
        if(slowGraph)
        {
            count++;
            if(count == 100)
            {
                xychart2.updateXYSeries("cases", gui.getStats().getTimeList(), gui.getStats().getCasesList(),null );

                repaint();
                count = 0;
            }
        }
        else
        {
            xychart2.updateXYSeries("cases", gui.getStats().getTimeList(), gui.getStats().getCasesList(),null );

            repaint();
        }
    }
}
