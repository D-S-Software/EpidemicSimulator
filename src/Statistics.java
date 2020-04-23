import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList;
    private int time = 0, numHealthy=0, numSick=0, numRecovered=0, numDead=0, numPeople=0;
    private GUI gui;
    private Board simBoard;
    private static CreateFile x = new CreateFile();

    public Statistics(Board simBoard, GUI gui, int numPeople)
    {
        this.simBoard = simBoard;
        pList = simBoard.getPList();
        this.numPeople = numPeople;
        this.gui = gui;
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
        numRecovered = recoveredCount;
        numSick = sickCount;
        numHealthy = healthyCount;
        numDead = numPeople - numHealthy - numSick - numRecovered;
        time++;

        gui.getNumHealthyLabel().setText("NumHealthy: " + numHealthy);
        gui.getNumSickLabel().setText("NumSick: " + numSick);
        gui.getNumRecoveredLabel().setText("NumRecovered: " + numRecovered);
        gui.getNumDeadLabel().setText("NumDead: " + numDead);

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
