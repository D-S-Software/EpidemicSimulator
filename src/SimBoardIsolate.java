import java.awt.*;

public class SimBoardIsolate extends SimBoardQuad{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int width, height, newXStart, newYStart, quarX, quarWidth;
    private int shift = 50;

    public SimBoardIsolate(Disease disease, Rectangle dimens, int numPeople, int travelers, int timeUntilIsolate)
    {
        super(disease, dimens, numPeople, travelers);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;
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

        super.setQ1Dimens(new Rectangle(dimens.x, dimens.y, width, height));
        super.setQ2Dimens(new Rectangle(newXStart, dimens.y, width - shift, height));
        super.setQ3Dimens(new Rectangle(dimens.x, newYStart, width, height));
        super.setQ4Dimens(new Rectangle(newXStart, newYStart, width - shift, height));

        super.setTravelDimens(new Rectangle(dimens.x, dimens.y, dimens.width - shift - quarWidth, dimens.height));
    }

    public void quarantineCheck()
    {
        for(int i = 0; i < super.getPList().size(); i++)
        {
            if(super.getPList().get(i).getHasDisease() && !super.getPList().get(i).getIsHealthy() && super.getPList().get(i).getTimeSinceSick() > timeUntilIsolate && !super.getPList().get(i).isIsoSick())
            {
                super.getPList().get(i).resetDimens(quarantine);
                super.getPList().get(i).setIsoSick(true);
            }
            if(super.getPList().get(i).getHasDisease() && super.getPList().get(i).getIsHealthy() && !super.getPList().get(i).isIsoRecovered())
            {
                if(super.getPList().get(i).getQuadLocation() == 0)
                {
                    super.getPList().get(i).resetDimens(super.getTravelDimens());
                }
                if(super.getPList().get(i).getQuadLocation() == 1)
                {
                    super.getPList().get(i).resetDimens(super.getQ1Dimens());
                }
                if(super.getPList().get(i).getQuadLocation() == 2)
                {
                    super.getPList().get(i).resetDimens(super.getQ2Dimens());
                }
                if(super.getPList().get(i).getQuadLocation() == 3)
                {
                    super.getPList().get(i).resetDimens(super.getQ3Dimens());
                }
                if(super.getPList().get(i).getQuadLocation() == 4)
                {
                    super.getPList().get(i).resetDimens(super.getQ4Dimens());
                }
                super.getPList().get(i).setIsoRecovered(true);
                super.getPList().get(i).setIsoSick(false);
            }
        }
    }

    public Rectangle getQuarantine()
    {
        return quarantine;
    }
}
