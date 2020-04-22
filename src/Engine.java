import javax.swing.*;

public class Engine {

    private Board simBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private Dimensions boardDimens;
    private Disease disease;
    private int boardWidth;
    private int personBoardHeight = (int)(3* boardWidth / 4.0);
    private int buffer = (int)(boardWidth / 12.0);  //Buffer between boardPanel and tallyPanel

    public Engine(int numPeople, int boardWidth)
    {
        this.boardWidth = boardWidth;

        disease = new Disease1();

        boardDimens = new Dimensions(buffer, (int)(.25*boardWidth), boardWidth,3* boardWidth /4);

        simBoard = new Board(disease, boardDimens, numPeople);
        windowFrame = new Frame(simBoard, boardDimens, boardWidth);

        stats = new Statistics(simBoard, numPeople);

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
