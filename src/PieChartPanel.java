import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.PieChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartPanel extends XChartPanel implements ActionListener{

    private GUI gui;
    private PieChart pc;
    /** Update limiter to once every 15 ticks (once every 150 ms)*/
    private int graphDelay = 14;
    private int count = graphDelay;

    public PieChartPanel(PieChart pc, GUI gui)
    {
        super(pc);
        this.gui = gui;
        this.pc = pc;
    }

    public void resetPie()
    {
        setVisible(false);
        pc.updatePieSeries("Healthy", 0);
        pc.updatePieSeries("Sick", 0);
        pc.updatePieSeries("Recovered", 0);
        pc.updatePieSeries("Dead", 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(count == graphDelay ) {
            pc.updatePieSeries("Healthy", gui.getStats().getNumHealthy());
            pc.updatePieSeries("Sick", gui.getStats().getNumSick());
            pc.updatePieSeries("Recovered", gui.getStats().getNumRecovered());
            pc.updatePieSeries("Dead", gui.getStats().getNumDead());

            repaint();

            count = 0;
        }
        count++;
    }
}
