import java.awt.*;
import java.util.ArrayList;

public class SimBoardOcto extends SimBoard{

    private int Xshift = 30, Yshift = 10;

    public SimBoardOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer)
    {
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
        getDimensList().add(getQ5Dimens());
        getDimensList().add(getQ6Dimens());
        getDimensList().add(getQ7Dimens());
        getDimensList().add(getQ8Dimens());
        getDimensList().add(getTravelDimens()); //takes place as travel dimens to make things line up. MAYBE getDimens()
    }

    public void constructListPList()
    {
        getListPList().add(getPList());
        getListPList().add(getPListQ1());
        getListPList().add(getPListQ2());
        getListPList().add(getPListQ3());
        getListPList().add(getPListQ4());
        getListPList().add(getPListQ5());
        getListPList().add(getPListQ6());
        getListPList().add(getPListQ7());
        getListPList().add(getPListQ8());
        getListPList().add(getPListTravel());

        constructPListNonTravel();
        constructPListTravel();
        constructPListTotal(getListPList(), 1);
    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        setDimens(updatedRect);

        int width = (getDimens().width / 4) - 4*Xshift;
        int height = (getDimens().height / 2) - Yshift;

        int q1xStart = getDimens().x;
        int q1yStart = getDimens().y;
        int q2xStart = getDimens().x + width + 4*Xshift;
        int q2yStart = getDimens().y;
        int q3xStart = getDimens().x + 2*width + 8*Xshift;
        int q3yStart = getDimens().y;
        int q4xStart = getDimens().x + 3*width + 12*Xshift;
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
}
