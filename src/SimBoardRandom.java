import java.awt.*;
import java.util.ArrayList;

public class SimBoardRandom extends SimBoard{

   private Rectangle dimens;
   private ArrayList<Person> pList;
   private boolean asymptomatic, isSocialDistancing;
   private int numPeople;
   private int socialDistanceValue;

    public SimBoardRandom(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                          int minPreExistingConditions, int maxPreExistingConditions)
    {
        this.dimens = dimens;
        this.socialDistanceValue = socialDistanceValue;
        this.numPeople = numPeople;

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = dimens.x + (int)(dimens.width*Math.random());
            int yPos = dimens.y + (int)(dimens.height*Math.random());

            if(Math.random() < asymptomaticChance)
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < socialDistanceChance)
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            pList.add(new Person((int)(minAge + (maxAge-minAge)*Math.random()),
                    (int)(minPreExistingConditions + (maxPreExistingConditions-minPreExistingConditions)*Math.random()), xPos, yPos, dimens, disease, circleRad, asymptomatic, isSocialDistancing));
        }
    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));

            if(!pList.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(i != j && pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
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

    public ArrayList<Person> getPList() {
        return pList;
    }

    public Rectangle getDimens() {
        return dimens;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public int getSocialDistanceValue()
    {
        return socialDistanceValue;
    }
}
