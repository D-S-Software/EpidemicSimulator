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

        updateAllDimens(dimens);
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
            int personalConditions = (int) (getMinPreExistingConditions() + (getMaxPreExistingConditions() - getMinPreExistingConditions()) * Math.random());

            int xPos1 = getQ1Dimens().x + (int) (getQ1Dimens().width * Math.random());
            int yPos1 = getQ1Dimens().y + (int) (getQ1Dimens().height * Math.random());

            int xPos2 = getQ2Dimens().x + (int) (getQ2Dimens().width * Math.random());
            int yPos2 = getQ2Dimens().y + (int) (getQ2Dimens().height * Math.random());

            int xPos3 = getQ3Dimens().x + (int) (getQ3Dimens().width * Math.random());
            int yPos3 = getQ3Dimens().y + (int) (getQ3Dimens().height * Math.random());

            int xPos4 = getQ4Dimens().x + (int) (getQ4Dimens().width * Math.random());
            int yPos4 = getQ4Dimens().y + (int) (getQ4Dimens().height * Math.random());

            int xPos5 = getQ5Dimens().x + (int) (getQ5Dimens().width * Math.random());
            int yPos5 = getQ5Dimens().y + (int) (getQ5Dimens().height * Math.random());

            int xPos6 = getQ6Dimens().x + (int) (getQ6Dimens().width * Math.random());
            int yPos6 = getQ6Dimens().y + (int) (getQ6Dimens().height * Math.random());

            int xPos7 = getQ7Dimens().x + (int) (getQ7Dimens().width * Math.random());
            int yPos7 = getQ7Dimens().y + (int) (getQ7Dimens().height * Math.random());

            int xPos8 = getQ8Dimens().x + (int) (getQ8Dimens().width * Math.random());
            int yPos8 = getQ8Dimens().y + (int) (getQ8Dimens().height * Math.random());


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, dimens, disease, circleRad, asymptomatic, isSocialDistancing);

            k++;
            if(k == 1)
            {
                person.setDimens(getQ1Dimens());
                person.setxPos(xPos1);
                person.setyPos(yPos1);
                person.setQuadLocation(1);
                getPListQ1().add(person);
            }
            if(k == 2)
            {
                person.setDimens(getQ2Dimens());
                person.setxPos(xPos2);
                person.setyPos(yPos2);
                person.setQuadLocation(2);
                getPListQ2().add(person);
            }
            if(k == 3)
            {
                person.setDimens(getQ3Dimens());
                person.setxPos(xPos3);
                person.setyPos(yPos3);
                person.setQuadLocation(3);
                getPListQ3().add(person);
            }
            if(k == 4)
            {
                person.setDimens(getQ4Dimens());
                person.setxPos(xPos4);
                person.setyPos(yPos4);
                person.setQuadLocation(4);
                getPListQ4().add(person);
            }
            if(k == 5)
            {
                person.setDimens(getQ5Dimens());
                person.setxPos(xPos5);
                person.setyPos(yPos5);
                person.setQuadLocation(5);
                getPListQ5().add(person);
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
            getPList().add(getPListQ1().get(i));
        for(int i = 0; i < getPListQ2().size(); i++)
            getPList().add(getPListQ2().get(i));
        for(int i = 0; i < getPListQ3().size(); i++)
            getPList().add(getPListQ3().get(i));
        for(int i = 0; i < getPListQ4().size(); i++)
            getPList().add(getPListQ4().get(i));
        for(int i = 0; i < getPListQ5().size(); i++)
            getPList().add(getPListQ5().get(i));
        for(int i = 0; i < getPListQ6().size(); i++)
            getPList().add(getPListQ6().get(i));
        for(int i = 0; i < getPListQ7().size(); i++)
            getPList().add(getPListQ7().get(i));
        for(int i = 0; i < getPListQ8().size(); i++)
            getPList().add(getPListQ8().get(i));
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
        for(int i = 1; i < getListPList().size(); i++)
            updateDistanceFromSickIteration(getListPList().get(i));
    }

    public void updateListPList()
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            updatePList(getListPList().get(i));
        }
    }
}
