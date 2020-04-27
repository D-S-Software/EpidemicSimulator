import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;

    private int travelers = 20;
    private int timeUntilIsolate = 100;
    private double quarentineChance = .8;
    private double asymptomaticChance = 0;
    private int socialDistanceValue = 0; // 0 --> no social distancing
    private double socialDistanceChance = .9;
    private int minAge = 20, maxAge = 80;
    private int minPreExistingConditions = 0;
    private int maxPreExistingConditions = 3;
    private int deley = 10; // Can chance this for a speed up button

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());

        //SimBoardRandom simBoard = new SimBoardRandom(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        //SimBoardIsolateRandom simBoard = new SimBoardIsolateRandom(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, timeUntilIsolate, quarentineChance);

        SimBoardQuad simBoard = new SimBoardQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers);
        //SimBoardIsolateQuad simBoard = new SimBoardIsolateQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers, timeUntilIsolate, quarentineChance);

        //SimBoardEight simBoard = new SimBoardEight(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers);
        //SimBoardIsolateEight simBoard = new SimBoardIsolateEight(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers, timeUntilIsolate, quarentineChance);

        gui.getSimBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, numPeople);
        gui.setStats(stats);

        clock = new Timer(deley, gui.getSimBoardPanel());
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
