import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard{

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens;
    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4;
    private int minAge = 20;
    private int maxAge = 80;
    private int minPreExistingConditions = 0;
    private int maxPreExistingConditions = 3;
    private int numPeople;

    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople)
    {
        this.dimens = dimens;
        this.numPeople = numPeople;
        updateAllDimens(dimens);

        pList = new ArrayList<>();
        pListQ1 = new ArrayList<>();
        pListQ2 = new ArrayList<>();
        pListQ3 = new ArrayList<>();
        pListQ4 = new ArrayList<>();

        int k = 0;
        for(int i = 0; i < numPeople; i++) {
            int personalAge = (int) (minAge + (maxAge - minAge) * Math.random());
            int personalConditions = (int) (minPreExistingConditions + (maxPreExistingConditions - minPreExistingConditions) * Math.random());

            int xPos1 = q1Dimens.x + (int) (q1Dimens.width * Math.random());
            int yPos1 = q1Dimens.y + (int) (q1Dimens.height * Math.random());

            int xPos2 = q2Dimens.x + (int) (q2Dimens.width * Math.random());
            int yPos2 = q2Dimens.y + (int) (q2Dimens.height * Math.random());

            int xPos3 = q3Dimens.x + (int) (q3Dimens.width * Math.random());
            int yPos3 = q3Dimens.y + (int) (q3Dimens.height * Math.random());

            int xPos4 = q4Dimens.x + (int) (q4Dimens.width * Math.random());
            int yPos4 = q4Dimens.y + (int) (q4Dimens.height * Math.random());


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, dimens, disease, circleRad);



            k++;
            if(k == 1)
            {
                person.setDimens(q1Dimens);
                person.setxPos(xPos1);
                person.setyPos(yPos1);
                pListQ1.add(person);
            }
            if(k == 2)
            {
                person.setDimens(q2Dimens);
                person.setxPos(xPos2);
                person.setyPos(yPos2);
                pListQ2.add(person);
            }
            if(k == 3)
            {
                person.setDimens(q3Dimens);
                person.setxPos(xPos3);
                person.setyPos(yPos3);
                pListQ3.add(person);
            }
            if(k == 4)
            {
                person.setDimens(q4Dimens);
                person.setxPos(xPos4);
                person.setyPos(yPos4);
                pListQ4.add(person);
                k = 0;
            }
        }
    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;

        q1Dimens = new Rectangle(dimens);
        q2Dimens = new Rectangle(dimens);
        q3Dimens = new Rectangle(dimens);
        q4Dimens = new Rectangle(dimens);

        /**
        int width = (dimens.width - dimens.x) / 2 - circleRad;
        int height = (dimens.height - dimens.y) / 2 - circleRad;

        int newXStart = (dimens.width - dimens.x) / 2 + circleRad;
        int newYStart = (dimens.height - dimens.y) / 2 + circleRad;

        q1Dimens = new Rectangle(dimens.x, dimens.y, width, height);
        q2Dimens = new Rectangle(newXStart, dimens.y, width, height);
        q3Dimens = new Rectangle(dimens.x, newYStart, width, height);
        q4Dimens = new Rectangle(newXStart, newYStart, width, height);*/
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < pListQ1.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));

            if(!pListQ1.get(i).getHasDisease())
                for(int j = 0; j < pListQ1.size(); j++)
                    if(i != j && pListQ1.get(j).getHasDisease() && !pListQ1.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ1.get(i).getXPos() - pListQ1.get(j).getXPos(), 2) + Math.pow(pListQ1.get(i).getYPos() - pListQ1.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ1.get(i).setDistanceFromSick(minDist);
        }
        for(int i = 0; i < pListQ2.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));

            if(!pListQ2.get(i).getHasDisease())
                for(int j = 0; j < pListQ2.size(); j++)
                    if(i != j && pListQ2.get(j).getHasDisease() && !pListQ2.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ2.get(i).getXPos() - pListQ2.get(j).getXPos(), 2) + Math.pow(pListQ2.get(i).getYPos() - pListQ2.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ2.get(i).setDistanceFromSick(minDist);
        }
        for(int i = 0; i < pListQ3.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));

            if(!pListQ3.get(i).getHasDisease())
                for(int j = 0; j < pListQ3.size(); j++)
                    if(i != j && pListQ3.get(j).getHasDisease() && !pListQ3.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ3.get(i).getXPos() - pListQ3.get(j).getXPos(), 2) + Math.pow(pListQ3.get(i).getYPos() - pListQ3.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ3.get(i).setDistanceFromSick(minDist);
        }
        for(int i = 0; i < pListQ4.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));

            if(!pListQ4.get(i).getHasDisease())
                for(int j = 0; j < pListQ4.size(); j++)
                    if(i != j && pListQ4.get(j).getHasDisease() && !pListQ4.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ4.get(i).getXPos() - pListQ4.get(j).getXPos(), 2) + Math.pow(pListQ4.get(i).getYPos() - pListQ4.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ4.get(i).setDistanceFromSick(minDist);
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updatePerson()
    {
        for(int i = 0; i < pListQ1.size(); i++)
        {
            pListQ1.get(i).checkCondition();
            pListQ1.get(i).move();
            if(!pListQ1.get(i).getHasDisease() && !pListQ1.get(i).getIsHealthy())
            {
                pListQ1.remove(pListQ1.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ2.size(); i++)
        {
            pListQ2.get(i).checkCondition();
            pListQ2.get(i).move();
            if(!pListQ2.get(i).getHasDisease() && !pListQ2.get(i).getIsHealthy())
            {
                pListQ2.remove(pListQ2.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ3.size(); i++)
        {
            pListQ3.get(i).checkCondition();
            pListQ3.get(i).move();
            if(!pListQ3.get(i).getHasDisease() && !pListQ3.get(i).getIsHealthy())
            {
                pListQ3.remove(pListQ3.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ4.size(); i++)
        {
            pListQ4.get(i).checkCondition();
            pListQ4.get(i).move();
            if(!pListQ4.get(i).getHasDisease() && !pListQ4.get(i).getIsHealthy())
            {
                pListQ4.remove(pListQ4.get(i));
                numPeople--;
            }
        }
    }

    public ArrayList<Person> getpListQ1() {
        return pListQ1;
    }

    public ArrayList<Person> getpListQ2() {
        return pListQ2;
    }

    public ArrayList<Person> getpListQ3() {
        return pListQ3;
    }

    public ArrayList<Person> getpListQ4() {
        return pListQ4;
    }

    public Rectangle getDimens() {
        return dimens;
    }

    public Rectangle getQ1Dimens() {
        return q1Dimens;
    }

    public Rectangle getQ2Dimens() {
        return q2Dimens;
    }

    public Rectangle getQ3Dimens() {
        return q3Dimens;
    }

    public Rectangle getQ4Dimens() {
        return q4Dimens;
    }

    public int getNumPeople() {
        return numPeople;
    }
}
