import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList;
    private int time = 0, numHealthy, numSick, numRecovered, numDead, numPeople;
    private static CreateFile x = new CreateFile();

    public Statistics(Board myBoard, int numPeople)
    {
        pList = myBoard.getPList();
        this.numPeople = numPeople;
        x.openFile();
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
        numRecovered = recoveredCount;
        numSick = sickCount;
        numHealthy = healthyCount;
        numDead = numPeople - numHealthy - numSick - numRecovered;

        time++;

        //TODO Remove when not needed for testing
        System.out.println("H| " + numHealthy + " S| " + numSick + " R| " + numRecovered + " D| " + numDead + " T| " + time);

        x.addFindAffectedPercent((double)(numSick + numRecovered + numDead) / numPeople);
        x.addSpace();
        x.addTime(time);
        x.addSpace();
        //x.addDiseaseArray(pList);
        //x.addSpace();

        boolean close;
        if(numSick > 0)
            close = false;
        else
            close = true;
        if(close)
        {
            /**x.closeFile();
            System.exit(0); TODO Add back in at some point */
        }
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
}
