import java.awt.*;
import java.util.ArrayList;

public class SimBoardMono extends SimBoard{

   //private Rectangle dimens;
   // ArrayList<Rectangle> dimensList;
   //private ArrayList<Person> pList;
  // private ArrayList<ArrayList<Person>> listPList;
    private boolean asymptomatic, isSocialDistancing; //maybe add to super
   //private int numPeople;
   //private int socialDistanceValue;

    public SimBoardMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                        int minPreExistingConditions, int maxPreExistingConditions)
    {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
        minPreExistingConditions, maxPreExistingConditions);
       // this.dimens = dimens;
       // dimensList = new ArrayList<>();


        //this.socialDistanceValue = socialDistanceValue;
        //this.numPeople = numPeople;

       // pList = new ArrayList<>();
       // listPList = new ArrayList<>();
        //listPList.add(pList);

    }

    public void constructDimensList()
    {
        getDimensList().add(getDimens());

    }

    public void constructListPList()
    {
        for(int i = 0; i < getNumPeople(); i++)
        {
            int xPos = getDimens().x + (int)(getDimensList().get(0).width*Math.random());
            int yPos = getDimens().y + (int)(getDimensList().get(0).height*Math.random());

            if(Math.random() < getAsymptomaticChance())
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < getSocialDistanceChance())
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            getPList().add(new Person((int)(getMinAge() + (getMaxAge() - getMinAge())*Math.random()),
                    (int)(getMinPreExistingConditions() + (getMaxPreExistingConditions() - getMinPreExistingConditions())*Math.random()), xPos, yPos, getDimens(), getDisease(), circleRad, asymptomatic, isSocialDistancing));
        }
        getListPList().add(getPList());
    }



    public void updateAllDimens(Rectangle updatedRect)
    {
        setDimens(updatedRect);
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < getPList().size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(getDimens().width, 2) + Math.pow(getDimens().height, 2));
            int closestSickIndex = 0;

            if(!getPList().get(i).getHasDisease())
                for(int j = 0; j < getPList().size(); j++)
                    if(i != j && getPList().get(j).getHasDisease() && !getPList().get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(getPList().get(i).getXPos() - getPList().get(j).getXPos(), 2) + Math.pow(getPList().get(i).getYPos() - getPList().get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            getPList().get(i).setDistanceFromSick(minDist);
            getPList().get(i).setClosestSickIndex(closestSickIndex);
        }
    }



    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updateListPList()
    {
       updatePList(getPList(),getPList());
    }



    //public ArrayList<Person> getPList() {
      //  return pList;
    //}

    //public Rectangle getDimens() {
      //  return dimens;
    //}

    //public int getNumPeople() {
      //  return numPeople;
   // }

    //public int getSocialDistanceValue()
    //{
     //   return socialDistanceValue;
    //}
}
