import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;

public class CreateFile {

    private Formatter resultsFile;

    public void openFile()
    {
        try
        {
            resultsFile = new Formatter("EpidemicResults.txt");
        }
        catch(Exception e)
        {
            System.out.println("There is an error");
        }
    }

    public void addDiseaseArray(ArrayList<Person> array)
    {
        resultsFile.format("%s%s%s", "[", arrayToString(array), "]");
    }

    public void addTime(int time)
    {
        resultsFile.format("%s", "" + time);
    }

    public void addFindAffectedPercent(double decimalPercent)
    {
        DecimalFormat df = new DecimalFormat("##.###");
        df.setRoundingMode(RoundingMode.CEILING);

        decimalPercent *= 100;

        resultsFile.format("%s%s%s", "Affected: ", df.format(decimalPercent), "%");
    }

    public void addSpace()
    {
        resultsFile.format("\n");
    }

    public void closeFile()
    {
        resultsFile.close();
    }

    public String arrayToString(ArrayList<Person> array)
    {
        String output = array.get(0).getHasDisease() + "";
        for(int i = 1; i < array.size(); i++)
        {
            output += " " + array.get(i).getHasDisease();
        }
        return output;
    }
}
