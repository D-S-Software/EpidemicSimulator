package backend.simboard;

import backend.disease.Disease;
import lib.CustomColor;

import java.awt.*;

public class SimBoardQuad extends SimBoard {

    int width, height, xShift, yShift, x2Start, y2Start;

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
     * @param reinfectRate The percentage of people that can be reinfected once they loose resistance
     * @param quarantineChance The percent of people that will quarantine when sick
     * @param antiBodyTime The time it takes for antibodies to expire
     */
    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, double socialDistanceValue, double socialDistanceChance,
                        double minAge, double maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, double reinfectRate, double quarantineChance, double antiBodyTime) {

        super(disease, dimens,  numPeople, asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
                minPreExistingConditions, maxPreExistingConditions, travelersPer, reinfectRate, quarantineChance, antiBodyTime);
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
        getDimensList().add(getTravelDimens());
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
     * @param updatedRect The current rectangle for the bounds of the SimBoardPanel
     */
    public void updateDimensList(Rectangle updatedRect) {

        getDimensList().get(0).setRect(updatedRect);

        xShift = 35;
        yShift = 10;

        width = (getDimensList().get(0).width - xShift) / 2;
        height = getDimensList().get(0).height / 2 - yShift;

        x2Start = getDimensList().get(0).x + width + xShift;
        y2Start = getDimensList().get(0).y + height + yShift;

        getDimensList().get(1).setRect(getDimens().x, getDimens().y, width, height);
        getDimensList().get(2).setRect(x2Start, getDimens().y, width, height);
        getDimensList().get(3).setRect(getDimens().x, y2Start, width, height);
        getDimensList().get(4).setRect(x2Start, y2Start, width, height);
        getDimensList().get(5).setRect(getDimens());
    }

    /**
     * Draws a line to separate each subsection
     * @param g2D graphics object used to draw the line
     */
    public void drawSepLines(Graphics2D g2D) {

        int segmentWidth = 4;
        int segmentYLen = getDimens().height/25;
        int segmentXLen = getDimens().width/31;
        g2D.setColor(CustomColor.EERIE_BLACK);

        for(int i = 0; i < 25; i++)
        {
            if(i%2 == 0)
                g2D.fillRect(getDimens().width/2, getDimens().y + i*segmentYLen, segmentWidth, segmentYLen );
        }
        for(int i = 0; i < 31; i++)
        {
            if(i%2 == 0)
                g2D.fillRect(getDimens().x + i*segmentXLen, getDimens().height/2, segmentXLen, segmentWidth );
        }
    }
}

