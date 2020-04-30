import Library.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimBoardPanel extends JPanel implements ActionListener
{
    SimBoard simBoard;
    Color background = CustomColor.JET;
    Graphics2D g2D; //TODO Should not be a field??
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


    public void toggleSocDist(boolean SocialDist)
    {
        if(!SocialDist)
        {
            for(int i = 0; i < simBoard.getPList().size(); i++)
            {
                simBoard.getPList().get(i).setIsSocialDistancing(false);
            }
        }
        else
        {
            for(int i = 0; i < simBoard.getPList().size(); i++)
            {
                simBoard.getPList().get(i).setIsSocialDistancing(simBoard.getPList().get(i).getIsSocialDistancingSaved());
            }
        }
    }
    public void everyoneSocialDistance()
    {
        for(int i = 0; i < simBoard.getPList().size(); i++)
        {
            simBoard.getPList().get(i).setIsSocialDistancing(true);
        }
    }


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

    public void setReset(boolean reset)
    {
        this.reset = reset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
