import java.awt.*;
import java.util.ArrayList;

public class SimBoardMono extends SimBoard{

    public SimBoardMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                        int minPreExistingConditions, int maxPreExistingConditions)
    {

        super(disease, dimens,  numPeople, asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
        minPreExistingConditions, maxPreExistingConditions, 0 );
    }

    public void constructDimensList()
    {
        getDimensList().add(getDimens());
        getDimensList().add(getQ1Dimens());
        getDimensList().add(getTravelDimens());
    }

    public void constructListPList()
    {
        getListPList().add(getPList());
        getListPList().add(getPListQ1());
        getListPList().add(getPListTravel());

        constructPListNonTravel();
        constructPListTravel();
        constructPListTotal(getListPList(), 1);

    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        setDimens(updatedRect);
        setQ1Dimens(getDimens());
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

    /**
     * For all the Person objects in board, they are checked for sickness, moved and removed if dead
     */
    public void updateListPList()
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            updatePList(getListPList().get(i));
        }
    }

    public void drawListPList(Graphics2D g2D)
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            drawPList(getListPList().get(i), i, g2D);
        }
    }
}
