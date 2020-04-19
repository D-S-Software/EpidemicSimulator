import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener
{
    Timer timer;
    ArrayList<Person> pList;

    public Panel(Timer t, ArrayList<Person> pList)
    {
        timer = t;
        this.pList = pList;

    }

    public void actionPerformed(ActionEvent e)
    {

    }

}
