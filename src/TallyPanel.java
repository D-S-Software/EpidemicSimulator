import Library.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener {

    GUI gui;
    JLabel numHealthyLabel, numSickLabel, numRecoveredLabel, numDeadLabel, fillLater;

    public TallyPanel(GUI gui, GridLayout gl)
    {
        super(gl);
        this.gui = gui;

        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        numHealthyLabel = new JLabel("Healthy: ");
        numHealthyLabel.setFont(numHealthyLabel.getFont ().deriveFont (18.0f));
        numHealthyLabel.setForeground(CustomColor.BLACK);
        numHealthyLabel.setPreferredSize(new Dimension(20, 10));


        numSickLabel = new JLabel("Sick: ");
        numSickLabel.setFont(numSickLabel.getFont ().deriveFont (18.0f));
        numSickLabel.setForeground(CustomColor.BLACK);
        numSickLabel.setPreferredSize(new Dimension(20, 10));

        numRecoveredLabel = new JLabel("Recovered: ");
        numRecoveredLabel.setFont(numRecoveredLabel.getFont ().deriveFont (18.0f));
        numRecoveredLabel.setForeground(CustomColor.BLACK);
        numRecoveredLabel.setPreferredSize(new Dimension(20, 10));

        numDeadLabel = new JLabel("Dead: ");
        numDeadLabel.setFont(numDeadLabel.getFont ().deriveFont (18.0f));
        numDeadLabel.setForeground(CustomColor.BLACK);
        numDeadLabel.setPreferredSize(new Dimension(20, 10));

        fillLater = new JLabel();
        JButton switchGraph = new JButton("Switch Graph");
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
        add(fillLater);
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
