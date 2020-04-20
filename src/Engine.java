import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;

    public Board getBoard()
    {
        return myBoard;
    }

    public Engine()
    {
        myBoard = new Board();
        windowFrame = new Frame(myBoard);
        stats = new Statistics(myBoard);
        this.clock = new Timer(10, windowFrame.getPanel());
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine();
        System.out.println(gameEngine.myBoard.getPList().get(2).getHasDisease());
        gameEngine.clock.start();
    }

    // NOTE: The 2 BoardDimensions constructor calls in Board class should be moved to engine constructor
    // THIS TO SHALL PASS
}
