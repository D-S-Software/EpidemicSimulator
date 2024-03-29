package backend.simboard;

import backend.disease.Disease;
import lib.CustomColor;

import java.awt.*;

public class SimBoardQuarMono extends SimBoardMono  implements Quarantinable {

    private Rectangle quarantine;
    private int quarXOrigin, quarWidth;
    private double timeUntilIsolate;

    /** Creates a SimBoardQuarMono (one area, quarantine board) to simulate the actions of people that are displayed on SimBoardPanel
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
     * @param antiBodyTime The time it takes for antibodies to expire
     * @param quarantineChance The percent of people that will quarantine when sick
     * @param timeUntilIsolate The time it takes for a sick person to go into quarantine
     */
    public SimBoardQuarMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, double socialDistanceValue, double socialDistanceChance,
                            double minAge, double maxAge, int minPreExistingConditions, int maxPreExistingConditions, double timeUntilIsolate, double reinfectRate, double quarantineChance, double antiBodyTime)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, reinfectRate, quarantineChance, antiBodyTime);
        this.timeUntilIsolate = timeUntilIsolate;
    }

    /** Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board (including the quarantine zone)
     *
     * @param updatedRect The current rectangle for the bounds of the SimBoardPanel
     */
    @Override
    public void updateDimensList(Rectangle updatedRect)
    {
        int shift = 150;

        getDimensList().get(0).setRect(updatedRect.x, updatedRect.y, updatedRect.width - shift - 30, updatedRect.height);
        quarXOrigin = getDimensList().get(0).width + getDimensList().get(0).x + 30;
        quarWidth = shift;
        quarantine = new Rectangle(quarXOrigin, getDimensList().get(0).y, quarWidth, getDimensList().get(0).height);

        super.updateDimensList(getDimensList().get(0));
    }

    /**
     * Checks if a sick person needs to go into quarantine if they participate. Then checks if they recover and returns them back to their section of the board
     */
    public void quarantineCheck()
    {
        for(int i = 0; i < getPList().size(); i++)
        {
            if(getPList().get(i).getHasDisease() && !getPList().get(i).isAsymptomatic() && !getPList().get(i).getIsHealthy() && getPList().get(i).getTimeSinceSick() > timeUntilIsolate && getPList().get(i).isIsoSick())
            {
                if(getPList().get(i).willQuarantine())
                {
                    getPList().get(i).resetDimens(quarantine);
                    getPList().get(i).setIsoSick(true);
                }
            }
            if(getPList().get(i).getHasDisease() && getPList().get(i).getIsHealthy() && getPList().get(i).isIsoRecovered())
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

        g2D.setColor(CustomColor.EERIE_BLACK);

        g2D.fillRect(quarantine.x - xBuffer, quarantine.y, segmentWidth, quarantine.height);
    }
}
