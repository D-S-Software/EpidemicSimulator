import java.util.ArrayList;

public class Board {

    private int xOrigin;
    private int yOrigin;
    private int xLen;
    private int yLen;
    private ArrayList<Person> personArrayList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param xOrigin
     * @param yOrigin
     * @param xLen
     * @param yLen
     * @param personModel
     * @param numPeople
     */

    public Board(int xOrigin, int yOrigin, int xLen, int yLen, Person personModel, int numPeople)
    {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xLen = xLen;
        this.yLen = yLen;

        personArrayList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = xOrigin + (int)(xLen*Math.random());
            int yPos = yOrigin + (int)(yLen*Math.random());

            personArrayList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param xOrigin
     * @param yOrigin
     * @param xLen
     * @param yLen
     * @param personArrayList
     *
     */
    public Board(int xOrigin, int yOrigin, int xLen, int yLen, ArrayList<Person> personArrayList)
    {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xLen = xLen;
        this.yLen = yLen;
        this.personArrayList = personArrayList;
    }

    /**Default board with hardcoded parameters */
    public Board()
    {
      this(0, 0, 1600, 900, new Person(20, false, 1, 1), 100);
    }


    public int getXOrigin() {
        return xOrigin;
    }

    public void setXOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    public int getYOrigin() {
        return yOrigin;
    }

    public void setYOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    public int getXLen() {
        return xLen;
    }

    public void setXLen(int xLen) {
        this.xLen = xLen;
    }

    public int getYLen() {
        return yLen;
    }

    public void setYLen(int yLen) {
        this.yLen = yLen;
    }

}
