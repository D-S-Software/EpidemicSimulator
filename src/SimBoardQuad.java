import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard {

    private boolean asymptomatic, isSocialDistancing;
    private int xShift = 30, yShift = 10;

    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer) {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
                minPreExistingConditions, maxPreExistingConditions, travelersPer);
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

        getListPList().add(getPList());
        getListPList().add(getPListQ1());
        getListPList().add(getPListQ2());
        getListPList().add(getPListQ3());
        getListPList().add(getPListQ4());
        getListPList().add(getPListTravel());

        constructPListNonTravel();
        constructPListTravel();
        constructPListTotal(getListPList(), 1);
    }

    public void updateAllDimens(Rectangle updatedRect) {

        setDimens(updatedRect);

        int width = getDimens().width / 2 - xShift;
        int height = getDimens().height / 2 - yShift;

        int xS2tart = getDimens().x + width + xShift;
        int y2Start = getDimens().y + height + yShift;

        setQ1Dimens( new Rectangle(getDimens().x, getDimens().y, width, height));
        setQ2Dimens( new Rectangle(xS2tart, getDimens().y, width, height));
        setQ3Dimens( new Rectangle(getDimens().x, y2Start, width, height));
        setQ4Dimens(new Rectangle(xS2tart, y2Start, width, height));
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

