import javax.swing.*;
import java.util.ArrayList;

public class Frame extends JFrame {

    Panel panel;

    public Frame(ArrayList<Person> pList)
    {
        panel = new Panel(pList);

    }
}
