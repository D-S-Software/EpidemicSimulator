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

    public SimBoardPanel()
    {
        super();
        setBackground(background);
    }

    public void setBoard(SimBoard simBoard)
    {
        this.simBoard = simBoard;
    }

    public void resetBoard()
    {
        g2D.setColor(background);
        g2D.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;

        if(simBoard != null)
        {
            simBoard.updateDimensList(this.getBounds()); /** Updates The simboard to the dimensions of the SimBoardPanel */
            simBoard.updateBoard(g2D); /** Updates the simboard's pList(s) ; no simboard object needed */
        }

        if(reset)
            resetBoard();
    }

    public void setReset(boolean reset)
    {
        this.reset = reset;
    }

    public SimBoard getSimBoard()
    {
        return simBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
