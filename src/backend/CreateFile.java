package backend;

import java.io.File;
import java.nio.file.Files;
import java.util.Formatter;

public class CreateFile {

    private Formatter resultsFile;

    /**
     * opens a new file to be written in
     * @param fileName The name of the file to look for / create
     */
    public void openFile(String fileName, String parentFileName)
    {
        try
        {
            resultsFile = new Formatter(parentFileName + "/" + fileName);
        }
        catch(Exception e)
        {
            System.out.println("There is an error");
        }
    }

    /** Deletes the file or folder (used to remove previous simulation data)
     *
     * @param dir Directory of the file to be deleted
     * @return true if performed successfully
     */
    public static boolean deleteDirectory(File dir)
    {
        if (dir.isDirectory())
        {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = deleteDirectory(children[i]);
                if (!success)
                    return false;
            }
        }
        return dir.delete();
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
    public void addStat(double stat)
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
