import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements ActionListener
{
    Board simBoard;

    public BoardPanel()
    {
        super();
        setBackground(Color.WHITE);
    }

    public void setBoard(Board simBoard)
    {
        this.simBoard = simBoard;
    }

    public void updateBoard(Graphics2D g2D)
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

        if(simBoard != null)
            updateBoard((Graphics2D)g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
