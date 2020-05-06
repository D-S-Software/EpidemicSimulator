package gui.chart;

import gui.GUI;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.PieChart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChartPanel extends XChartPanel implements ActionListener{

    private GUI gui;
    private PieChart pc;
    private int graphDelay = 14; /** Update limiter to once every 15 ticks (once every 150 ms)*/
    private int count = graphDelay;

    /**Creates a pie chart panel
     *
     * @param pc The pie chart object being displayed on the panel
     * @param gui The gui object that is displaying the panel
     */
    public PieChartPanel(PieChart pc, GUI gui)
    {
        super(pc);
        this.gui = gui;
        this.pc = pc;
    }

    /**
     * Resets the pie chart once the simulation is being reset
     */
    public void resetPie()
    {
        count = graphDelay;
        boolean show = isVisible();
        setVisible(false);
        pc.updatePieSeries("Healthy", 0);
        pc.updatePieSeries("Sick", 0);
        pc.updatePieSeries("Recovered", 0);
        pc.updatePieSeries("Dead", 0);
        setVisible(show);
    }

    /**
     * Updates the contents of the pie chart every tick and decides when to top updating (when there is no sick people)
     * @param e event each tick
     */
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
        if(gui.getStats().getNumSick() == 0)
            count = graphDelay+1;
    }
}
