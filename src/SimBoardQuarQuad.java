import java.awt.*;

public class SimBoardQuarQuad extends SimBoardQuad{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Start, y2Origin, quarXOrigin, quarWidth;
    private double quarantineChance;
    private int shift = 50;

    public SimBoardQuarQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
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
        }
    }

    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;

        width = (dimens.width - dimens.x) / 2 - circleRad - shift;
        height = (dimens.height - dimens.y) / 2 - circleRad;

        x2Start = (dimens.width - dimens.x) / 2 + circleRad - shift;
        y2Origin = (dimens.height - dimens.y) / 2 + circleRad;

        quarXOrigin = x2Start + width - shift/2;
        quarWidth = 2*shift + 4*circleRad;

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

    public Rectangle getQuarantine()
    {
        return quarantine;
    }
}
