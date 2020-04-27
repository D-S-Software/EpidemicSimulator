import Library.CustomColor;

import java.awt.*;

public class SimBoardQuarOcto extends SimBoardOcto implements Quarantinable {

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Origin, y2Origin, x3Origin, x4Origin, quarXOrigin, quarWidth;
    private double quarantineChance;
    private int xShift = 30, yShift = 30;

    public SimBoardQuarOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, int travelers, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;

        updateAllDimens(dimens);

        for(int i = 0; i < getPList().size(); i++)
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
            if(getPList().get(i).getQuadLocation() == 5)
            {
                getPList().get(i).resetDimens(getQ5Dimens());
            }
            if(getPList().get(i).getQuadLocation() == 6)
            {
                getPList().get(i).resetDimens(getQ6Dimens());
            }
            if(getPList().get(i).getQuadLocation() == 7)
            {
                getPList().get(i).resetDimens(getQ7Dimens());
            }
            if(getPList().get(i).getQuadLocation() == 8)
            {
                getPList().get(i).resetDimens(getQ8Dimens());
            }
        }
    }

    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;

        width = (dimens.width - dimens.x) / 4 - 2* xShift;
        height = (dimens.height - dimens.y) / 2 - yShift;

        x2Origin = dimens.x + width + xShift;
        y2Origin = (dimens.height - dimens.y) / 2 + yShift;

        x3Origin = dimens.x + 2* width + 2* xShift;

        x4Origin = dimens.x + 3* width + 3* xShift;

        quarXOrigin = x4Origin + width + xShift;
        quarWidth = dimens.width - 4* width - 4* xShift - circleRad;

        quarantine = new Rectangle(quarXOrigin, dimens.y, quarWidth, dimens.height);

        setQ1Dimens(new Rectangle(dimens.x, dimens.y, width, height));
        setQ2Dimens(new Rectangle(x2Origin, dimens.y, width, height));
        setQ3Dimens(new Rectangle(x3Origin, dimens.y, width, height));
        setQ4Dimens(new Rectangle(x4Origin, dimens.y, width, height));
        setQ5Dimens(new Rectangle(dimens.x, y2Origin, width, height));
        setQ6Dimens(new Rectangle(x2Origin, y2Origin, width, height));
        setQ7Dimens(new Rectangle(x3Origin, y2Origin, width, height));
        setQ8Dimens(new Rectangle(x4Origin, y2Origin, width, height));

        setTravelDimens(new Rectangle(dimens.x, dimens.y, dimens.x + x4Origin + width, dimens.height));
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
                if(getPList().get(i).getQuadLocation() == 5)
                {
                    getPList().get(i).resetDimens(getQ5Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 6)
                {
                    getPList().get(i).resetDimens(getQ6Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 7)
                {
                    getPList().get(i).resetDimens(getQ7Dimens());
                }
                if(getPList().get(i).getQuadLocation() == 8)
                {
                    getPList().get(i).resetDimens(getQ8Dimens());
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
        int segmentLen = dimens.height/15;

        g2D.setColor(CustomColor.EERIE_BLACK);

        for(int i = 0; i < 17; i++)
        {
            if(i%2 == 0)
                g2D.fillRect(quarantine.x - xBuffer, quarantine.y + i*segmentLen, segmentWidth, segmentLen );
        }

    }


    public Rectangle getQuarantine()
    {
        return quarantine;
    }
}