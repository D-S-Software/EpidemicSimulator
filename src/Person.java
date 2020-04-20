import java.awt.*;
import java.awt.event.ActionEvent;

public class Person {

    private boolean hasDisease, isHealthy;
    private int age, spreadRate, xPos, yPos, directionAngle;
    private double changeOfDirectionChange;
    private int circleRad = 3;
    private BoardDimensions dimensions;
    private int recoverTime = 0;

    /**
     *
     * @param age
     * @param hasDisease
     * @param xPos
     * @param yPos
     */
    public Person(int age, boolean hasDisease, int xPos, int yPos, BoardDimensions dimensions)
    {
        this.dimensions = new BoardDimensions(dimensions);
        this.age = age;
        this.xPos = xPos;
        this.yPos = yPos;
        // All people start healthy but with a change of having the disease based on this parameter
        this.hasDisease = hasDisease;

        // If this person is starting with the disease, then they also start as sick
        if(hasDisease)
            isHealthy = false;
        spreadRate = 1;
    }

    /** Copy constructor except for a different position
     *
     * @param testSubject
     * @param xPos
     * @param yPos
     */
    public Person(Person testSubject, int xPos, int yPos)
    {
        this.dimensions = new BoardDimensions(testSubject.dimensions);
        age = testSubject.age;
        xPos = xPos;
        yPos = yPos;
        hasDisease = testSubject.hasDisease;
        directionAngle = testSubject.directionAngle;
        isHealthy = testSubject.isHealthy;
        changeOfDirectionChange = testSubject.changeOfDirectionChange;
    }

    /**Copy Constructor
     *
     * @param testSubject
     */
    public Person(Person testSubject)
    {
        this(testSubject, testSubject.xPos, testSubject.yPos);
    }

    public boolean getHasDisease()
    {
        return hasDisease;
    }

    public boolean getIsHealthy()
    {
        return isHealthy;
    }

    /**
     *
     * @param other
     * @return
     */
    public double updateSpreadRate(Person other)
    {
        double distanceFromOther = Math.sqrt(Math.pow((xPos - other.xPos), 2) + Math.pow((yPos - other.yPos), 2));
        return spreadRate /= distanceFromOther;
    }

    /**
     *
     */
    public void move()
    {
        if(Math.random() >= .5)
            directionAngle = (int)(360*Math.random());
        if(Math.abs(xPos - dimensions.xLen) < 10 || Math.abs(xPos - dimensions.xOrigin) < 10 || Math.abs(yPos - dimensions.yLen) < 10 || Math.abs(yPos - dimensions.yOrigin) < 10)
            directionAngle *= -1;

        xPos += (int)(10*Math.cos(directionAngle));
        yPos += (int)(10*Math.sin(directionAngle));
    }

    public void checkSick()
    {
        if(!hasDisease && spreadRate * Math.random() > 5)
        {
            hasDisease = true;
            isHealthy = false;
        }
        if(hasDisease)
        {
            recoverTime++;
            if(recoverTime * Math.random() > 1000)
            {
                if((int)(2*Math.random()) >0)
                    isHealthy = true;
                else hasDisease = false;
            }
        }
    }

    public void draw(Graphics g)
    {
        //maybe have Color be a field and its own method to update color
        Color color;
        if(hasDisease && !isHealthy)
            color = Color.RED;
        else if(!hasDisease && isHealthy)
        {
            color = Color.BLUE;
        }
        else if(hasDisease && isHealthy)
        {
            color = Color.GRAY;
        }

        g.fillOval(xPos + circleRad,yPos + circleRad, circleRad, circleRad);
    }
}
