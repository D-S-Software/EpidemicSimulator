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

    public void updateBoard()
    {
        simBoard.updateDistanceFromSick();

        simBoard.updatePerson();

        simBoard.updateAllDimens(getBounds());
        Rectangle newDimens;

        if(simBoard instanceof SimBoardRandom)
        {
            for(int i = 0; i < simBoard.getPList().size(); i++)
            {
                newDimens = simBoard.getDimens();
                simBoard.getPList().get(i).updateDimens(newDimens);
                simBoard.getPList().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardQuad)
        {
            for(int i = 0; i < simBoard.getpListQ1().size(); i++)
            {
                newDimens = simBoard.getQ1Dimens();
                simBoard.getpListQ1().get(i).updateDimens(newDimens);
                simBoard.getpListQ1().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ2().size(); i++)
            {
                newDimens = simBoard.getQ2Dimens();
                simBoard.getpListQ2().get(i).updateDimens(newDimens);
                simBoard.getpListQ2().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ3().size(); i++)
            {
                newDimens = simBoard.getQ3Dimens();
                simBoard.getpListQ3().get(i).updateDimens(newDimens);
                simBoard.getpListQ3().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ4().size(); i++)
            {
                newDimens = simBoard.getQ4Dimens();
                simBoard.getpListQ4().get(i).updateDimens(newDimens);
                simBoard.getpListQ4().get(i).draw(g2D);
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;

        if(simBoard != null)
            updateBoard();

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
