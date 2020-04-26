import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListTravel;
    private int time, numHealthy, numSick, numRecovered, numDead, numPeople, numCases;
    private static CreateFile x = new CreateFile();
    private ArrayList<Integer> timeList = new ArrayList<>();
    private ArrayList<Integer> healthyList = new ArrayList<>();
    private ArrayList<Integer> sickList = new ArrayList<>();
    private ArrayList<Integer> recoveredList = new ArrayList<>();
    private ArrayList<Integer> deadList = new ArrayList<>();
    private ArrayList<Integer> casesList = new ArrayList<>();
    private SimBoard simBoard;
    private int count = 99;

    public Statistics(SimBoard simBoard, int numPeople)
    {
        if(simBoard instanceof SimBoardRandom)
        {
            pList = simBoard.getPList();
        }
        if(simBoard instanceof SimBoardQuad)
        {
            pListQ1 = simBoard.getpListQ1();
            pListQ2 = simBoard.getpListQ2();
            pListQ3 = simBoard.getpListQ3();
            pListQ4 = simBoard.getpListQ4();
            pListTravel = simBoard.getpListTravel();
        }

        this.simBoard = simBoard;
        this.numPeople = numPeople;
        numPeople = simBoard.getNumPeople();
        time = -1;

        //x.openFile(); TODO Add back in at some point

        updateStats();
    }

    public void updateStats()
    {
        int healthyCount = 0;
        int sickCount = 0;
        int recoveredCount = 0;

        if(simBoard instanceof SimBoardRandom)
        {
            for(int i = 0; i < pList.size(); i++)
            {
                if(pList.get(i).getHasDisease() && pList.get(i).getIsHealthy())
                    recoveredCount++;
                if(pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                    sickCount++;
                if(!pList.get(i).getHasDisease() && pList.get(i).getIsHealthy())
                    healthyCount++;
            }
        }
        if(simBoard instanceof SimBoardQuad)
        {
            for(int i = 0; i < pListQ1.size(); i++)
            {
                if(pListQ1.get(i).getHasDisease() && pListQ1.get(i).getIsHealthy())
                    recoveredCount++;
                if(pListQ1.get(i).getHasDisease() && !pListQ1.get(i).getIsHealthy())
                    sickCount++;
                if(!pListQ1.get(i).getHasDisease() && pListQ1.get(i).getIsHealthy())
                    healthyCount++;
            }
            for(int i = 0; i < pListQ2.size(); i++)
            {
                if(pListQ2.get(i).getHasDisease() && pListQ2.get(i).getIsHealthy())
                    recoveredCount++;
                if(pListQ2.get(i).getHasDisease() && !pListQ2.get(i).getIsHealthy())
                    sickCount++;
                if(!pListQ2.get(i).getHasDisease() && pListQ2.get(i).getIsHealthy())
                    healthyCount++;
            }
            for(int i = 0; i < pListQ3.size(); i++)
            {
                if(pListQ3.get(i).getHasDisease() && pListQ3.get(i).getIsHealthy())
                    recoveredCount++;
                if(pListQ3.get(i).getHasDisease() && !pListQ3.get(i).getIsHealthy())
                    sickCount++;
                if(!pListQ3.get(i).getHasDisease() && pListQ3.get(i).getIsHealthy())
                    healthyCount++;
            }
            for(int i = 0; i < pListQ4.size(); i++)
            {
                if(pListQ4.get(i).getHasDisease() && pListQ4.get(i).getIsHealthy())
                    recoveredCount++;
                if(pListQ4.get(i).getHasDisease() && !pListQ4.get(i).getIsHealthy())
                    sickCount++;
                if(!pListQ4.get(i).getHasDisease() && pListQ4.get(i).getIsHealthy())
                    healthyCount++;
            }
            for(int i = 0; i < pListTravel.size(); i++)
            {
                if(pListTravel.get(i).getHasDisease() && pListTravel.get(i).getIsHealthy())
                    recoveredCount++;
                if(pListTravel.get(i).getHasDisease() && !pListTravel.get(i).getIsHealthy())
                    sickCount++;
                if(!pListTravel.get(i).getHasDisease() && pListTravel.get(i).getIsHealthy())
                    healthyCount++;
            }
        }

        time++;
        numRecovered = recoveredCount;
        numSick = sickCount;
        numHealthy = healthyCount;
        numDead = numPeople - numHealthy - numSick - numRecovered;
        numCases = numSick + numRecovered + numDead;
    }

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

        /** TODO possible implementation of an "Action Event"
        if(numDead > 10)
            for(int i = 0; i < pList.size(); i++)
                pList.get(i).setStep(0);*/


        /** TODO Add back in at some point
         boolean close;
         if(numSick > 0)
            close = false;
         else
            close = true;
         if(close)
         {
            x.closeFile();
            System.exit(0);
         }*/
    }

    /** Getter and Setter Methods */

    public static CreateFile getCreateFile()
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
    public int getNumCases() {
        return numCases;
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
