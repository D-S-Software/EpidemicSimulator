package backend.simboard;

import backend.disease.Disease;

import java.awt.*;

public class SimBoardOcto extends SimBoard{

    private int Xshift = 30, Yshift = 10;

    /** Creates a SimBoardOcto (8 areas) to simulate the actions of people that are displayed on SimBoardPanel
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
    public SimBoardOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer)
    {
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
        getDimensList().add(getQ5Dimens());
        getDimensList().add(getQ6Dimens());
        getDimensList().add(getQ7Dimens());
        getDimensList().add(getQ8Dimens());
        getDimensList().add(getTravelDimens());
    }

    /**
     * Creates an ArrayList of ArrayLists of people to be used when calling update methods
     */
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

    /**
     * Updates the dimensions of each rectangle each tick to keep each person in the correct zone
     * @param updatedRect The current rectangle for the bounds of the SimBoardPanel
     */
    public void updateDimensList(Rectangle updatedRect)
    {
        getDimensList().get(0).setRect(updatedRect);

        int width = (getDimens().width - 3*Xshift) / 4;
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

        getDimensList().get(1).setRect(q1xStart, q1yStart, width, height);
        getDimensList().get(2).setRect(q2xStart, q2yStart, width, height);
        getDimensList().get(3).setRect(q3xStart, q3yStart, width, height);
        getDimensList().get(4).setRect(q4xStart, q4yStart, width, height);
        getDimensList().get(5).setRect(q5xStart, q5yStart, width, height);
        getDimensList().get(6).setRect(q6xStart, q6yStart, width, height);
        getDimensList().get(7).setRect(q7xStart, q7yStart, width, height);
        getDimensList().get(8).setRect(q8xStart, q8yStart, width, height);
        getDimensList().get(9).setRect(getDimens());
    }
}
