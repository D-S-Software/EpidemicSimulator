import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private Dimensions boardDimens;
    private Dimensions tallyDimens;
    private int buffer = 10;  //Buffer between boardPanel and tallyPanel

    public Engine()
    {
        boardDimens = new Dimensions();
        tallyDimens = new Dimensions(boardDimens.xOrigin + boardDimens.xLen + buffer, boardDimens.yOrigin + buffer, boardDimens.xLen/2, boardDimens.yLen);

        myBoard = new Board(boardDimens, 1000);
        windowFrame = new Frame(myBoard, tallyDimens);

        stats = new Statistics(myBoard);

        clock = new Timer(10, windowFrame.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(windowFrame.getTallyPanel());
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine();
        gameEngine.clock.start();
    }
}
