import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        board.distanceCheck(); //TODO: SEE if this is doing anything

        board.draw(g2D);
        board.updateDiseaseAndMove();

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
