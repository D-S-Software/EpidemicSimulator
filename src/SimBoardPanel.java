import Library.CustomColor;

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
     * Creates a SimBoardPanel to display all of the people in the simulation and update their locations
     */
    public SimBoardPanel()
    {
        super();
        setBackground(background);
    }

    /**
     * Sets the simBoard object that is created and pairs it with the SimBoardPanel to be updated each tick
     * @param simBoard
     */
    public void setBoard(SimBoard simBoard)
    {
        this.simBoard = simBoard;
    }

    /**
     * Resets the Panel to clear it of people for the next simulation
     */
    public void resetBoard()
    {
        g2D.setColor(background);
        g2D.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

    /** Called every tick to update the dimensions for the SimBoardPanel and update the SimBoard
     *
     * @param g The graphics component used to draw the people
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;

        if(simBoard != null)
        {
            simBoard.updateAllDimens(this.getBounds()); /** Updates The simBoard to the dimensions of the SimBoardPanel */
            simBoard.updateBoard(g2D); /** Updates the simBoard's pList(s) ; no simBoard object needed */
        }

        if(reset)
            resetBoard();
    }

    /** Repaints the SimBoardPanel each tick
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /** Getters and Setter Methods*/

    public void setReset(boolean reset)
    {
        this.reset = reset;
    }

    public SimBoard getSimBoard()
    {
        return simBoard;
    }
}
