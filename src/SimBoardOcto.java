import java.awt.*;
import java.util.ArrayList;

public class SimBoardOcto extends SimBoard{

    private boolean asymptomatic, isSocialDistancing;

    private int Xshift = 30, Yshift = 10;

    public SimBoardOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer)
    {
        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
            minPreExistingConditions, maxPreExistingConditions, travelersPer);
       // this.dimens = dimens;


      //  this.socialDistanceValue = socialDistanceValue;
       // this.travelDimens = dimens;
       // this.numPeople = numPeople;
        updateAllDimens(dimens);

      //  pList = new ArrayList<>();
       // pListQ1 = new ArrayList<>();
      //  pListQ2 = new ArrayList<>();
      //  pListQ3 = new ArrayList<>();
      //  pListQ4 = new ArrayList<>();
     //   pListQ5 = new ArrayList<>();
      //  pListQ6 = new ArrayList<>();
       // pListQ7 = new ArrayList<>();
       // pListQ8 = new ArrayList<>();
       // listPList.add(pList);
      //  listPList.add(pListQ1);
      //  listPList.add(pListQ2);
       // listPList.add(pListQ3);
      //  listPList.add(pListQ4);
       // listPList.add(pListQ5);
//        listPList.add(pListQ7);
//        listPList.add(pListTravel);


    }

    public void constructDimensList()
    {
        getDimensList().add(getDimens());
        getDimensList().add(getQ1Dimens());
        getDimensList().add(getQ2Dimens());
        getDimensList().add(getQ3Dimens());
        getDimensList().add(getQ4Dimens());
        getDimensList().add(getQ5Dimens());
        getDimensList().add(getQ6Dimens());
        getDimensList().add(getQ7Dimens());
        getDimensList().add(getQ8Dimens());
        getDimensList().add(getTravelDimens()); //takes place as travel dimens to make things line up. MAYBE getDimens()
    }

    public void constructListPList()
    {
        int travelers = (int)(getNumPeople()*getTravelersPer());

        int k = 0;
        for(int i = 0; i < getNumPeople() - travelers; i++) {

            if(Math.random() < getAsymptomaticChance())
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < getSocialDistanceChance())
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            int personalAge = (int) (getMinAge() + (getMaxAge() - getMinAge()) * Math.random());
            int personalConditions = (int) (getMinPreExistingConditions() + (maxPreExistingConditions - minPreExistingConditions) * Math.random());

            int xPos1 = getQ1Dimens().x + (int) (q1Dimens.width * Math.random());
            int yPos1 = q1Dimens.y + (int) (q1Dimens.height * Math.random());

            int xPos2 = q2Dimens.x + (int) (q2Dimens.width * Math.random());
            int yPos2 = q2Dimens.y + (int) (q2Dimens.height * Math.random());

            int xPos3 = q3Dimens.x + (int) (q3Dimens.width * Math.random());
            int yPos3 = q3Dimens.y + (int) (q3Dimens.height * Math.random());

            int xPos4 = q4Dimens.x + (int) (q4Dimens.width * Math.random());
            int yPos4 = q4Dimens.y + (int) (q4Dimens.height * Math.random());

            int xPos5 = q5Dimens.x + (int) (q5Dimens.width * Math.random());
            int yPos5 = q5Dimens.y + (int) (q5Dimens.height * Math.random());

            int xPos6 = q6Dimens.x + (int) (q6Dimens.width * Math.random());
            int yPos6 = q6Dimens.y + (int) (q6Dimens.height * Math.random());

            int xPos7 = q7Dimens.x + (int) (q7Dimens.width * Math.random());
            int yPos7 = q7Dimens.y + (int) (q7Dimens.height * Math.random());

            int xPos8 = q8Dimens.x + (int) (q8Dimens.width * Math.random());
            int yPos8 = q8Dimens.y + (int) (q8Dimens.height * Math.random());


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, dimens, disease, circleRad, asymptomatic, isSocialDistancing);

            k++;
            if(k == 1)
            {
                person.setDimens(q1Dimens);
                person.setxPos(xPos1);
                person.setyPos(yPos1);
                person.setQuadLocation(1);
                pListQ1.add(person);
            }
            if(k == 2)
            {
                person.setDimens(q2Dimens);
                person.setxPos(xPos2);
                person.setyPos(yPos2);
                person.setQuadLocation(2);
                pListQ2.add(person);
            }
            if(k == 3)
            {
                person.setDimens(q3Dimens);
                person.setxPos(xPos3);
                person.setyPos(yPos3);
                person.setQuadLocation(3);
                pListQ3.add(person);
            }
            if(k == 4)
            {
                person.setDimens(q4Dimens);
                person.setxPos(xPos4);
                person.setyPos(yPos4);
                person.setQuadLocation(4);
                pListQ4.add(person);
            }
            if(k == 5)
            {
                person.setDimens(q5Dimens);
                person.setxPos(xPos5);
                person.setyPos(yPos5);
                person.setQuadLocation(5);
                pListQ5.add(person);
            }
            if(k == 6)
            {
                person.setDimens(getQ6Dimens());
                person.setxPos(xPos6);
                person.setyPos(yPos6);
                person.setQuadLocation(6);
                getPListQ6().add(person);
            }
            if(k == 7)
            {
                person.setDimens(getQ7Dimens());
                person.setxPos(xPos7);
                person.setyPos(yPos7);
                person.setQuadLocation(7);
                getPListQ7().add(person);
            }
            if(k == 8)
            {
                person.setDimens(getQ8Dimens());
                person.setxPos(xPos8);
                person.setyPos(yPos8);
                person.setQuadLocation(8);
                getPListQ8().add(person);
                k = 0;
            }
        }

        for(int i = 0; i < travelers; i++)
        {
            if(Math.random() < getAsymptomaticChance())
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < getSocialDistanceChance())
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            int personalAge = (int) (getMinAge() + (getMaxAge() - getMinAge()) * Math.random());
            int personalConditions = (int) (getMinPreExistingConditions() + (getMaxPreExistingConditions() - getMinPreExistingConditions()) * Math.random());
            int xPos = getDimens().x + (int)(getDimens().width*Math.random());
            int yPos = getDimens().y + (int)(getDimens().height*Math.random());

            getPListTravel().add(new Person(personalAge, personalConditions, xPos, yPos, getTravelDimens(), getDisease(), circleRad, asymptomatic, isSocialDistancing));
        }

        for(int i = 0; i < getPListQ1().size(); i++)
            pList.add(getPListQ1().get(i));
        for(int i = 0; i < getPListQ2().size(); i++)
            pList.add(getPListQ2().get(i));
        for(int i = 0; i < getPListQ3().size(); i++)
            pList.add(getPListQ3().get(i));
        for(int i = 0; i < getPListQ4().size(); i++)
            pList.add(getPListQ4().get(i));
        for(int i = 0; i < getPListQ5().size(); i++)
            pList.add(getPListQ5().get(i));
        for(int i = 0; i < getPListQ6().size(); i++)
            pList.add(getPListQ6().get(i));
        for(int i = 0; i < getPListQ7().size(); i++)
            pList.add(getPListQ7().get(i));
        for(int i = 0; i < getPListQ8().size(); i++)
            pList.add(getPListQ8().get(i));
        for(int i = 0; i < getPListTravel().size(); i++)
            getPList().add(getPListTravel().get(i));
    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        setDimens(updatedRect);

        int width = (getDimens().width / 4) - Xshift;
        int height = (getDimens().height / 2) - Yshift;

        int q1xStart = getDimens().x;
        int q1yStart = getDimens().y;
        int q2xStart = getDimens().x + width + Xshift;
        int q2yStart = getDimens().y;
        int q3xStart = getDimens().x + 2*width + 2*Xshift;
        int q3yStart = getDimens().y;
        int q4xStart = getDimens().x + 3*width + 3*Xshift;
        int q4yStart = getDimens().y;
        int q5xStart = q1xStart;
        int q5yStart = height + 2*Yshift;
        int q6xStart = q2xStart;
        int q6yStart = height + 2*Yshift;
        int q7xStart = q3xStart;
        int q7yStart = height + 2*Yshift;
        int q8xStart = q4xStart;
        int q8yStart = height + 2*Yshift;

        setQ1Dimens(new Rectangle(q1xStart, q1yStart, width, height));
        setQ2Dimens(new Rectangle(q2xStart, q2yStart, width, height));
        setQ3Dimens(new Rectangle(q3xStart, q3yStart, width, height));
        setQ4Dimens(new Rectangle(q4xStart, q4yStart, width, height));
        setQ5Dimens(new Rectangle(q5xStart, q5yStart, width, height));
        setQ6Dimens(new Rectangle(q6xStart, q6yStart, width, height));
        setQ7Dimens(new Rectangle(q7xStart, q7yStart, width, height));
        setQ8Dimens(new Rectangle(q8xStart, q8yStart, width, height));
        setTravelDimens(getDimens());
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < pListQ1.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ1.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ1.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ1.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ1.get(i).setDistanceFromSick(minDist);
            pListQ1.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ2.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ2.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ2.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ2.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ2.get(i).setDistanceFromSick(minDist);
            pListQ2.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ3.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ3.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ3.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ3.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ3.get(i).setDistanceFromSick(minDist);
            pListQ3.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ4.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ4.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ4.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ4.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ4.get(i).setDistanceFromSick(minDist);
            pListQ4.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ5.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ5.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ5.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ5.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ5.get(i).setDistanceFromSick(minDist);
            pListQ5.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ6.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ6.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ6.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ6.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ6.get(i).setDistanceFromSick(minDist);
            pListQ6.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ7.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ7.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ7.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ7.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ7.get(i).setDistanceFromSick(minDist);
            pListQ7.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ8.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ8.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ8.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ8.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ8.get(i).setDistanceFromSick(minDist);
            pListQ8.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListTravel.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListTravel.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListTravel.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListTravel.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListTravel.get(i).setDistanceFromSick(minDist);
            pListTravel.get(i).setClosestSickIndex(closestSickIndex);
        }
    }

    public void updateListPList()
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            updatePList(getListPList().get(i), getListPList().get(0));
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */




}
