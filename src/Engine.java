import javax.swing.*;

public class Engine {

    private Timer clock;
    private GUI gui;
    private Board simBoard;

    public Engine(GUI gui, Disease disease, int numPeople, int baseXLen, int baseYLen)
    {
        this.gui = gui;

        Dimensions boardDimens = new Dimensions(0, 0, baseXLen/2, (baseYLen+50)/2);

        simBoard = new Board(gui, disease, boardDimens, numPeople);

        Statistics stats = new Statistics(simBoard, gui, numPeople);

        clock = new Timer(10,simBoard);
        clock.addActionListener(stats);
    }

   public Timer getClock()
   {
       return clock;
   }
}
