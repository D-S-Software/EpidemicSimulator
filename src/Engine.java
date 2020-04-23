import javax.swing.*;

public class Engine {

    private Timer clock;
    private GUI gui;
    private Board simBoard;

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        this.gui = gui;

        Dimensions boardDimens = new Dimensions(gui.getBoardRec());

        simBoard = new Board(disease, boardDimens, numPeople);

        gui.getBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, gui, numPeople);

        clock = new Timer(10, gui.getBoardPanel());
        clock.addActionListener(stats);
    }

   public Timer getClock()
   {
       return clock;
   }


}
