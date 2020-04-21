import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private Dimensions boardDimens;
    private Dimensions tallyDimens;
    private Disease disease;
    private int boardWidth;
    private int personBoardHeight = 3* boardWidth / 4;
    private int buffer = boardWidth / 12 ;  //Buffer between boardPanel and tallyPanel

    public Engine(int numPeople, int boardWidth)
    {
        this.boardWidth = boardWidth;

        disease = new Disease1();

        boardDimens = new Dimensions(buffer,3*buffer, boardWidth,3* boardWidth /4);
        tallyDimens = new Dimensions(0,0,boardWidth/4, 3*boardWidth/16);

        myBoard = new Board(disease, boardDimens, numPeople);
        windowFrame = new Frame(myBoard, tallyDimens);

        stats = new Statistics(myBoard, numPeople);

        clock = new Timer(10, windowFrame.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(windowFrame.getTallyPanel());
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine(500, 800);
        gameEngine.clock.start();
    }
}
