import Library.CustomColor;

import java.awt.*;

public class SimBoardQuarQuad extends SimBoardQuad implements Quarantinable{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Start, y2Origin, quarXOrigin, quarWidth;
    private double quarantineChance;
    private int shift = 50;

    public SimBoardQuarQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
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
        }
    }

    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;

        width = (dimens.width - dimens.x) / 2 - shift;
        height = (dimens.height - dimens.y) / 2;

        x2Start = (dimens.width - dimens.x) / 2 - shift;
        y2Origin = (dimens.height - dimens.y) / 2;

        quarXOrigin = x2Start + width - shift/2;
        quarWidth = 3*shift;

        quarantine = new Rectangle(quarXOrigin, dimens.y, quarWidth, dimens.height);

        setQ1Dimens(new Rectangle(dimens.x, dimens.y, width, height));
        setQ2Dimens(new Rectangle(x2Start, dimens.y, width - shift, height));
        setQ3Dimens(new Rectangle(dimens.x, y2Origin, width, height));
        setQ4Dimens(new Rectangle(x2Start, y2Origin, width - shift, height));

        setTravelDimens(new Rectangle(dimens.x, dimens.y, dimens.width - shift - quarWidth, dimens.height));
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
