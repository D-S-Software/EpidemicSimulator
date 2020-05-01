import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard {

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

        int xShift = 35, yShift = 10;

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
}

