import Library.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements ActionListener
{
    Board simBoard;
    Color background = CustomColor.JET;
    Graphics2D g2D;
    boolean reset = false;

    public BoardPanel()
    {
        super();
        setBackground(background);
    }

    public void setBoard(Board simBoard)
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

        for(int i = 0; i < simBoard.getPList().size(); i++)
        {
            simBoard.getPList().get(i).updateDimens(getBounds()); //Checks the borders of the BoardPanel each tick to update each person move / bounce method
            simBoard.getPList().get(i).draw(g2D);
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
