package backend;

import backend.disease.Disease;
import gui.GUI;
import backend.simboard.*;

import javax.swing.*;
import java.awt.*;

public class Engine {

    private Timer clock;
    private int delay = 10;

    /** Creates and engine object for a run of the simulation. Creates the requested board and sets parameters
     *
     * @param gui The gui object to set the dimensions, stats object, and simboard to the simBoardPanel
     * @param disease The disease object used for a simulation
     * @param numPeople The total number of people in a simulation
     * @param boardType The type of board being used (mono, quad, octo)
     * @param quarBoard Decides if the board has a quarantine section
     * @param asymptomaticChance The percent of people who are asymptomatic
     * @param socialDistanceValue The number of pixels each person tries to stay apart by when social distancing is enabled
     * @param socialDistanceChance The percent of people who participate in social distancing
     * @param minAge The minimum age of a person in the simulation
     * @param maxAge The maximum age of a person in the simulation
     * @param minPreExistingConditions The minimum pre-existing conditions of a person in the simulation
     * @param maxPreExistingConditions The maximum pre-existing conditions of a person in the simulation
     * @param travelersPer The percent of people who travel without bound in a quad or octo board
     * @param timeUntilQuarantine The time until a sick person will go to quarantine
     * @param quarantineChance The percent of sick people that will go to quarantine
     * @param reinfectRate The percentage of people that can be reinfected once they loose resistance
     * @param antiBodyTime The time it takes for antibodies to expire
     */
    public Engine(GUI gui, Disease disease, int numPeople, int boardType, boolean quarBoard,
                  double asymptomaticChance, double socialDistanceValue, double socialDistanceChance, double minAge, double maxAge,
                  int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, double timeUntilQuarantine,
                  double quarantineChance, double reinfectRate, double antiBodyTime)
    {
        Rectangle boardDimens = new Rectangle(gui.getSimBoardRec());
        SimBoard simBoard;
        if(quarBoard) {
            if (boardType == 8)
                simBoard = new SimBoardQuarOcto(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, timeUntilQuarantine, reinfectRate, quarantineChance, antiBodyTime);
            else if (boardType == 4)
                simBoard = new SimBoardQuarQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, timeUntilQuarantine, reinfectRate, quarantineChance, antiBodyTime);
            else// if boardType == 1
                simBoard = new SimBoardQuarMono(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, timeUntilQuarantine, reinfectRate, quarantineChance, antiBodyTime);
        }
        else //!quarBoard /
        {
            if (boardType == 8)
                simBoard = new SimBoardOcto(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, reinfectRate, 0, antiBodyTime);
            else if (boardType == 4)
                simBoard = new SimBoardQuad(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, reinfectRate, 0, antiBodyTime);
            else //if boardType == 1
                simBoard = new SimBoardMono(disease, boardDimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, reinfectRate, 0, antiBodyTime);
        }

        gui.getSimBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, gui.getControlPanel().getNumStatsFile());
        gui.setStats(stats);

        clock = new Timer(delay, gui.getSimBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(gui.getXYChartPanel());
        clock.addActionListener(gui.getXYChartPanel2());
        clock.addActionListener(gui.getPieChartPanel());
        clock.addActionListener(gui.getTallyPanel());
    }

    /**
     *
     * @return Returns the clock object that runs the simulation
     */
    public Timer getClock()
    {
        return clock;
    }

    /**
     *  Slows down the simulation by 2 ms
     */
    public void slowDown() {
        if(clock.getDelay() < 19)
            clock.setDelay(clock.getDelay() + 1);
    }

    /**
     *  Speeds up the simulation by 2 ms
     */
    public void speedUp()
    {
        if(clock.getDelay() > 0)
            clock.setDelay(clock.getDelay() - 1);
    }

    /**
     *
     * @return Returns the delay of the clock running the simulation
     */
    public int getDelay()
    {
        return clock.getDelay();
    }
}
