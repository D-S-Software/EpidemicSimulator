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
            for(int i = 0; i < simBoard.getPListQ1().size(); i++)
            {
                newDimens = simBoard.getQ1Dimens();
                if(simBoard.getPListQ1().get(i).isIsoSick() == false)
                    simBoard.getPListQ1().get(i).updateDimens(newDimens);
                simBoard.getPListQ1().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ2().size(); i++)
            {
                newDimens = simBoard.getQ2Dimens();
                if(simBoard.getPListQ2().get(i).isIsoSick() == false)
                    simBoard.getPListQ2().get(i).updateDimens(newDimens);
                simBoard.getPListQ2().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ3().size(); i++)
            {
                newDimens = simBoard.getQ3Dimens();
                if(simBoard.getPListQ3().get(i).isIsoSick() == false)
                    simBoard.getPListQ3().get(i).updateDimens(newDimens);
                simBoard.getPListQ3().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ4().size(); i++)
            {
                newDimens = simBoard.getQ4Dimens();
                if(simBoard.getPListQ4().get(i).isIsoSick() == false)
                    simBoard.getPListQ4().get(i).updateDimens(newDimens);
                simBoard.getPListQ4().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardQuad)
        {
            for(int i = 0; i < simBoard.getPListTravel().size(); i++)
            {
                newDimens = ((SimBoardQuad) simBoard).getTravelDimens();
                if(simBoard.getPListTravel().get(i).isIsoSick() == false)
                    simBoard.getPListTravel().get(i).updateDimens(newDimens);
                simBoard.getPListTravel().get(i).draw(g2D);
            }
        }
        if(simBoard instanceof SimBoardOcto)
        {
            for(int i = 0; i < simBoard.getPListQ5().size(); i++)
            {
                newDimens = simBoard.getQ5Dimens();
                if(simBoard.getPListQ5().get(i).isIsoSick() == false)
                    simBoard.getPListQ5().get(i).updateDimens(newDimens);
                simBoard.getPListQ5().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ6().size(); i++)
            {
                newDimens = simBoard.getQ6Dimens();
                if(simBoard.getPListQ6().get(i).isIsoSick() == false)
                    simBoard.getPListQ6().get(i).updateDimens(newDimens);
                simBoard.getPListQ6().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ7().size(); i++)
            {
                newDimens = simBoard.getQ7Dimens();
                if(simBoard.getPListQ7().get(i).isIsoSick() == false)
                    simBoard.getPListQ7().get(i).updateDimens(newDimens);
                simBoard.getPListQ7().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListQ8().size(); i++)
            {
                newDimens = simBoard.getQ8Dimens();
                if(simBoard.getPListQ8().get(i).isIsoSick() == false)
                    simBoard.getPListQ8().get(i).updateDimens(newDimens);
                simBoard.getPListQ8().get(i).draw(g2D);
            }
            for(int i = 0; i < simBoard.getPListTravel().size(); i++)
            {
                newDimens = ((SimBoardOcto) simBoard).getTravelDimens();
                if(simBoard.getPListTravel().get(i).isIsoSick() == false)
                    simBoard.getPListTravel().get(i).updateDimens(newDimens);
                simBoard.getPListTravel().get(i).draw(g2D);
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
