import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener {

    GUI gui;
    JLabel numHealthyLabel, numSickLabel, numRecoveredLabel, numDeadLabel, fillLater;
    boolean pieChart = true;

    public TallyPanel(GUI gui, GridLayout gl)
    {
        super(gl);
        this.gui = gui;

        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        numHealthyLabel = new JLabel("NumHealthy: ");
        numHealthyLabel.setPreferredSize(new Dimension(20, 10));


        numSickLabel = new JLabel("NumSick: ");
        numSickLabel.setPreferredSize(new Dimension(20, 10));

        numRecoveredLabel = new JLabel("NumRecovered: ");
        numRecoveredLabel.setPreferredSize(new Dimension(20, 10));

        numDeadLabel = new JLabel("NumDead: ");
        numDeadLabel.setPreferredSize(new Dimension(20, 10));

        fillLater = new JLabel();
        JButton switchGraph = new JButton("Switch Graph");

        switchGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(pieChart)
                {
                    gui.getXYChartPanel().setVisible(true);
                    gui.getPieChartPanel().setVisible(false);
                    pieChart = false;
                }
                else
                {
                    gui.getXYChartPanel().setVisible(false);
                    gui.getPieChartPanel().setVisible(true);
                    pieChart = true;
                }
            }
        });

        add(numHealthyLabel);
        add(numRecoveredLabel);
        add(fillLater);
        add(numSickLabel);
        add(numDeadLabel);
        add(switchGraph);

        setBackground(Color.ORANGE);
    }

    public void actionPerformed(ActionEvent e)
    {
        numHealthyLabel.setText("NumHealthy: " + gui.getStats().getNumHealthy());
        numSickLabel.setText("NumSick: " + gui.getStats().getNumSick());
        numRecoveredLabel.setText("NumRecovered: " + gui.getStats().getNumRecovered());
        numDeadLabel.setText("NumDead: " + gui.getStats().getNumDead());
    }

    public JLabel getNumHealthyLabel()
    {
        return numHealthyLabel;
    }

    public JLabel getNumSickLabel() {
        return numSickLabel;
    }

    public JLabel getNumRecoveredLabel() {
        return numRecoveredLabel;
    }

    public JLabel getNumDeadLabel() {
        return numDeadLabel;
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
