import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Person implements ActionListener {

    private boolean hasDisease, isHealthy;
    private int xPos, yPos, dx, dy, directionAngle, minTimeSick, maxTimeSick;
    private int circleRad = 8, timeSinceSick = 0, step = 2;
    private double distanceFromSick, mortalityRate;
    private Dimensions dimens;
    private Disease disease;


    public Person(int age, boolean preExistingConditions, int xPos, int yPos, Dimensions dimens, Disease disease)
    {
        this.dimens = new Dimensions(dimens);
        this.disease = disease;
        this.xPos = xPos;
        this.yPos = yPos;
        minTimeSick = disease.getBaseMinTimeSick(); //TODO change based on person age and preExistingConditions
        maxTimeSick = disease.getBaseMaxTimeSick() ; //TODO change based on person age and preExistingConditions
        mortalityRate = disease.getBaseMortalityRate(); //TODO increase this to make the person more likely to die (0 --> 1) Use age and preExistingConditions
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
     *///TODO: Person objects should not phase through each other
    public void move()
    {
        if(Math.random() >= .95)
            directionAngle = (int)(360*Math.random());

        dx = (int)(step*Math.cos(directionAngle));
        dy = (int)(step*Math.sin(directionAngle));

        if(xPos + dx > dimens.getxOrigin() + dimens.getxLen() - circleRad)
        {
            xPos = dimens.getxOrigin() + dimens.getxLen() - circleRad - 1;
            directionAngle += 180;
        }
        if(xPos + dx < dimens.getxOrigin() + circleRad)
        {
            xPos = dimens.getxOrigin() + circleRad;
            directionAngle += 180;
        }
        else xPos += dx;

        if(yPos + dy > dimens.getyOrigin() + dimens.getyLen()  - circleRad)
        {
            yPos = dimens.getyOrigin() + dimens.getyLen() - circleRad - 1;
            directionAngle *= -1;
        }
        if(yPos + dy < dimens.getyOrigin() + circleRad)
        {
            yPos = dimens.getyOrigin() + circleRad;
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
            if(timeSinceSick >  minTimeSick + (int)((maxTimeSick - minTimeSick)*Math.random()))
            {
                if(Math.random() > mortalityRate)
                    isHealthy = true;    // Recovers
                else hasDisease = false; //Dies
            }
        }
    }

    public void updateDimens(Rectangle rect)
    {
        dimens.setxLen(rect.width);
        dimens.setyLen(rect.height);
        //TODO Remove when not needed for testing
        //System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
    }

    public void draw(Graphics2D g2D)
    {
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
