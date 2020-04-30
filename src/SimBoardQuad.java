import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard {


    private boolean asymptomatic, isSocialDistancing;

    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer) {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
                minPreExistingConditions, maxPreExistingConditions,travelersPer);

        updateAllDimens(dimens);
    }

    public void constructDimensList()
    {
        getDimensList().add(getDimens());
        getDimensList().add(getQ1Dimens());
        getDimensList().add(getQ2Dimens());
        getDimensList().add(getQ3Dimens());
        getDimensList().add(getQ4Dimens());
        getDimensList().add(getTravelDimens()); //takes place as travel dimens to make things line up. MAYBE getDimens()
    }


    public void constructListPList() {

        int travelers = (int)(getNumPeople()*getTravelersPer());

        int k = 0;
        for (int i = 0; i < getNumPeople() - travelers; i++) {



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


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, getDimens(), getDisease(), circleRad, asymptomatic, isSocialDistancing);

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
        for(int i = 0; i < getPListTravel().size(); i++)
            getPList().add(getPListTravel().get(i));

        getListPList().add(getPList());
        getListPList().add(getPListQ1());
        getListPList().add(getPListQ2());
        getListPList().add(getPListQ3());
        getListPList().add(getPListQ4());
        getListPList().add(getPListTravel());

    }

    public void updateAllDimens(Rectangle updatedRect) {
         setDimens(updatedRect);

        int width = (getDimens().width - getDimens().x) / 2 - circleRad;
        int height = (getDimens().height - getDimens().y) / 2 - circleRad;

        int newXStart = (getDimens().width - getDimens().x) / 2 + circleRad;
        int newYStart = (getDimens().height - getDimens().y) / 2 + circleRad;

        setQ1Dimens( new Rectangle(getDimens().x, getDimens().y, width, height));
        setQ2Dimens( new Rectangle(newXStart, getDimens().y, width, height));
        setQ3Dimens( new Rectangle(getDimens().x, newYStart, width, height));
        setQ4Dimens(new Rectangle(newXStart, newYStart, width, height));
        setTravelDimens(getDimens());
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick() {

        for(int i = 1; i < getListPList().size(); i++)
            updateDistanceFromSickIteration(getListPList().get(i));
    }

    public void updateListPList() {
        for (int i = 1; i < getListPList().size(); i++) {
            updatePList(getListPList().get(i));
        }
    }

    public void drawListPList(Graphics2D g2D)
    {

        for(int i = 1; i < getListPList().size(); i++) //
        {
            drawPList(getListPList().get(i), i, g2D); //i
        }
    }
}

