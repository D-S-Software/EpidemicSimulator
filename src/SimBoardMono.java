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
}
