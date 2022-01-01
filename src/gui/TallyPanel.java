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
    JLabel numHealthyLabel, numSickLabel, numRecoveredLabel, numDeadLabel, rNotLabel, rLabel, filler;
    JButton switchGraph, toggle;
    Formatter formatter;
    boolean showCases;

    /**Creates a tally panel object to be displayed on the main gui
     *
     * @param gui The gui object that displays the tally panel
     */
    public TallyPanel(GUI gui)
    {
        super();
        this.gui = gui;
        formatter = new Formatter();
        formatter.formatPanel(this, CustomColor.SPACE_CADET_LIGHT, new Rectangle(8,8,8,8), new GridLayout(3,3));

        numHealthyLabel = new JLabel("Healthy: ");
        formatter.formatLabel(numHealthyLabel, CustomColor.SILVER, 15.0f, new Dimension(20,10));

        numSickLabel = new JLabel("Sick: ");
        formatter.formatLabel(numSickLabel, CustomColor.SILVER,15.0f,new Dimension(20,10));

        numRecoveredLabel = new JLabel("Recov.: ");
        formatter.formatLabel(numRecoveredLabel,CustomColor.SILVER,15.0f,new Dimension(20,10));

        numDeadLabel = new JLabel("Dead: ");
        formatter.formatLabel(numDeadLabel,CustomColor.SILVER,15.0f,new Dimension(20,10));

        rNotLabel = new JLabel("Ro Value: ");
        formatter.formatLabel(rNotLabel, CustomColor.SILVER,15.0f,new Dimension(20,10));

        rLabel = new JLabel("R Value: ");
        formatter.formatLabel(rLabel,CustomColor.SILVER,15.0f,new Dimension(20,10));

        filler = new JLabel();
        formatter.formatLabel(filler,CustomColor.SILVER,16.0f,null);

        toggle = new JButton("Graph Mode");
        formatter.formatButton(toggle, CustomColor.BUTTON, CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,15.0f,"Switch to Population Breakdown");
        toggle.setVisible(false);

        toggle.addActionListener(e -> {

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
        });

        switchGraph = new JButton("Switch Graph");
        formatter.formatButton(switchGraph, CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,15.0f,"Switch to Line Graph");
        switchGraph.addActionListener(e -> switchGraphButton());

        add(numHealthyLabel);
        add(numRecoveredLabel);
        add(filler);
        add(numSickLabel);
        add(numDeadLabel);
        add(toggle);
        add(rNotLabel);
        add(rLabel);
        add(switchGraph);
    }

    /**
     * Switches the graph between the line and pie charts
     */
    public void switchGraphButton()
    {
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

        DecimalFormat df2 = new DecimalFormat("##.###");
        df2.setRoundingMode(RoundingMode.CEILING);

        rNotLabel.setText("Ro Value: " + df2.format(gui.getStats().getAverageRoValue()) + "    ");
        rLabel.setText("R Value: " + df2.format(gui.getStats().getrValue()) + "    ");

        toggle.setVisible(gui.getXYChartPanel().isVisible() || gui.getXYChartPanel2().isVisible());
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
