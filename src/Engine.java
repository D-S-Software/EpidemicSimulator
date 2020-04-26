import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());

        //SimBoardRandom simBoard = new SimBoardRandom(disease, boardDimens, numPeople);
        SimBoardQuad simBoard = new SimBoardQuad(disease, boardDimens, numPeople);

        gui.getSimBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, numPeople);
        gui.setStats(stats);

        clock = new Timer(10, gui.getSimBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(gui.getXYChartPanel());
        clock.addActionListener(gui.getXYChartPanel2());
        clock.addActionListener(gui.getPieChartPanel());
        clock.addActionListener(gui.getTallyPanel());
    }

   public Timer getClock()
   {
       return clock;
   }
}
