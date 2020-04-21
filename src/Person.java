import java.awt.*;

public class Person {

    private boolean hasDisease, isHealthy;
    private int age, baseSpreadRate, xPos, yPos, dx, dy, directionAngle;
    private int circleRad = 5, recoverTime = 0, step = 2, contagiousRange = 5;
    private double distanceFromSick, contagiousPercent = .05;
    private BoardDimensions dimensions;

    /**
     *
     * @param age
     * @param xPos
     * @param yPos
     */
    public Person(int age, int xPos, int yPos, BoardDimensions dimensions)
    {
        this.dimensions = new BoardDimensions(dimensions);
        this.age = age;
        this.xPos = xPos;
        this.yPos = yPos;
        baseSpreadRate = 1; // increase this to make the person more likely to be infected
        directionAngle = (int)(306*Math.random());

        if(Math.random() > .95)
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
        if(!hasDisease && Math.random() < contagiousPercent && distanceFromSick < contagiousRange * baseSpreadRate)
        {
            hasDisease = true;
            isHealthy = false;
        }
        if(hasDisease && !isHealthy) // if is sick
        {
            recoverTime++;
            //If sick for 30 sec has increasing chance to die / recover until guaranteed at 110 sec
            if(recoverTime >  3000 + (int)(8000*Math.random()))
            {
                if(Math.random() > .8)
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
        g2D.fillOval(xPos + circleRad,yPos + circleRad, circleRad, circleRad);
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
