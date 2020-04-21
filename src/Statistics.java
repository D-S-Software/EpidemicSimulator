import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Statistics implements ActionListener {

    private ArrayList<Person> pList;
    private int time = 0;
    private static CreateFile x = new CreateFile();

    public Statistics(Board myBoard)
    {
        pList = myBoard.getPList();
        x.openFile();
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
        return (double)count / pList.size();
    }

    public void actionPerformed(ActionEvent e)
    {
        time++;

        x.addDiseasePercent(findDiseasePercent());
        x.addSpace();
        x.addTime(time);
        x.addSpace();
        //x.addDiseaseArray(pList);
        //x.addSpace();

        if(time >= 4000)
        {
            x.closeFile();
            System.exit(0);
        }
    }
}
