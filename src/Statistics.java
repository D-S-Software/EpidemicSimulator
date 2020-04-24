import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList;
    private int time = 0, numHealthy=0, numSick=0, numRecovered=0, numDead=0, numPeople=0;
    private static CreateFile x = new CreateFile();
    private ArrayList<Integer> timeList = new ArrayList<>();
    private ArrayList<Integer> healthyList = new ArrayList<>();
    private ArrayList<Integer> sickList = new ArrayList<>();
    private ArrayList<Integer> recoveredList = new ArrayList<>();
    private ArrayList<Integer> deadList = new ArrayList<>();



    public Statistics(Board simBoard, int numPeople)
    {
        pList = simBoard.getPList();
        this.numPeople = numPeople;
        //x.openFile(); TODO Add back in at some point
    }

    public static CreateFile getCreateFile()
    {
        return x;
    }

    public void actionPerformed(ActionEvent e)
    {
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
        timeList.add(time);

        numRecovered = recoveredCount;
        recoveredList.add(numRecovered);

        numSick = sickCount;
        sickList.add(numSick);

        numHealthy = healthyCount;
        healthyList.add(numHealthy);

        numDead = numPeople - numHealthy - numSick - numRecovered;
        deadList.add(numDead);


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

    // TODO Remove when not needed for testing
    boolean lastPrint;
    public void printResults()
    {
        if(numSick > 0)
        {
            System.out.println("H| " + numHealthy + " S| " + numSick + " R| " + numRecovered + " D| " + numDead + " T| " + time);
            lastPrint = true;
        }
        else if(lastPrint && numSick == 0)
        {
            System.out.println("H| " + numHealthy + " S| " + numSick + " R| " + numRecovered + " D| " + numDead + " T| " + time);
            lastPrint = false;
        }
    }
}
