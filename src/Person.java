import java.awt.*;

public class Person {

    private boolean hasDisease, isHealthy;
    private int xPos, yPos, dx, dy, directionAngle;
    private int circleRad = 8, recoverTime = 0, step = 2;
    private double distanceFromSick, mortalityRate;
    private Dimensions dimensions;
    private Disease disease;


    public Person(int age, boolean preExistingConditions, int xPos, int yPos, Dimensions dimensions, Disease disease)
    {
        this.dimensions = new Dimensions(dimensions);
        this.disease = disease;
        this.xPos = xPos;
        this.yPos = yPos;
        mortalityRate = disease.getBaseMortalityRate(); // increase this to make the person more likely to die (0 --> 1) Use age and preExistingConditions
        directionAngle = (int)(306*Math.random());

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
     *///TODO: Person objects should not phase through each other
    public void move()
    {
        if(Math.random() >= .95)
            directionAngle = (int)(360*Math.random());

        dx = (int)(step*Math.cos(directionAngle));
        dy = (int)(step*Math.sin(directionAngle));

        if(xPos + dx > dimensions.xLen - circleRad)
        {
            xPos = dimensions.xLen - circleRad - 1;
            directionAngle += 180;
        }
        if(xPos + dx < dimensions.xOrigin + circleRad)
        {
            xPos = circleRad;
            directionAngle += 180;
        }
        else xPos += dx;

        if(yPos + dy > dimensions.yLen - circleRad)
        {
            yPos = dimensions.yLen - circleRad - 1;
            directionAngle *= -1;
        }
        if(yPos + dy < dimensions.yOrigin + circleRad)
        {
            yPos = circleRad;
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
            recoverTime++;
            //If sick for 10 sec has increasing chance to die / recover until guaranteed at 1 min
            if(recoverTime >  1000 + (int)(5000*Math.random()))
            {
                if(Math.random() > mortalityRate)
                    isHealthy = true;    // Recovers
                else hasDisease = false; //Dies
            }
        }
    }

    public void draw(Graphics2D g2D)
    {
        //maybe have Color be a field and its own method to update color
        Color color;
        if(hasDisease && !isHealthy)
            color = Color.RED;
        else if(!hasDisease && isHealthy)
        {
            color = Color.BLUE;
        }
        else // (hasDisease && isHealthy)
        {
            color = Color.GRAY;
        }

        g2D.setPaint(color);

        g2D.fillOval(xPos,yPos, circleRad, circleRad);
    }

    /** Getter and Setter Methods */

    public boolean getHasDisease()
    {
        return hasDisease;
    }

    public boolean getIsHealthy()
    {
        return isHealthy;
    }

    public void setDistanceFromSick(double dist)
    {
        distanceFromSick = dist;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }
}
