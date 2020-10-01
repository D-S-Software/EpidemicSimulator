package backend;

import backend.simboard.SimBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private int time, numHealthy, numSick, numRecovered, numDead, numPeople, numCases, count = 99;
    private double averageRValue;
    private static CreateFile x;
    private ArrayList<Integer> timeList = new ArrayList<>();
    private ArrayList<Integer> healthyList = new ArrayList<>();
    private ArrayList<Integer> sickList = new ArrayList<>();
    private ArrayList<Integer> recoveredList = new ArrayList<>();
    private ArrayList<Integer> deadList = new ArrayList<>();
    private ArrayList<Integer> casesList = new ArrayList<>();
    private SimBoard simBoard;

    /**Creates a statistics object that collects the data from the simulation to be displayed, used in chart, or printed to a text file
     *
     * @param simBoard The simulation board being used in the simulation
     */
    public Statistics(SimBoard simBoard, int numStatFile)
    {
        this.simBoard = simBoard;
        numPeople = simBoard.getNumPeople();
        time = -1;

        x = new CreateFile();
        String resultsFolder = "SimulationData";

        if(numStatFile == 1) //Deletes the previous data from last application launch
        {
            x.deleteDirectory(new File(resultsFolder));
            new File("SimulationData").mkdirs();
        }

        x.openFile("EpidemicResults" + numStatFile + ".txt", resultsFolder);
        x.addString("Time (centi sec):");
        x.addString("NumCases:");
        x.addString("NumHealthy:");
        x.addString("NumRecovered:");
        x.addString("NumDead:");
        x.addString("NumSick:");
        x.addString("Ro Value:");
        x.addSpace();

        updateStats();
    }

    /**
     * Updates the each of the statistics (Called every tick)
     */
    private void updateStats()
    {
        int healthyCount = 0;
        int sickCount = 0;
        int recoveredCount = 0;

        for(int i = 1; i < simBoard.getListPList().size(); i++)
        {
            for(int j = 0; j < simBoard.getListPList().get(i).size(); j++)
            {
                if(simBoard.getListPList().get(i).get(j).getHasDisease() && simBoard.getListPList().get(i).get(j).getIsHealthy())
                    recoveredCount++;
                if(simBoard.getListPList().get(i).get(j).getHasDisease() && !simBoard.getListPList().get(i).get(j).getIsHealthy())
                    sickCount++;
                if(!simBoard.getListPList().get(i).get(j).getHasDisease() && simBoard.getListPList().get(i).get(j).getIsHealthy())
                    healthyCount++;
            }
        }

        time++;
        numRecovered = recoveredCount;
        numSick = sickCount;
        numHealthy = healthyCount;
        numDead = numPeople - numHealthy - numSick - numRecovered;
        numCases = numSick + numRecovered + numDead;

        if(numCases > 0)
        {
            int infectedSum = 0;
            for(int i = 0; i < simBoard.getRNot().size(); i++)
            {
                infectedSum += simBoard.getRNot().get(i);
            }
            averageRValue = (double)infectedSum / numCases;
        }

        if(numSick > 0)
            printStats();
    }

    private void printStats()
    {
        x.addStat(time);
        x.addStat(numCases);
        x.addStat(numHealthy);
        x.addStat(numRecovered);
        x.addStat(numDead);
        x.addStat(numSick);
        x.addStat(averageRValue);
        x.addSpace();
    }

    /**Calls the update stats methods, calls actions events when needed, and decides when to close a text file (called every tick)
     *
     * @param e event each tick
     */
    public void actionPerformed(ActionEvent e)
    {
        count++;
        if(count == 100)
        {
            timeList.add(time/100);
            healthyList.add(numHealthy);
            sickList.add(numSick);
            recoveredList.add(numRecovered);
            deadList.add(numDead);
            casesList.add(numCases);

            count = 0;
        }

        updateStats();

        for(int i = 0; i < simBoard.getPList().size(); i++)
            simBoard.getPList().get(i).updateMortalityRate(numSick, numPeople);

        if(numSick == 0)
            x.closeFile();

        //TODO possible implementation of an "Action Event"
        /**if(numDead > 10)
           for(int i = 0; i < simBoard.getListPList().size(); i++)
               for(int j = 0; j < simBoard.getListPList().get(i).size(); j++)
                   simBoard.getListPList().get(i).get(j).setIsSocialDistancing(true);
        */
    }

    /** Getter and Setter Methods */

    public CreateFile getCreateFile()
    {
        return x;
    }

    public int getNumHealthy()
    {
        return numHealthy;
    }
    public int getNumSick()
    {
        return numSick;
    }
    public int getNumRecovered()
    {
        return numRecovered;
    }
    public int getNumDead()
    {
        return numDead;
    }
    public int getTime()
    {
        return time;
    }
    public double getAverageRValue()
    {
        return averageRValue;
    }

    public ArrayList<Integer> getTimeList() {
        return timeList;
    }
    public ArrayList<Integer> getHealthyList() {
        return healthyList;
    }
    public ArrayList<Integer> getSickList() {
        return sickList;
    }
    public ArrayList<Integer> getRecoveredList() {
        return recoveredList;
    }
    public ArrayList<Integer> getDeadList() {
        return deadList;
    }
    public ArrayList<Integer> getCasesList() {
        return casesList;
    }
}
