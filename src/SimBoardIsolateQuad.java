import java.awt.*;

public class SimBoardIsolateQuad extends SimBoardQuad{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int width, height, newXStart, newYStart, quarX, quarWidth;
    private int shift = 50;

    public SimBoardIsolateQuad(Disease disease, Rectangle dimens, int numPeople, int travelers, int timeUntilIsolate)
    {
        super(disease, dimens, numPeople, travelers);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;

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

        newXStart = (dimens.width - dimens.x) / 2 + circleRad - shift;
        newYStart = (dimens.height - dimens.y) / 2 + circleRad;

        quarX = newXStart + width - shift/2;
        quarWidth = 2*shift + 4*circleRad;

        quarantine = new Rectangle(quarX, dimens.y, quarWidth, dimens.height);

        setQ1Dimens(new Rectangle(dimens.x, dimens.y, width, height));
        setQ2Dimens(new Rectangle(newXStart, dimens.y, width - shift, height));
        setQ3Dimens(new Rectangle(dimens.x, newYStart, width, height));
        setQ4Dimens(new Rectangle(newXStart, newYStart, width - shift, height));

        setTravelDimens(new Rectangle(dimens.x, dimens.y, dimens.width - shift - quarWidth, dimens.height));
    }

    public void quarantineCheck()
    {
        for(int i = 0; i < getPList().size(); i++)
        {
            if(getPList().get(i).getHasDisease() && !getPList().get(i).getIsHealthy() && getPList().get(i).getTimeSinceSick() > timeUntilIsolate && !getPList().get(i).isIsoSick())
            {
                getPList().get(i).resetDimens(quarantine);
                getPList().get(i).setIsoSick(true);
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
