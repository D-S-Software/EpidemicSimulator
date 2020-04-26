import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;
    private int travelers = 20;
    private int timeUntilIsolate = 400;
    private double asymptomaticChance = 0;
    private double quarentineChance = 1;

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());

        //SimBoardRandom simBoard = new SimBoardRandom(disease, boardDimens, numPeople, asymptomaticChance);
        //SimBoardQuad simBoard = new SimBoardQuad(disease, boardDimens, numPeople, asymptomaticChance, travelers);
        //SimBoardIsolateQuad simBoard = new SimBoardIsolateQuad(disease, boardDimens, numPeople, asymptomaticChance, travelers, timeUntilIsolate, quarentineChance);
        SimBoardIsolateRandom simBoard = new SimBoardIsolateRandom(disease, boardDimens, numPeople, asymptomaticChance, timeUntilIsolate, quarentineChance);

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
