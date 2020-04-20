import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics /**implements ActionListener*/ {

    private ArrayList<Person> pList;
    private int time = 0;
    private double percentInfected;
    private static CreateFile x = new CreateFile();

    public Statistics(Board myBoard)
    {
        pList = myBoard.getPList();
        // x.openFile(); --> this can be uncommented once we find a place to close the file
    }

    public static CreateFile getCreateFile()
    {
        return x;
    }

    public double findDiseasePercent()
    {
        int count = 0;
        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getHasDisease())
                count++;
        }
        return count / pList.size();
    }

    /**
    @Override
    public void actionPerformed(ActionEvent e)
    {
        time++;

        x.addDiseasePercent(findDiseasePercent());
        x.addTime(time);
        x.addSpace();
        x.addDiseaseArray(pList);
        x.addSpace();
    }
    */ // This can be uncommented once the actionListener problem is solved (same with implements comment)
}
