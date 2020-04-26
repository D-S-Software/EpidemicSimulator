import java.awt.*;

public class SimBoardIsolateRandom extends SimBoardRandom{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int quarX, quarWidth;
    private int shift = 200;

    public SimBoardIsolateRandom(Disease disease, Rectangle dimens, int numPeople, int timeUntilIsolate)
    {
        super(disease, dimens, numPeople);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;
        updateAllDimens(dimens);

        for(int i = 0; i < getPList().size(); i++)
        {
            getPList().get(i).resetDimens(this.dimens);
        }
    }

    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = new Rectangle(updatedRect.x, updatedRect.y, updatedRect.width - shift, updatedRect.height);

        quarX = dimens.width + dimens.x + 2*circleRad;
        quarWidth = shift - 2*circleRad;
        quarantine = new Rectangle(quarX, dimens.y, quarWidth, dimens.height);
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
                getPList().get(i).resetDimens(getDimens());
                getPList().get(i).setIsoRecovered(true);
                getPList().get(i).setIsoSick(false);
            }
        }
    }

    @Override
    public Rectangle getDimens()
    {
        return dimens;
    }

    public Rectangle getQuarantine()
    {
        return quarantine;
    }
}
