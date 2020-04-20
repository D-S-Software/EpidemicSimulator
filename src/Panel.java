import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener
{

    private ArrayList<Person> pList;
    private Board board;


    public Panel(Board board, ActionListener actionListener)
    {
        this.pList = board.getPList();
        this.board = board;

        }

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        board.draw(g);

        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).move();
            pList.get(i).draw(g);

        }
    }



    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

}
