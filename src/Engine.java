import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;

    public Board getBoard()
    {
        return myBoard;
    }

    public Engine()
    {
        myBoard = new Board();
        windowFrame = new Frame(myBoard);
        this.clock = new Timer(10, windowFrame.getPanel());
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine();

    }
}
