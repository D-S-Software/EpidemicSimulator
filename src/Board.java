import java.util.ArrayList;

public class Board {



    private int originX;
    private int originY;
    private int xLen;
    private int yLen;
    private ArrayList<Person> personArrayList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param originX
     * @param originY
     * @param xLen
     * @param yLen
     * @param personModel
     * @param numPeople
     */
    public Board(int originX, int originY, int xLen, int yLen, Person personModel, int numPeople)
    {
        this.originX = originX;
        this.originY = originY;
        this.xLen = xLen;
        this.yLen = yLen;

        personArrayList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = originX + (int)(xLen*Math.random());
            int yPos = originY + (int)(yLen*Math.random());

            personArrayList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param originX
     * @param originY
     * @param xLen
     * @param yLen
     * @param personArrayList
     *
     */
    public Board(int originX, int originY, int xLen, int yLen, ArrayList<Person> personArrayList)
    {
        this.originX = originX;
        this.originY = originY;
        this.xLen = xLen;
        this.yLen = yLen;
        this.personArrayList = personArrayList;
    }


    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getxLen() {
        return xLen;
    }

    public void setxLen(int xLen) {
        this.xLen = xLen;
    }

    public int getyLen() {
        return yLen;
    }

    public void setyLen(int yLen) {
        this.yLen = yLen;
    }

}
