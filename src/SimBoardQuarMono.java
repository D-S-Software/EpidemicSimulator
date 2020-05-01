import Library.CustomColor;

import java.awt.*;
import java.beans.Customizer;

public class SimBoardQuarMono extends SimBoardMono  implements Quarantinable{

    private Rectangle quarantine;
    private int timeUntilIsolate;
    private int quarXOrigin, quarWidth;
    private double quarantineChance;

    public SimBoardQuarMono(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions);
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;
    }

    @Override
    public void updateAllDimens(Rectangle updatedRect)
    {
        int shift = 150;

        setDimens(new Rectangle(updatedRect.x, updatedRect.y, updatedRect.width - shift - 30, updatedRect.height));
        quarXOrigin = getDimens().width + getDimens().x + 30;
        quarWidth = shift;
        quarantine = new Rectangle(quarXOrigin, getDimens().y, quarWidth, getDimens().height);
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
                getPList().get(i).resetDimens(getDimensList().get(getPList().get(i).getQuadLocation()));
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
                g2D.fillRect(quarantine.x - xBuffer, quarantine.y + i*segmentLen, segmentWidth, segmentLen);
        }
    }
}
