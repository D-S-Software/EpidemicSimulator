import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList;
    private int time, numHealthy, numSick, numRecovered, numDead, numPeople, numCases;
    private static CreateFile x = new CreateFile();
    private ArrayList<Integer> timeList = new ArrayList<>();
    private ArrayList<Integer> healthyList = new ArrayList<>();
    private ArrayList<Integer> sickList = new ArrayList<>();
    private ArrayList<Integer> recoveredList = new ArrayList<>();
    private ArrayList<Integer> deadList = new ArrayList<>();
    private ArrayList<Integer> casesList = new ArrayList<>();
    private int count = 99;
    private boolean slowGraph = true;

    public Statistics(Board simBoard, int numPeople)
    {
        pList = simBoard.getPList();
        this.numPeople = numPeople;
        //x.openFile(); TODO Add back in at some point

        int healthyCount = 0;
        int sickCount = 0;

        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                sickCount++;
            if(!pList.get(i).getHasDisease() && pList.get(i).getIsHealthy())
                healthyCount++;
        }
        numHealthy = healthyCount;
        numSick = sickCount;
        numPeople = pList.size();
        numCases = numSick + numRecovered + numDead;
    }

    public static CreateFile getCreateFile()
    {
        return x;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(slowGraph && numSick > 0)
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
        }
        else if(numSick > 0)
        {
            timeList.add(time);
            healthyList.add(numHealthy);
            sickList.add(numSick);
            recoveredList.add(numRecovered);
            deadList.add(numDead);
            casesList.add(numCases);
        }

        int healthyCount = 0;
        int sickCount = 0;
        int recoveredCount = 0;

        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getHasDisease() && pList.get(i).getIsHealthy())
                recoveredCount++;
            if(pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                sickCount++;
            if(!pList.get(i).getHasDisease() && pList.get(i).getIsHealthy())
                healthyCount++;
        }

        time++;
        numRecovered = recoveredCount;
        numSick = sickCount;
        numHealthy = healthyCount;
        numDead = numPeople - numHealthy - numSick - numRecovered;
        numCases = numSick + numRecovered + numDead;

        /**
        x.addFindAffectedPercent((double)(numSick + numRecovered + numDead) / numPeople);
        x.addSpace();
        x.addTime(time);
        x.addSpace();

        boolean close;
        if(numSick > 0)
            close = false;
        else
            close = true;
        if(close)
        {
            x.closeFile();
            System.exit(0); TODO Add back in at some point
        }*/
    }

    /** Getter and Setter Methods */
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
    public int getNumCases() {
        return numCases;
    }

    public boolean isSlowGraph() {
        return slowGraph;
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
