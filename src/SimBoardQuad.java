import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard {

    /** Creates a SimBoardQuad (4 areas) to simulate the actions of people that are displayed on SimBoardPanel
     *
     * @param disease The disease object used for a simulation
     * @param dimens The rectangle that represents the area a person can move in
     * @param numPeople The total number of people in the simulation
     * @param asymptomaticChance The percent of people who are asymptomatic
     * @param socialDistanceValue The number of pixels each person tries to stay apart by when social distancing is enabled
     * @param socialDistanceChance The percent of people who participate in social distancing
     * @param minAge The minimum age of a person in the simulation
     * @param maxAge The maximum age of a person in the simulation
     * @param minPreExistingConditions The minimum pre-existing conditions of a person in the simulation
     * @param maxPreExistingConditions The maximum pre-existing conditions of a person in the simulation
     * @param travelersPer The percent of people who travel without bound in a quad or octo board
     */
    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer) {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
                minPreExistingConditions, maxPreExistingConditions, travelersPer);
    }

    /**
     * Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board
     */
    public void constructDimensList()
    {
        getDimensList().add(getDimens());
        getDimensList().add(getQ1Dimens());
        getDimensList().add(getQ2Dimens());
        getDimensList().add(getQ3Dimens());
        getDimensList().add(getQ4Dimens());
        getDimensList().add(getTravelDimens()); //takes place as travel dimens to make things line up. MAYBE getDimens()
    }

    /**
     * Creates an ArrayList of ArrayLists of people to be used when calling update methods
     */
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

    /**
     * Updates the dimensions of each rectangle each tick to keep each person in the correct zone
     * @param updatedRect The current rectangle for the bounds of the SimBoard Panel
     */
    public void updateAllDimens(Rectangle updatedRect) {

        getDimensList().get(0).setRect(updatedRect);

        int xShift = 35, yShift = 10;

        int width = getDimensList().get(0).width / 2 - xShift;
        int height = getDimensList().get(0).height / 2 - yShift;

        int xS2tart = getDimensList().get(0).x + width + xShift;
        int y2Start = getDimensList().get(0).y + height + yShift;

        getDimensList().get(1).setRect(getDimens().x, getDimens().y, width, height);
        getDimensList().get(2).setRect(xS2tart, getDimens().y, width, height);
        getDimensList().get(3).setRect(getDimens().x, y2Start, width, height);
        getDimensList().get(4).setRect(xS2tart, y2Start, width, height);
        getDimensList().get(5).setRect(getDimens());
    }
}

