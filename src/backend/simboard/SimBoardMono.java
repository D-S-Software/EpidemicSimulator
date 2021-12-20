package backend.simboard;

import backend.disease.Disease;

import java.awt.*;

public class SimBoardMono extends SimBoard{

    /** Creates a SimBoardMono (one area) to simulate the actions of people that are displayed on SimBoardPanel
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
     * @param reinfectRate The percentage of people that can be reinfected once they loose resistance
     * @param quarantineChance The percent of people that will quarantine when sick
     * @param antiBodyTime The time it takes for antibodies to expire
     */
    public SimBoardMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, double socialDistanceValue, double socialDistanceChance, double minAge, double maxAge,
                        int minPreExistingConditions, int maxPreExistingConditions, double reinfectRate, double quarantineChance, double antiBodyTime)
    {

        super(disease, dimens,  numPeople, asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
        minPreExistingConditions, maxPreExistingConditions, 0, reinfectRate, quarantineChance, antiBodyTime);
    }

    /**
     * Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board
     */
    public void constructDimensList()
    {
        getDimensList().add(getDimens());
        getDimensList().add(getQ1Dimens());
        getDimensList().add(getTravelDimens());
    }

    /**
     * Creates an ArrayList of ArrayLists of people to be used when calling update methods
     */
    public void constructListPList()
    {
        getListPList().add(getPList());
        getListPList().add(getPListQ1());
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
        getDimensList().get(1).setRect(updatedRect);
        getDimensList().get(2).setRect(updatedRect);
    }

    /**
     * Null Method for this instance
     */
    public void drawSepLines(Graphics2D g2D) {

        //Null Method
    }
}
