import java.awt.*;
import java.util.ArrayList;

public class Board {

   public final BoardDimensions boardDimensions;
   private ArrayList<Person> pList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param personModel
     * @param numPeople
     */

    public Board(BoardDimensions boardDimensions, Person personModel, int numPeople)
    {
        this.boardDimensions = boardDimensions;

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = boardDimensions.xOrigin + (int)(boardDimensions.xLen*Math.random());
            int yPos = boardDimensions.yOrigin + (int)(boardDimensions.yLen*Math.random());

            pList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param pList
     *
     */
    public Board(BoardDimensions boardDimensions, ArrayList<Person> pList)
    {
        this.boardDimensions = boardDimensions;
        this.pList = pList;
    }

    /**Default board with hardcoded parameters and default BoardDimensions constructed object */
    public Board()
    {
      this(new BoardDimensions(), new Person(20, false, 1, 1, new BoardDimensions()), 100);
    }

    public ArrayList<Person> getPList()
    {
        return pList;
    }

    public void distanceCheck()
    {
        for(int i = 0; i < pList.size()-1; i++)
        {
            for(int j = i+1; j < pList.size(); j++)
            {
                if(pList.get(i).getHasDisease())
                    pList.get(j).updateSpreadRate(pList.get(i));
                else if(pList.get(j).getHasDisease())
                    pList.get(i).updateSpreadRate(pList.get(j));
            }
        }
    }

    public void updateDiseaseAndMove()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).move();
            pList.get(i).checkSick();
            if(!pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                pList.remove(pList.get(i));
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(boardDimensions.xOrigin, boardDimensions.yOrigin, boardDimensions.xLen,boardDimensions.yLen);
    }
}
