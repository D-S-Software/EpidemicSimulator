import java.awt.*;

public class Person {

    private boolean hasDisease, isHealthy, canSpread;
    private int age, spreadRate, xPos, yPos, dx, dy, directionAngle;
    private int circleRad = 5;
    private BoardDimensions dimensions;
    private int recoverTime = 0;
    private int step = 2;
    private double willSpread;

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
        spreadRate = 100;
        directionAngle = (int)(306*Math.random());

        if(Math.random() > .9)
            hasDisease = true;
        else hasDisease = false;

        // If this person is starting with the disease, then they also start as sick
        if(hasDisease)
            isHealthy = false;
        else isHealthy = true;
    }

    public boolean getHasDisease()
    {
        return hasDisease;
    }

    public boolean getIsHealthy()
    {
        return isHealthy;
    }

    public void setCanSpread(boolean bValue)
    {
        canSpread = bValue;
    }

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

    public void willSpread(Person other)
    {
        Double distanceFromOther = Math.sqrt(Math.pow((xPos - other.xPos), 2) + Math.pow((yPos - other.yPos), 2));
        if(distanceFromOther.equals(0))
            willSpread = spreadRate;
        else willSpread = spreadRate / distanceFromOther; //distance varies from 0 to 1000ish
    }

    public void checkSick()
    {
        // if a person is within 100 pixels of infected --> 95% change of spread
        if(!hasDisease && canSpread && Math.random() > .05 && willSpread > (spreadRate / 100))
        {
            hasDisease = true;
            isHealthy = false;
        }
        if(hasDisease && !isHealthy) // if is sick
        {
            recoverTime++;
            //If sick for 30 sec has increasing chance to die / recover until guaranteed at 100 sec
            // TODO is the timer going up by 100 or 1000 per sec (testing shows its 100)
            if(recoverTime >  3000 + (int)(8000*Math.random()))
            {
                if((int)(2*Math.random()) > 0)
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
}
