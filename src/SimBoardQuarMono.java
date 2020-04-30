import Library.CustomColor;

import java.awt.*;
import java.beans.Customizer;

public class SimBoardQuarMono extends SimBoardMono  implements Quarantinable{

    private Rectangle dimens, quarantine;
    private int timeUntilIsolate;
    private int quarXOrigin, quarWidth;
    private double quarantineChance;
    private int shift = 200;

    public SimBoardQuarMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        this.dimens = dimens;
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;
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

        quarXOrigin = dimens.width + dimens.x + 2*circleRad;
        quarWidth = shift - 2*circleRad;
        quarantine = new Rectangle(quarXOrigin, dimens.y, quarWidth, dimens.height);
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
                getPList().get(i).resetDimens(getDimens());
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
