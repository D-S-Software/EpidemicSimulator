import Library.CustomColor;

import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.awt.*;

public class SimBoardQuarQuad extends SimBoardQuad implements Quarantinable{

    private Rectangle quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Start, y2Origin, quarXOrigin, quarWidth;
    private double quarantineChance;

    /** Creates a SimBoardQuarQuad (4 areas, quarantine board) to simulate the actions of people that are displayed on SimBoardPanel
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
    public SimBoardQuarQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;
    }

    /** Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board (including the quarantine zone)
     *
     * @param updatedRect The current rectangle for the bounds of the SimBoard Panel
     */
    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        int xShift = 120, yShift = 10;

        setDimens(updatedRect);

        width = getDimens().width / 2 - xShift;
        height = getDimens().height / 2 - yShift;

        x2Start = getDimens().x + width + xShift / 6;
        y2Origin = getDimens().height / 2 + yShift;

        quarXOrigin = x2Start + width + xShift / 5;
        quarWidth = getDimens().width - quarXOrigin;

        quarantine = new Rectangle(quarXOrigin, getDimens().y, quarWidth, getDimens().height);

        setQ1Dimens(new Rectangle(getDimens().x, getDimens().y, width, height));
        setQ2Dimens(new Rectangle(x2Start, getDimens().y, width, height));
        setQ3Dimens(new Rectangle(getDimens().x, y2Origin, width, height));
        setQ4Dimens(new Rectangle(x2Start, y2Origin, width, height));

        setTravelDimens(new Rectangle(getDimens().x, getDimens().y, getDimens().width - xShift - quarWidth, getDimens().height));
    }

    /**
     * Checks if a sick person needs to go into quarantine if they participate. Then checks if they recover and returns them back to their section of the board
     */
    public void quarantineCheck()
    {
        for(int i = 0; i < getPList().size(); i++)
        {
            if(getPList().get(i).getHasDisease() && !getPList().get(i).isAsymptomatic() && !getPList().get(i).getIsHealthy() && getPList().get(i).getTimeSinceSick() > timeUntilIsolate && !getPList().get(i).isIsoSick())
            {
                if(Math.random() < quarantineChance)
                {
                    getPList().get(i).resetDimens(quarantine);
                    getPList().get(i).setIsoSick(true);
                }
            }
            if(getPList().get(i).getHasDisease() && getPList().get(i).getIsHealthy() && !getPList().get(i).isIsoRecovered())
            {
                getPList().get(i).resetDimens(getDimensList().get(getPList().get(i).getQuadLocation()));
                getPList().get(i).setIsoRecovered(true);
                getPList().get(i).setIsoSick(false);
            }
        }
    }

    /**
     * Draws a line to mark of the quarantine zone
     * @param g2D graphics object used to draw the line
     */
    @Override
    public void drawQuarLine(Graphics2D g2D) {

        int segmentWidth = 4;
        int xBuffer = 12;
        int segmentLen = getDimens().height/15;

        g2D.setColor(CustomColor.EERIE_BLACK);

        for(int i = 0; i < 17; i++)
        {
            if(i%2 == 0)
                g2D.fillRect(quarantine.x - xBuffer, quarantine.y + i*segmentLen, segmentWidth, segmentLen );
        }
    }
}
