package backend;

import backend.disease.Disease;
import lib.CustomColor;

import java.awt.*;

public class Person {

    private boolean hasDisease, isHealthy;
    private int timeSinceSick, timeSinceRecovered, quadLocation, othersInfected, closestSickIndex, xTarget, yTarget;
    private double directionAngle, distanceFromSick, personalMortalityRate, baseMortalityRate, ageMortalityFactor = 0.0007, conditionsMortalityFactor = 0.02, sickTime, antiBodyTime, step = 2, xPos, yPos, dx, dy;
    private boolean isSocialDistancing, isSocialDistancingSaved, isoRecovered = false, isoSick = false, asymptomatic, rAccounted = false, canReInfect, willQuarantine, hasTarget = false;
    private Rectangle dimens;
    private Disease disease;
    private final int circleRad = 5;

    /** Creates a person object for the simBoard
     *
     * @param age The age of the person (affects max time sick and mortality rate)
     * @param preExistingConditions The number of preexisting conditions (affects mortality rate)
     * @param xPos The x position of the person
     * @param yPos They y position of the person
     * @param dimens The dimensions the person can be in (Rectangle obj)
     * @param disease The disease object the person has
     * @param asymptomatic Boolean value if the person can be asymptomatic when sick
     * @param isSocialDistancing Boolean value if the person is social distancing
     * @param antiBodyTime How long it takes for antibodies to expire
     * @param canReInfect Boolean value if the person is able to be reinfected once recovered if antibodies expire
     * @param quarantineChance The chance that a person will quarantine
     */
    public Person(double age, int preExistingConditions, int xPos, int yPos, Rectangle dimens, Disease disease, boolean asymptomatic, boolean isSocialDistancing, double antiBodyTime, boolean canReInfect, double quarantineChance)
    {
        this.dimens = new Rectangle(dimens);
        this.disease = disease;

        this.asymptomatic = asymptomatic;
        this.isSocialDistancing = isSocialDistancing;
        isSocialDistancingSaved = isSocialDistancing;
        willQuarantine = Math.random() < quarantineChance;

        this.xPos = xPos;
        this.yPos = yPos;

        double maxTimeSick = disease.getBaseMaxTimeSick() + (100 * age*Math.random()); // Random chance to add 1 per year of age up to age. 100 centi Sec = 1 sec)
        sickTime = disease.getBaseMinTimeSick() + (maxTimeSick - disease.getBaseMinTimeSick())*Math.random();
        personalMortalityRate = disease.getBaseMortalityRate() + ageMortalityFactor*age + conditionsMortalityFactor*preExistingConditions;
        baseMortalityRate = personalMortalityRate;

        this.antiBodyTime = antiBodyTime;
        this.canReInfect = canReInfect;
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
        if(hasTarget)
            moveTarget();
        else
        {
            if(Math.random() >= .95)
                directionAngle = (360*Math.random());
            dy = step*Math.sin(directionAngle);
            dx = step*Math.cos(directionAngle);
            checkBoundaries();
            xPos += dx;
            yPos += dy;
        }
    }

    /**
     * Moves the person to a particular point
     * @return returns if the person is at the desired point
     */
    public boolean moveTarget()
    {
        if(xTarget < xPos)
        {
            dx = -(step*Math.cos(directionAngle));
            dy = -(step*Math.sin(directionAngle));
        }
        else
        {
            dx = step*Math.cos(directionAngle);
            dy = step*Math.sin(directionAngle);
        }
        checkBoundaries();

        if(xPos >= xTarget-(step/2) && xPos <= xTarget+(step/2) && yPos >= yTarget-(step/2) && yPos <= yTarget+(step/2))
        {
            hasTarget = false;
            return true;
        }
        else
        {
            xPos += dx;
            yPos += dy;
            hasTarget = true;
            return false;
        }
    }

    /**
     * Sets the target coordinates for a person to travel to
     * @param xTarget the x target cord
     * @param yTarget the y target cord
     */
    public void setTarget(int xTarget, int yTarget)
    {
        this.xTarget = xTarget;
        this.yTarget = yTarget;
        hasTarget = true;
        if(xTarget != xPos)
            directionAngle = Math.atan((yTarget-yPos)/(xTarget-xPos));
    }

    /**
     * Checks if a person is on the boundry and needs to change direction
     */
    private void checkBoundaries()
    {
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
    }

    /**
     * Checks each person if they should become sick / recover / die
     */
    public void checkCondition()
    {
        // checks if a healthy person should become infected
        if(!hasDisease && Math.random() < disease.getContagiousPercent() && distanceFromSick < disease.getContagiousRange())
        {
            hasDisease = true;
            isHealthy = false;
            timeSinceSick = 0;
        }
        if(hasDisease && !isHealthy) // if is sick
        {
            timeSinceSick++;
            //If sick for min time has increasing chance to die / recover until guaranteed at max time
            if(timeSinceSick > sickTime)
            {
                if(Math.random() > personalMortalityRate || asymptomatic)
                {
                    isHealthy = true;    // Recovers
                    timeSinceRecovered = 0;
                }
                else
                    hasDisease = false; //Dies
            }
        }
        if(hasDisease && isHealthy && canReInfect) // if recovered
        {
            timeSinceRecovered++;
            //checks if a recovered person should become sick
            if(timeSinceRecovered > antiBodyTime && distanceFromSick < disease.getContagiousRange() && Math.random() < disease.getContagiousPercent())
            {
                isHealthy = false;
                isoRecovered = false;
                timeSinceSick = 0;
                othersInfected = 0;
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

    /** Updates the current mortality rate based on the percentage of people sick in a given moment
     *
     * @param numSick The current number of people sick in the simulation
     * @param numPeople The total number of people in the simulation
     */
    public void updateMortalityRate(int numSick, int numPeople)
    {
        personalMortalityRate = baseMortalityRate * (1 + ((double)numSick / numPeople));
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

        g2D.fillOval((int)xPos, (int)yPos, circleRad, circleRad);
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

    public double getXPos()
    {
        return xPos;
    }

    public double getYPos()
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

    public Rectangle getDimens()
    {
        return dimens;
    }

    public int getTimeSinceSick()
    {
        return timeSinceSick;
    }

    public void setQuadLocation(int location)
    {
        quadLocation = location;
    }

    public void setIsSick()
    {
        isHealthy = false;
    }

    public void setHasDisease()
    {
        hasDisease = true;
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
    public boolean willQuarantine() {
        return willQuarantine;
    }

    public int getQuadLocation()
    {
        return quadLocation;
    }
}
