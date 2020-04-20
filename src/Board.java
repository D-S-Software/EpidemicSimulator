import java.awt.*;
import java.util.ArrayList;

public class Board {

   public final BoardDimensions dimens;
   private ArrayList<Person> pList;

    public Board(int xOrigin, int yOrigin, int xLen, int yLen, int numPeople)
    {
        this.dimens = new BoardDimensions(xOrigin, yOrigin, xLen, yLen);

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = xOrigin + (int)(xLen*Math.random());
            int yPos = yOrigin + (int)(yLen*Math.random());

            pList.add(new Person(20, xPos, yPos, dimens));
        }
    }

    public Board(BoardDimensions dimens, int numPeople)
    {
        this(dimens.xOrigin, dimens.yOrigin, dimens.xLen, dimens.yLen, numPeople);
    }

    /**Default board with hardcoded parameters and default BoardDimensions constructed object */
    public Board()
    {
        this(new BoardDimensions(), 100);
    }

    public ArrayList<Person> getPList()
    {
        return pList;
    }

    public void sickComparison()
    {
        for(int i = 0; i < pList.size()-1; i++)
        {
            for(int j = i+1; j < pList.size(); j++)
            {
                if(pList.get(i).getHasDisease())
                {
                    pList.get(j).willSpread(pList.get(i));
                    pList.get(j).setCanGetSick(true);
                }
                else if(pList.get(j).getHasDisease())
                {
                    pList.get(i).willSpread(pList.get(j));
                    pList.get(i).setCanGetSick(true);
                }
                else
                {
                    pList.get(i).setCanGetSick(false);
                    pList.get(i).setCanGetSick(false);
                }
            }
        }
    }


    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updatePerson()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).checkSick();
            pList.get(i).move();
            if(!pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                pList.remove(pList.get(i));
        }
    }

    public void draw(Graphics2D g2D)
    {
        g2D.setColor(Color.WHITE);
        g2D.fillRect(dimens.xOrigin, dimens.yOrigin, dimens.xLen, dimens.yLen);
    }
}
