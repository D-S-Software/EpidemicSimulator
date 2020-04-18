import java.util.ArrayList;

public class Board {



    private int XOrigin;
    private int YOrigin;
    private int xLen;
    private int yLen;
    private ArrayList<Person> personArrayList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param XOrigin
     * @param YOrigin
     * @param xLen
     * @param yLen
     * @param personModel
     * @param numPeople
     */
    public Board(int XOrigin, int YOrigin, int xLen, int yLen, Person personModel, int numPeople)
    {
        this.XOrigin = XOrigin;
        this.YOrigin = YOrigin;
        this.xLen = xLen;
        this.yLen = yLen;

        personArrayList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = XOrigin + (int)(xLen*Math.random());
            int yPos = YOrigin + (int)(yLen*Math.random());

            personArrayList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param XOrigin
     * @param YOrigin
     * @param xLen
     * @param yLen
     * @param personArrayList
     *
     */
    public Board(int XOrigin, int YOrigin, int xLen, int yLen, ArrayList<Person> personArrayList)
    {
        this.XOrigin = XOrigin;
        this.YOrigin = YOrigin;
        this.xLen = xLen;
        this.yLen = yLen;
        this.personArrayList = personArrayList;
    }


    public int getXOrigin() {
        return XOrigin;
    }

    public void setXOrigin(int XOrigin) {
        this.XOrigin = XOrigin;
    }

    public int getYOrigin() {
        return YOrigin;
    }

    public void setYOrigin(int YOrigin) {
        this.YOrigin = YOrigin;
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
