import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    GUI gui;

    public Main(int numPeople, int baseXLen, int baseYLen)
    {
        gui = new GUI(baseXLen, baseYLen);
    }
    public static void main(String[] args)
    {
        Main test = new Main(500, 1500, 1000);
    }
}


//TODO: Pause button (Start and stop the clock object)
//TODO: End Button