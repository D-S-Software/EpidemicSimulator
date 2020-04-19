import javax.swing.*;
import java.util.ArrayList;

public class Frame extends JFrame {

    Panel panel;

    public Frame(Timer t, ArrayList<Person> pList)
    {
        panel = new Panel(t, pList);

    }
}
