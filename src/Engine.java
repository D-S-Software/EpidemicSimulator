import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;
    private int deley = 10;
    private SimBoard simBoard;

    public Engine(GUI gui, Disease disease, int numPeople, int boardType, boolean quarBoard,
                  double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, int timeUntilQuarantine, double quarantineChance)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());

        System.out.println(boardType);

        if(quarBoard) {
            if (boardType == 8)
                simBoard = new SimBoardQuarOcto(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, timeUntilQuarantine, quarantineChance);
            else if (boardType == 4)
                simBoard = new SimBoardQuarQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, timeUntilQuarantine, quarantineChance);
            else// if boardType == 1
                simBoard = new SimBoardQuarMono(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, timeUntilQuarantine, quarantineChance);
        }
        else //!quarBoard
        {
            if (boardType == 8)
                simBoard = new SimBoardOcto(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
            else if (boardType == 4)
                simBoard = new SimBoardQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
            else //if boardType == 1
                simBoard = new SimBoardMono(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        }

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
    public void slowDown() {
        if(clock.getDelay() < 20)
            clock.setDelay(clock.getDelay() + 2);
    }
    public void speedUp()
    {
        if(clock.getDelay() > 0)
            clock.setDelay(clock.getDelay() - 1);
    }
    private void toggleSocDist()
    {

    }
}
