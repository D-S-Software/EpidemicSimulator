import java.awt.*;
import java.util.ArrayList;

public class SimBoardMono extends SimBoard{

    private boolean asymptomatic, isSocialDistancing; //maybe add to super

    public SimBoardMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                        int minPreExistingConditions, int maxPreExistingConditions)
    {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
        minPreExistingConditions, maxPreExistingConditions);
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
        updateDistanceFromSickIteration(getPList());
    }

    /**
     * For all the Person objects in board, they are checked for sickness, moved and removed if dead
     */
    public void updateListPList()
    {
       updatePList(getPList());
    }

    public void drawListPList(Graphics2D g2D)
    {

        for(int i = 0; i < getListPList().size(); i++) //
        {
            drawPList(getListPList().get(i), i, g2D); //i
        }
    }

}
