import Library.CustomColor;

import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.awt.*;

public class SimBoardQuarQuad extends SimBoardQuad implements Quarantinable{

    private Rectangle quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Start, y2Origin, quarXOrigin, quarWidth;
    private double quarantineChance;

    public SimBoardQuarQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;
    }

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
                if(getPList().get(i).getQuadLocation() == 0)
                {
                    getPList().get(i).resetDimens(getTravelDimens());
                }
                if(getPList().get(i).getQuadLocation() == 1)
                {
                    getPList().get(i).resetDimens(getQ1Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 2)
                {
                    getPList().get(i).resetDimens(getQ2Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 3)
                {
                    getPList().get(i).resetDimens(getQ3Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 4)
                {
                    getPList().get(i).resetDimens(getQ4Dimens());
                }
                getPList().get(i).setIsoRecovered(true);
                getPList().get(i).setIsoSick(false);
            }
        }
    }

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
