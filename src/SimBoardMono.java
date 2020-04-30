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
    }

    public void constructListPList()
    {
        getListPList().add(getPList());

        for(int i = 0; i < getNumPeople(); i++)
        {
            getPList().add(constructPerson(getDimens(), 0));
        }
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
        for(int i = 0; i < getListPList().size(); i++)
        {
            drawPList(getListPList().get(i), i, g2D);
        }
    }
}
