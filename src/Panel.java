import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener
{

    ArrayList<Person> pList;

    public Panel(ArrayList<Person> pList)
    {
        this.pList = pList; //

    }

  
    public void actionPerformed(ActionEvent e)
    {

    }

}
