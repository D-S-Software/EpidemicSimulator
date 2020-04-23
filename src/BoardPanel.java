import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements ActionListener
{
    Board simBoard;
    Graphics2D g2D;

    public BoardPanel()
    {
        super();
        setBackground(Color.WHITE);
    }

    public void setBoard(Board simBoard)
    {
        this.simBoard = simBoard;
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
    }

    public Graphics2D getGraphics()
    {
        return g2D;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
