import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener {

    GUI gui;

    public TallyPanel(GUI gui, GridLayout gl)
    {
        super(gl);
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e)
    {
        gui.getNumHealthyLabel().setText("NumHealthy: " + gui.getStats().getNumHealthy());
        gui.getNumSickLabel().setText("NumSick: " + gui.getStats().getNumSick());
        gui.getNumRecoveredLabel().setText("NumRecovered: " + gui.getStats().getNumRecovered());
        gui.getNumDeadLabel().setText("NumDead: " + gui.getStats().getNumDead());
    }
}
