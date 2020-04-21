import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private BoardDimensions dimens;

    public Engine()
    {
        dimens = new BoardDimensions();
        myBoard = new Board(dimens, 500);
        windowFrame = new Frame(myBoard);

        stats = new Statistics(myBoard);
        clock = new Timer(10, windowFrame.getPeopleBoardPanel());
        clock.addActionListener(stats);
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine();
        gameEngine.clock.start();
    }
}
