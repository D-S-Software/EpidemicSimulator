package gui;

import lib.CustomColor;
import backend.simboard.SimBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimBoardPanel extends JPanel implements ActionListener
{
    SimBoard simBoard;
    Color background = CustomColor.JET;
    Graphics2D g2D;
    boolean reset = false;

    /**
     * Creates a panel for the simboard to attach to.
     */
    public SimBoardPanel()
    {
        super();
        setBackground(background);
    }

    /**
     * Sets the simboard to the panel once the simulation has started and the chosen simboard has been created.
     * @param simBoard simboard object being set to the simboard panel
     */
    public void setBoard(SimBoard simBoard)
    {
        this.simBoard = simBoard;
    }

    /**
     * Resets the panel to remove the dots representing the people on the board
     */
    public void resetBoard()
    {
        g2D.setColor(background);
        g2D.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

    /**
     * The bounds from the window size are updated and sent to the simboard.
     * @param g Graphics object
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;

        if(simBoard != null)
        {
            simBoard.updateDimensList(this.getBounds()); // Updates The simboard to the dimensions of the SimBoardPanel
            simBoard.updateBoard(g2D); // Updates the simboard's pList(s) ; no simboard object needed
        }

        if(reset)
            resetBoard();
    }

    /**
     * Calls the paintComponet function
     * @param e event tick
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /** Getter and Setter Methods*/

    public void setReset(boolean reset)
    {
        this.reset = reset;
    }

    public SimBoard getSimBoard()
    {
        return simBoard;
    }
}
