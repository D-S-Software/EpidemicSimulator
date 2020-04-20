import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener
{

    ArrayList<Person> pList;
    Board board;

    public Panel(Board board)
    {
        this.pList = board.getPList();
        this.board = board;

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).move();
            pList.get(i).checkSick();
            if(!pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                pList.remove(pList.get(i));
            pList.get(i).draw(g);
        }

        board.draw(g);
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

}
