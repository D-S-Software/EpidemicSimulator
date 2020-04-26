import java.awt.*;

public class SimBoardIsolateRandom extends SimBoardRandom{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int quarX, quarWidth;
    private double quarentineChance;
    private int shift = 200;

    public SimBoardIsolateRandom(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                                 int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, int timeUntilIsolate, double quarentineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarentineChance = quarentineChance;
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
            if(getPList().get(i).getHasDisease() && !getPList().get(i).isAsymptomatic() && !getPList().get(i).getIsHealthy() && getPList().get(i).getTimeSinceSick() > timeUntilIsolate && !getPList().get(i).isIsoSick())
            {
                if(Math.random() < quarentineChance)
                {
                    getPList().get(i).resetDimens(quarantine);
                    getPList().get(i).setIsoSick(true);
                }
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
