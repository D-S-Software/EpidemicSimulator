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

    public void socialDistanceUpdate()
    {
        for(int i = 0; i < simBoard.getPList().size(); i++)
        {
            for(int j = 0; j < simBoard.getPList().size(); j++)
            {
                if(i != j && simBoard.getPList().get(i).isSocialDistancing() && Math.sqrt(Math.pow(simBoard.getPList().get(i).getXPos() - simBoard.getPList().get(j).getXPos(), 2) + Math.pow(simBoard.getPList().get(i).getYPos() - simBoard.getPList().get(j).getYPos(), 2)) < simBoard.getSocialDistanceValue())
                    simBoard.getPList().get(i).changeDirectionAngle();
            }
        }
    }

    public void updateBoard(Graphics2D g2D)
    {
        simBoard.updateDistanceFromSick();

        simBoard.updatePList();

        simBoard.updateAllDimens(getBounds());
        Rectangle newDimens;

        if(simBoard instanceof Quarantinable)
        {
            ((Quarantinable) simBoard).quarantineCheck();
            ((Quarantinable) simBoard).drawQuarLine(g2D);
        }


        if(simBoard instanceof SimBoardMono)
        {
            for(int i = 0; i < simBoard.getPList().size(); i++)
            {
                newDimens = simBoard.getDimens();
                if(simBoard.getPList().get(i).isIsoSick() == false)
                    simBoard.getPList().get(i).updateDimens(newDimens);
                simBoard.getPList().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardQuad || simBoard instanceof SimBoardOcto)
        {
            for(int i = 0; i < simBoard.getpListQ1().size(); i++)
            {
                newDimens = simBoard.getQ1Dimens();
                if(simBoard.getpListQ1().get(i).isIsoSick() == false)
                    simBoard.getpListQ1().get(i).updateDimens(newDimens);
                simBoard.getpListQ1().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ2().size(); i++)
            {
                newDimens = simBoard.getQ2Dimens();
                if(simBoard.getpListQ2().get(i).isIsoSick() == false)
                    simBoard.getpListQ2().get(i).updateDimens(newDimens);
                simBoard.getpListQ2().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ3().size(); i++)
            {
                newDimens = simBoard.getQ3Dimens();
                if(simBoard.getpListQ3().get(i).isIsoSick() == false)
                    simBoard.getpListQ3().get(i).updateDimens(newDimens);
                simBoard.getpListQ3().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ4().size(); i++)
            {
                newDimens = simBoard.getQ4Dimens();
                if(simBoard.getpListQ4().get(i).isIsoSick() == false)
                    simBoard.getpListQ4().get(i).updateDimens(newDimens);
                simBoard.getpListQ4().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardQuad)
        {
            for(int i = 0; i < simBoard.getpListTravel().size(); i++)
            {
                newDimens = ((SimBoardQuad) simBoard).getTravelDimens();
                if(simBoard.getpListTravel().get(i).isIsoSick() == false)
                    simBoard.getpListTravel().get(i).updateDimens(newDimens);
                simBoard.getpListTravel().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardOcto)
        {
            for(int i = 0; i < simBoard.getpListQ5().size(); i++)
            {
                newDimens = simBoard.getQ5Dimens();
                if(simBoard.getpListQ5().get(i).isIsoSick() == false)
                    simBoard.getpListQ5().get(i).updateDimens(newDimens);
                simBoard.getpListQ5().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ6().size(); i++)
            {
                newDimens = simBoard.getQ6Dimens();
                if(simBoard.getpListQ6().get(i).isIsoSick() == false)
                    simBoard.getpListQ6().get(i).updateDimens(newDimens);
                simBoard.getpListQ6().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ7().size(); i++)
            {
                newDimens = simBoard.getQ7Dimens();
                if(simBoard.getpListQ7().get(i).isIsoSick() == false)
                    simBoard.getpListQ7().get(i).updateDimens(newDimens);
                simBoard.getpListQ7().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListQ8().size(); i++)
            {
                newDimens = simBoard.getQ8Dimens();
                if(simBoard.getpListQ8().get(i).isIsoSick() == false)
                    simBoard.getpListQ8().get(i).updateDimens(newDimens);
                simBoard.getpListQ8().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getpListTravel().size(); i++)
            {
                newDimens = ((SimBoardOcto) simBoard).getTravelDimens();
                if(simBoard.getpListTravel().get(i).isIsoSick() == false)
                    simBoard.getpListTravel().get(i).updateDimens(newDimens);
                simBoard.getpListTravel().get(i).draw(g2D);
            }
        }
        socialDistanceUpdate();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;

        if(simBoard != null)
            updateBoard(g2D);

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
