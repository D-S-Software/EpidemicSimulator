import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;
    private GUI gui;
    private Board simBoard;

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        this.gui = gui;

        Rectangle boardDimens = new Rectangle(gui.getBoardRec());

        simBoard = new Board(disease, boardDimens, numPeople);

        gui.getBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, numPeople);
        gui.setStats(stats);

        clock = new Timer(10, gui.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(gui.getChartPanel());
        clock.addActionListener(gui.getTallyPanel());
    }

   public Timer getClock()
   {
       return clock;
   }


}
