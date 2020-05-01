package backend;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;

public class CreateFile {

    private Formatter resultsFile;

    /**
     * opens a new file to be written in
     */
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

    public void log(Music song)
    {
        resultsFile.format("%s", song + "");
    }

    /** Adds the person arrayList contents to the file
     *
     * @param array
     */
    public void addDiseaseArray(ArrayList<Person> array)
    {
        resultsFile.format("%s%s%s", "[", arrayToString(array), "]");
    }

    /**
     * Adds the time stamps for the data being written
     * @param time
     */
    public void addTime(int time)
    {
        resultsFile.format("%s", "" + time);
    }

    /**
     * Adds the percent of people who have been affected by the epidemic
     * @param decimalPercent
     */
    public void addFindAffectedPercent(double decimalPercent)
    {
        DecimalFormat df = new DecimalFormat("##.###");
        df.setRoundingMode(RoundingMode.CEILING);

        decimalPercent *= 100;

        resultsFile.format("%s%s%s", "Affected: ", df.format(decimalPercent), "%");
    }

    /**
     * Adds a space in the file
     */
    public void addSpace()
    {
        resultsFile.format("\n");
    }

    /**
     * Closes the file
     */
    public void closeFile()
    {
        resultsFile.close();
    }

    /**Converts an arrayList of people into a string to be written
     *
     * @param array The ArrayList of people to be converted
     * @return The string representing the ArrayList of Persons
     */
    private String arrayToString(ArrayList<Person> array)
    {
        String output = array.get(0).getHasDisease() + "";
        for(int i = 1; i < array.size(); i++)
        {
            output += " " + array.get(i).getHasDisease();
        }
        return output;
    }
}
