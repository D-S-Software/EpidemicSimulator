import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener
{

    Board board;

    public Panel(Board board)
    {
        this.board = board;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2D = (Graphics2D)g;

        board.updateDistanceFromSick();

        board.updatePerson();

        board.draw(g2D);

        for(int i = 0; i < board.getPList().size(); i++)
        {
            board.getPList().get(i).draw(g2D);
        }
    }

   public void actionPerformed(ActionEvent e)
    {
      repaint();
    }

}
