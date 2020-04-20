import java.awt.*;
import java.util.ArrayList;

public class Board {

   public final BoardDimensions dimens;
   private ArrayList<Person> pList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param personModel
     * @param numPeople
     */

    public Board(BoardDimensions dimens, Person personModel, int numPeople)
    {
        this.dimens = dimens;

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = dimens.xOrigin + (int)(dimens.xLen*Math.random());
            int yPos = dimens.yOrigin + (int)(dimens.yLen*Math.random());

            pList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param pList
     *
     */
    public Board(BoardDimensions dimens, ArrayList<Person> pList)
    {
        this.dimens = dimens;
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
            /*pList.get(i).checkSick();
             * if(!pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
             *   pList.remove(pList.get(i));
             * TODO: FIX These LINES
             * */


        }
    }

    public void draw(Graphics2D g2D)
    {
        g2D.setColor(Color.WHITE);
        g2D.fillRect(dimens.xOrigin, dimens.yOrigin, dimens.xLen, dimens.yLen);
    }
}
