import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.PieChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartPanel extends XChartPanel implements ActionListener{

    private GUI gui;
    private PieChart pc;

    

    public PieChartPanel(PieChart pc, GUI gui)
    {
        super(pc);
        this.gui = gui;
        this.pc = pc;
    }

    public void actionPerformed(ActionEvent e)
    {
        if()
        pc.updatePieSeries("Healthy", gui.getStats().getNumHealthy());
        pc.updatePieSeries("Sick", gui.getStats().getNumSick());
        pc.updatePieSeries("Recovered", gui.getStats().getNumRecovered());
        pc.updatePieSeries("Dead", gui.getStats().getNumDead());

        repaint();

    }
}
