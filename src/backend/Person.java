package backend;

import backend.disease.Disease;
import lib.CustomColor;

import java.awt.*;

public class Person {

    private boolean hasDisease, isHealthy;
    private int xPos, yPos, dx, dy, maxTimeSick, timeSinceSick, quadLocation, othersInfected, closestSickIndex;
    private double directionAngle, distanceFromSick, mortalityRate, step = 2, ageMortalityFactor = 0.0007, conditionsMortalityFactor = 0.02;
    private boolean isSocialDistancing, isSocialDistancingSaved, isoRecovered = false, isoSick = false, asymptomatic;
    private Rectangle dimens;
    private Disease disease;
    private final int circleRad = 8;

    public Person(int age, int preExistingConditions, int xPos, int yPos, Rectangle dimens, Disease disease, boolean asymptomatic, boolean isSocialDistancing)
    {
        this.dimens = new Rectangle(dimens);
        this.disease = disease;
        this.asymptomatic = asymptomatic;
        this.isSocialDistancing = isSocialDistancing;
        isSocialDistancingSaved = isSocialDistancing;
        this.xPos = xPos;
        this.yPos = yPos;
        maxTimeSick = disease.getBaseMaxTimeSick() + 100 * (int)(age*Math.random()); /** Random chance to add 1 per year of age up to age. 100 centi Sec = 1 sec) */
        mortalityRate = disease.getBaseMortalityRate() + ageMortalityFactor*age + conditionsMortalityFactor*preExistingConditions;
        directionAngle = (int)(360*Math.random());

        if(Math.random() > disease.getStartPercentHealthy())
            hasDisease = true;
        else hasDisease = false;

        // If this person is starting with the disease, then they also start as sick
        if(hasDisease)
            isHealthy = false;
        else isHealthy = true;
    }

    /**
     * Moves the person and decides if to change direction
     */
    public void move()
    {
        if(Math.random() >= .95)
            directionAngle = (360*Math.random());

        dx = (int)(step*Math.cos(directionAngle));
        dy = (int)(step*Math.sin(directionAngle));

        if(xPos + dx > dimens.x + dimens.width - circleRad)
        {
            xPos = dimens.x + dimens.width - circleRad - 1;
            directionAngle += 180;
        }
        if(xPos + dx < dimens.x + circleRad)
        {
            xPos = dimens.x + circleRad;
            directionAngle += 180;
        }
        else xPos += dx;

        if(yPos + dy > dimens.y + dimens.height  - circleRad)
        {
            yPos = dimens.y + dimens.height - circleRad - 1;
            directionAngle *= -1;
        }
        if(yPos + dy < dimens.y + circleRad)
        {
            yPos = dimens.y + circleRad;
            directionAngle *= -1;
        }
        else
        {
            xPos += dx;
            yPos += dy;
        }
    }

    /**
     * Checks each person if they should become sick / recover / die
     */
    public void checkCondition()
    {
        // if a person is within -- pixels of infected --> --% change of spread
        if(!hasDisease && Math.random() < disease.getContagiousPercent() && distanceFromSick < disease.getContagiousRange())
        {
            hasDisease = true;
            isHealthy = false;
        }
        if(hasDisease && !isHealthy) // if is sick
        {
            timeSinceSick++;
            //If sick for min time has increasing chance to die / recover until guaranteed at max time
            if(timeSinceSick >  disease.getBaseMinTimeSick() + (int)((maxTimeSick - disease.getBaseMinTimeSick())*Math.random()))
            {
                if(Math.random() > mortalityRate || asymptomatic)
                    isHealthy = true;    // Recovers
                else
                    hasDisease = false; //Dies
            }
        }
    }

    /** Updates the dimensions for the person to make sure they do not go out of bounds
     *
     * @param rect The Rectangle object that defines the dimensions
     */
    public void updateDimens(Rectangle rect)
    {
        dimens = new Rectangle(rect);
        dimens.width -= circleRad;
        dimens.height -= circleRad;
    }

    /** Resets the person's dimension if they move to another location (ie. Quarantine)
     *
     * @param rect The Rectangle object that defines the dimensions
     */
    public void resetDimens(Rectangle rect)
    {
        dimens = new Rectangle(rect);
        dimens.width -= circleRad;
        dimens.height -= circleRad;

        xPos = dimens.x + (int) (dimens.width * Math.random());
        yPos = dimens.y + (int) (dimens.height * Math.random());
    }

    /** Draws the person as a circle and decides what color to display based on condition
     *
     * @param g2D The graphics object to draw circles
     */
    public void draw(Graphics2D g2D)
    {
        Color color;
        if(hasDisease && !isHealthy)
        {
            if(asymptomatic)
                color = CustomColor.ASYMPTOMATIC;
            else
                color = CustomColor.SICK;
        }
        else if(!hasDisease && isHealthy)
        {
            color = CustomColor.HEALTHY;
        }
        else // (hasDisease && isHealthy)
        {
            color = CustomColor.RECOVERED;
        }

        g2D.setPaint(color);

        g2D.fillOval(xPos,yPos, circleRad, circleRad);
    }

    /** Getter and Setter Methods */

    public void setStep(double step)
    {
        this.step = step;
    }

    public boolean getHasDisease()
    {
        return hasDisease;
    }

    public boolean getIsHealthy()
    {
        return isHealthy;
    }

    public boolean isSocialDistancing()
    {
        return isSocialDistancing;
    }

    public void setIsSocialDistancing(boolean socialDist)
    {
        isSocialDistancing = socialDist;
    }

    public boolean getIsSocialDistancingSaved() {
        return isSocialDistancingSaved;
    }

    public void setDistanceFromSick(double dist)
    {
        distanceFromSick = dist;
    }

    public void addOthersInfected()
    {
        othersInfected++;
    }

    public int getOthersInfected()
    {
        return othersInfected;
    }

    public void setClosestSickIndex(int index)
    {
        closestSickIndex = index;
    }

    public int getClosestSickIndex()
    {
        return closestSickIndex;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }

    public void changeDirectionAngle()
    {
        directionAngle += 180;
    }

    public void setDimens(Rectangle dimens) {
        this.dimens = dimens;
    }

    public int getTimeSinceSick()
    {
        return timeSinceSick;
    }

    public void setQuadLocation(int location)
    {
        quadLocation = location;
    }

    /** SimBoardIso Methods*/

    public boolean isIsoRecovered() {
        return isoRecovered;
    }
    public void setIsoRecovered(boolean isoRecovered) {
        this.isoRecovered = isoRecovered;
    }
    public boolean isIsoSick() {
        return isoSick;
    }
    public void setIsoSick(boolean isoSick) {
        this.isoSick = isoSick;
    }
    public boolean isAsymptomatic() {
        return asymptomatic;
    }

    public int getQuadLocation()
    {
        return quadLocation;
    }
}
