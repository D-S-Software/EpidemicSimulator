import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;
    private int deley = 10; // Can chance this for a speed up button (go to 1 for ex)

    private int travelers = 20; // --> how many people in a divided board can go anywhere

    private int timeUntilQuarantine = 100; // --> how much time (centiseconds) until sick people quarantine
    private double quarantineChance = .8; // --> what % of people decide to quarantine

    private double asymptomaticChance = 0; // --> what % of people are asymptomatic (still contagious, do not quarantine, do not dry (only recover))

    private int socialDistanceValue = 0; // --> how far to stay apart if possible (0 for no social distancing)
    private double socialDistanceChance = .9; // --> what % of people try to social distance

    private int minAge = 20, maxAge = 80; // --> age affects how long someone is sick for and how deadly a disease is
    private int minPreExistingConditions = 0; // --> preexisting conditions affect how deadly a disease is
    private int maxPreExistingConditions = 3;

    public Engine(GUI gui, Disease disease, int numPeople)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());

        //SimBoardRandom simBoard = new SimBoardRandom(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        //SimBoardIsolateRandom simBoard = new SimBoardIsolateRandom(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, timeUntilQuarantine, quarantineChance);

        SimBoardQuad simBoard = new SimBoardQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers);
        //SimBoardIsolateQuad simBoard = new SimBoardIsolateQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers, timeUntilQuarantine, quarantineChance);

        //SimBoardEight simBoard = new SimBoardEight(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers);
        //SimBoardIsolateEight simBoard = new SimBoardIsolateEight(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers, timeUntilQuarantine, quarantineChance);

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
