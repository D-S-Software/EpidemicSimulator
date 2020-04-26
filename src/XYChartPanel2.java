import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XYChartPanel2 extends XChartPanel implements ActionListener {

    private GUI gui;
    private XYChart xychart2;
    private int count = 99;

    public XYChartPanel2(XYChart c, GUI gui)
    {
        super(c);
        this.gui = gui;
        xychart2 = c;
    }

    public void resetXY()
    {
        count = 99;
        boolean show = isVisible();
        setVisible(false);

        xychart2.updateXYSeries("cases", new ArrayList<Integer>(), new ArrayList<Integer>(),null );
        setVisible(show);
    }

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
