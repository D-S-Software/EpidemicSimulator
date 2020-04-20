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

        for(int i = 0; i < pList.size()-1; i++)
        {
            for(int j = i+1; j < pList.size(); j++)
            {
                if(pList.get(i).getHasDisease())
                    pList.get(j).spreadRateUpdate(pList.get(i));
                else if(pList.get(j).getHasDisease())
                    pList.get(i).spreadRateUpdate(pList.get(j));
            }
        }

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
