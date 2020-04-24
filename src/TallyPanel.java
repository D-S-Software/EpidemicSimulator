import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener {

    GUI gui;
    JLabel numHealthyLabel, numSickLabel, numRecoveredLabel, numDeadLabel;
    JButton switchGraph, toggle;
    boolean casesDisplayed = true;

    public TallyPanel(GUI gui, GridLayout gl)
    {
        super(gl);
        this.gui = gui;

        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        numHealthyLabel = new JLabel("Healthy: ");
        numHealthyLabel.setFont(numHealthyLabel.getFont ().deriveFont (18.0f));
        numHealthyLabel.setPreferredSize(new Dimension(20, 10));


        numSickLabel = new JLabel("Sick: ");
        numSickLabel.setFont(numSickLabel.getFont ().deriveFont (18.0f));
        numSickLabel.setPreferredSize(new Dimension(20, 10));

        numRecoveredLabel = new JLabel("Recovered: ");
        numRecoveredLabel.setFont(numRecoveredLabel.getFont ().deriveFont (18.0f));
        numRecoveredLabel.setPreferredSize(new Dimension(20, 10));

        numDeadLabel = new JLabel("Dead: ");
        numDeadLabel.setFont(numDeadLabel.getFont ().deriveFont (18.0f));
        numDeadLabel.setPreferredSize(new Dimension(20, 10));

        toggle = new JButton("Graph Mode");
        toggle.setFont(toggle.getFont ().deriveFont (18.0f));

        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(casesDisplayed)
                {
                    gui.getXyChartPanel2().setVisible(false);
                    gui.getXYChartPanel().setVisible(true);
                    casesDisplayed = false;
                }
                else
                {
                    gui.getXYChartPanel().setVisible(false);
                    gui.getXyChartPanel2().setVisible(true);
                    casesDisplayed = true;
                }
            }
        });

        switchGraph = new JButton("Switch Graph");
        switchGraph.setFont(switchGraph.getFont ().deriveFont (18.0f));

        switchGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(gui.getPieChartPanel().isVisible())
                {
                    gui.getXYChartPanel().setVisible(true);
                    gui.getPieChartPanel().setVisible(false);
                }
                else
                {
                    gui.getXYChartPanel().setVisible(false);
                    gui.getPieChartPanel().setVisible(true);
                }
            }
        });

        add(numHealthyLabel);
        add(numRecoveredLabel);
        add(toggle);
        add(numSickLabel);
        add(numDeadLabel);
        add(switchGraph);

        setBackground(Color.ORANGE); //TODO Add color for TallyPanel
    }

    public void actionPerformed(ActionEvent e)
    {
        numHealthyLabel.setText("Healthy: " + gui.getStats().getNumHealthy() + "  ");
        numSickLabel.setText("Sick: " + gui.getStats().getNumSick() + "  ");
        numRecoveredLabel.setText("Recovered: " + gui.getStats().getNumRecovered() + "  ");
        numDeadLabel.setText("Dead: " + gui.getStats().getNumDead() + "    ");

        if(gui.getXYChartPanel().isVisible() || gui.getXyChartPanel2().isVisible())
            toggle.setVisible(true);
        else
            toggle.setVisible(false);
    }

    public void setNumHealthyLabel(String s)
    {
        numHealthyLabel.setText(s);
    }
    public void setNumSickLabel(String s)
    {
        numSickLabel.setText(s);
    }
    public void setNumRecoveredLabel(String s)
    {
        numRecoveredLabel.setText(s);
    }
    public void setNumDeadLabel(String s)
    {
        numDeadLabel.setText(s);
    }
}
