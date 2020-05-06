package backend;

import java.util.Formatter;

public class CreateFile {

    private Formatter resultsFile;

    /**
     * opens a new file to be written in
     */
    public void openFile(String fileName)
    {
        try
        {
            resultsFile = new Formatter(fileName);
        }
        catch(Exception e)
        {
            System.out.println("There is an error");
        }
    }

    /**Adds a String to a printed text file
     *
     * @param s The String to be recorded
     */
    public void addString(String s)
    {
        resultsFile.format("%s", s + "\t");
    }

    /**Adds a value to a printed text file
     *
     * @param stat The value to be recorded
     */
    public void addStat(int stat)
    {
        resultsFile.format("%s", stat + "\t");
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
}
