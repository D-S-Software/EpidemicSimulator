package gui;

import lib.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TallyPanel extends JPanel implements ActionListener {

    GUI gui;
    JLabel numHealthyLabel, numSickLabel, numRecoveredLabel, numDeadLabel, rNotLabel;
    JButton switchGraph, toggle;
    boolean showCases;

    /**Creates a tally panel object to be displayed on the main gui
     *
     * @param gui The gui object that displays the tally panel
     * @param gl The gridLayout being used in this panel
     */
    public TallyPanel(GUI gui, GridLayout gl)
    {
        super(gl);
        this.gui = gui;

        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        numHealthyLabel = new JLabel("Healthy: ");
        numHealthyLabel.setFont(numHealthyLabel.getFont ().deriveFont (15.0f));
        numHealthyLabel.setForeground(CustomColor.ON_BUTTON_LABEL);
        numHealthyLabel.setPreferredSize(new Dimension(20, 10));


        numSickLabel = new JLabel("Sick: ");
        numSickLabel.setFont(numSickLabel.getFont ().deriveFont (15.0f));
        numSickLabel.setForeground(CustomColor.ON_BUTTON_LABEL);
        numSickLabel.setPreferredSize(new Dimension(20, 10));

        numRecoveredLabel = new JLabel("Recov.: ");
        numRecoveredLabel.setFont(numRecoveredLabel.getFont ().deriveFont (15.0f));
        numRecoveredLabel.setForeground(CustomColor.ON_BUTTON_LABEL);
        numRecoveredLabel.setPreferredSize(new Dimension(20, 10));

        numDeadLabel = new JLabel("Dead: ");
        numDeadLabel.setFont(numDeadLabel.getFont ().deriveFont (15.0f));
        numDeadLabel.setForeground(CustomColor.ON_BUTTON_LABEL);
        numDeadLabel.setPreferredSize(new Dimension(20, 10));

        rNotLabel = new JLabel("Ro Value: ");
        rNotLabel.setFont(rNotLabel.getFont ().deriveFont (15.0f));
        rNotLabel.setForeground(CustomColor.ON_BUTTON_LABEL);
        rNotLabel.setPreferredSize(new Dimension(20, 10));

        toggle = new JButton("Graph Mode");
        toggle.setFont(toggle.getFont ().deriveFont (15.0f));
        toggle.setForeground(CustomColor.ON_BUTTON_LABEL);
        toggle.setBackground(CustomColor.BUTTON);
        toggle.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        toggle.setToolTipText("Switch to Population Breakdown");
        toggle.setVisible(false);

        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(gui.getXYChartPanel2().isVisible())
                {
                    toggle.setToolTipText("Switch to Total Cases");
                    gui.getXYChartPanel2().setVisible(false);
                    gui.getXYChartPanel().setVisible(true);
                    gui.getPieChartPanel().setVisible(false);
                    showCases = false;
                }
                else
                {
                    toggle.setToolTipText("Switch to Population Breakdown");
                    gui.getXYChartPanel().setVisible(false);
                    gui.getXYChartPanel2().setVisible(true);
                    gui.getPieChartPanel().setVisible(false);
                    showCases = true;
                }
            }
        });

        switchGraph = new JButton("Switch Graph");
        switchGraph.setFont(switchGraph.getFont ().deriveFont (15.0f));
        switchGraph.setBackground(CustomColor.BUTTON);
        switchGraph.setForeground(CustomColor.ON_BUTTON_LABEL);
        switchGraph.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        switchGraph.setToolTipText("Switch to Line Graph");

        switchGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(gui.getPieChartPanel().isVisible())
                {
                    switchGraph.setToolTipText("Switch to Pie chart");
                    gui.getPieChartPanel().setVisible(false);
                    if(showCases)
                    {
                        gui.getXYChartPanel2().setVisible(true);
                        gui.getXYChartPanel().setVisible(false);
                    }
                    else
                    {
                        gui.getXYChartPanel().setVisible(true);
                        gui.getXYChartPanel2().setVisible(false);
                    }
                }
                else
                {
                    switchGraph.setToolTipText("Switch to Line Graph");
                    gui.getPieChartPanel().setVisible(true);
                    gui.getXYChartPanel2().setVisible(false);
                    gui.getXYChartPanel().setVisible(false);
                }
            }
        });

        JLabel filler = new JLabel();
        JLabel filler2 = new JLabel();

        add(numHealthyLabel);
        add(numRecoveredLabel);
        add(filler);
        add(numSickLabel);
        add(numDeadLabel);
        add(toggle);
        add(rNotLabel);
        add(filler2);
        add(switchGraph);
    }

    /**
     * Sets the boolean parameter to decide which of the line graphs to currently display
     * @param showCases The boolean parameter
     */
    public void setShowCases(boolean showCases)
    {
        this.showCases = showCases;
    }

    /**
     * Shows the graph mode toggle button
     */
    public void showGraphModeButton()
    {
        toggle.setVisible(true);
    }

    /**
     * Updates the stats on the info board based on the stats object
     * @param e event each tick
     */
    public void actionPerformed(ActionEvent e)
    {
        numHealthyLabel.setText("Healthy: " + gui.getStats().getNumHealthy() + "  ");
        numSickLabel.setText("Sick: " + gui.getStats().getNumSick() + "  ");
        numRecoveredLabel.setText("Recov.: " + gui.getStats().getNumRecovered() + "  ");
        numDeadLabel.setText("Dead: " + gui.getStats().getNumDead() + "    ");

        DecimalFormat df = new DecimalFormat("##.###");
        df.setRoundingMode(RoundingMode.CEILING);

        rNotLabel.setText("Ro Value: " + df.format(gui.getStats().getAverageRValue()) + "    ");

        if(gui.getXYChartPanel().isVisible() || gui.getXYChartPanel2().isVisible())
            toggle.setVisible(true);
        else
            toggle.setVisible(false);
    }

    /** Getter and Setter Methods*/

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
