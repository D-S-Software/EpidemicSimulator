import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private Dimensions boardDimens;
    private Dimensions tallyDimens;
    private Disease disease;
    private int buffer = 10;  //Buffer between boardPanel and tallyPanel

    public Engine(int numPeople)
    {
        disease = new Disease1();

        boardDimens = new Dimensions(50,50,800,450);
        tallyDimens = new Dimensions(boardDimens.xOrigin + boardDimens.xLen + buffer, boardDimens.yOrigin + buffer, boardDimens.xLen/2, boardDimens.yLen);

        myBoard = new Board(disease, boardDimens, numPeople);
        windowFrame = new Frame(myBoard, tallyDimens);

        stats = new Statistics(myBoard, numPeople);

        clock = new Timer(10, windowFrame.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(windowFrame.getTallyPanel());
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine(500);
        gameEngine.clock.start();
    }
}
