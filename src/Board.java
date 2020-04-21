import java.awt.*;
import java.util.ArrayList;

public class Board {

   public final Dimensions dimens;
   private ArrayList<Person> pList;

    public Board(int xOrigin, int yOrigin, int xLen, int yLen, int numPeople)
    {
        this.dimens = new Dimensions(xOrigin, yOrigin, xLen, yLen);

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = xOrigin + (int)(xLen*Math.random());
            int yPos = yOrigin + (int)(yLen*Math.random());

            pList.add(new Person(20, xPos, yPos, dimens));
        }
    }

    public Board(Dimensions dimens, int numPeople)
    {
        this(dimens.xOrigin, dimens.yOrigin, dimens.xLen, dimens.yLen, numPeople);
    }

    /**Default board with hardcoded parameters and default Dimensions constructed object */
    public Board()
    {
        this(new Dimensions(), 100);
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.xLen, 2) + Math.pow(dimens.yLen, 2));

            if(!pList.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(i != j && pList.get(j).getHasDisease())
                    {
                        double distTest = Math.sqrt(Math.pow(pList.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pList.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pList.get(i).setDistanceFromSick(minDist);
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updatePerson()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).checkCondition();
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

    public ArrayList<Person> getPList()
    {
        return pList;
    }
}
