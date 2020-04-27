import java.awt.*;
import java.util.ArrayList;

public class SimBoardOcto extends SimBoard{

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, q5Dimens, q6Dimens, q7Dimens, q8Dimens, travelDimens;
    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListQ5, pListQ6, pListQ7, pListQ8, pListTravel;
    private int numPeople;
    private boolean asymptomatic, isSocialDistancing;
    private int socialDistanceValue;
    private int Xshift = 30, Yshift = 10;

    public SimBoardOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer)
    {
        this.dimens = dimens;
        this.socialDistanceValue = socialDistanceValue;
        this.travelDimens = dimens;
        this.numPeople = numPeople;
        updateAllDimens(dimens);

        pList = new ArrayList<>();
        pListQ1 = new ArrayList<>();
        pListQ2 = new ArrayList<>();
        pListQ3 = new ArrayList<>();
        pListQ4 = new ArrayList<>();
        pListQ5 = new ArrayList<>();
        pListQ6 = new ArrayList<>();
        pListQ7 = new ArrayList<>();
        pListQ8 = new ArrayList<>();
        pListTravel = new ArrayList<>();

        int travelers = (int)(numPeople*travelersPer);

        int k = 0;
        for(int i = 0; i < numPeople - travelers; i++) {

            if(Math.random() < asymptomaticChance)
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < socialDistanceChance)
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

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

            int xPos5 = q5Dimens.x + (int) (q5Dimens.width * Math.random());
            int yPos5 = q5Dimens.y + (int) (q5Dimens.height * Math.random());

            int xPos6 = q6Dimens.x + (int) (q6Dimens.width * Math.random());
            int yPos6 = q6Dimens.y + (int) (q6Dimens.height * Math.random());

            int xPos7 = q7Dimens.x + (int) (q7Dimens.width * Math.random());
            int yPos7 = q7Dimens.y + (int) (q7Dimens.height * Math.random());

            int xPos8 = q8Dimens.x + (int) (q8Dimens.width * Math.random());
            int yPos8 = q8Dimens.y + (int) (q8Dimens.height * Math.random());


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, dimens, disease, circleRad, asymptomatic, isSocialDistancing);

            k++;
            if(k == 1)
            {
                person.setDimens(q1Dimens);
                person.setxPos(xPos1);
                person.setyPos(yPos1);
                person.setQuadLocation(1);
                pListQ1.add(person);
            }
            if(k == 2)
            {
                person.setDimens(q2Dimens);
                person.setxPos(xPos2);
                person.setyPos(yPos2);
                person.setQuadLocation(2);
                pListQ2.add(person);
            }
            if(k == 3)
            {
                person.setDimens(q3Dimens);
                person.setxPos(xPos3);
                person.setyPos(yPos3);
                person.setQuadLocation(3);
                pListQ3.add(person);
            }
            if(k == 4)
            {
                person.setDimens(q4Dimens);
                person.setxPos(xPos4);
                person.setyPos(yPos4);
                person.setQuadLocation(4);
                pListQ4.add(person);
            }
            if(k == 5)
            {
                person.setDimens(q5Dimens);
                person.setxPos(xPos5);
                person.setyPos(yPos5);
                person.setQuadLocation(5);
                pListQ5.add(person);
            }
            if(k == 6)
            {
                person.setDimens(q6Dimens);
                person.setxPos(xPos6);
                person.setyPos(yPos6);
                person.setQuadLocation(6);
                pListQ6.add(person);
            }
            if(k == 7)
            {
                person.setDimens(q7Dimens);
                person.setxPos(xPos7);
                person.setyPos(yPos7);
                person.setQuadLocation(7);
                pListQ7.add(person);
            }
            if(k == 8)
            {
                person.setDimens(q8Dimens);
                person.setxPos(xPos8);
                person.setyPos(yPos8);
                person.setQuadLocation(8);
                pListQ8.add(person);
                k = 0;
            }
        }

        for(int i = 0; i < travelers; i++)
        {
            if(Math.random() < asymptomaticChance)
                asymptomatic = true;
            else
                asymptomatic = false;

            if(Math.random() < socialDistanceChance)
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            int personalAge = (int) (minAge + (maxAge - minAge) * Math.random());
            int personalConditions = (int) (minPreExistingConditions + (maxPreExistingConditions - minPreExistingConditions) * Math.random());
            int xPos = dimens.x + (int)(dimens.width*Math.random());
            int yPos = dimens.y + (int)(dimens.height*Math.random());

            pListTravel.add(new Person(personalAge, personalConditions, xPos, yPos, travelDimens, disease, circleRad, asymptomatic, isSocialDistancing));
        }

        for(int i = 0; i < pListQ1.size(); i++)
            pList.add(pListQ1.get(i));
        for(int i = 0; i < pListQ2.size(); i++)
            pList.add(pListQ2.get(i));
        for(int i = 0; i < pListQ3.size(); i++)
            pList.add(pListQ3.get(i));
        for(int i = 0; i < pListQ4.size(); i++)
            pList.add(pListQ4.get(i));
        for(int i = 0; i < pListQ5.size(); i++)
            pList.add(pListQ5.get(i));
        for(int i = 0; i < pListQ6.size(); i++)
            pList.add(pListQ6.get(i));
        for(int i = 0; i < pListQ7.size(); i++)
            pList.add(pListQ7.get(i));
        for(int i = 0; i < pListQ8.size(); i++)
            pList.add(pListQ8.get(i));
        for(int i = 0; i < pListTravel.size(); i++)
            pList.add(pListTravel.get(i));
    }

    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;

        int width = (dimens.width / 4) - Xshift;
        int height = (dimens.height / 2) - Yshift;

        int q1xStart = dimens.x;
        int q1yStart = dimens.y;
        int q2xStart = dimens.x + width + Xshift;
        int q2yStart = dimens.y;
        int q3xStart = dimens.x + 2*width + 2*Xshift;
        int q3yStart = dimens.y;
        int q4xStart = dimens.x + 3*width + 3*Xshift;
        int q4yStart = dimens.y;
        int q5xStart = q1xStart;
        int q5yStart = height + 2*Yshift;
        int q6xStart = q2xStart;
        int q6yStart = height + 2*Yshift;
        int q7xStart = q3xStart;
        int q7yStart = height + 2*Yshift;
        int q8xStart = q4xStart;
        int q8yStart = height + 2*Yshift;


        q1Dimens = new Rectangle(q1xStart, q1yStart, width, height);
        q2Dimens = new Rectangle(q2xStart, q2yStart, width, height);
        q3Dimens = new Rectangle(q3xStart, q3yStart, width, height);
        q4Dimens = new Rectangle(q4xStart, q4yStart, width, height);
        q5Dimens = new Rectangle(q5xStart, q5yStart, width, height);
        q6Dimens = new Rectangle(q6xStart, q6yStart, width, height);
        q7Dimens = new Rectangle(q7xStart, q7yStart, width, height);
        q8Dimens = new Rectangle(q8xStart, q8yStart, width, height);
        travelDimens = dimens;
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
            int closestSickIndex = 0;

            if(!pListQ1.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ1.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ1.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ1.get(i).setDistanceFromSick(minDist);
            pListQ1.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ2.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ2.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ2.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ2.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ2.get(i).setDistanceFromSick(minDist);
            pListQ2.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ3.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ3.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ3.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ3.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ3.get(i).setDistanceFromSick(minDist);
            pListQ3.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ4.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ4.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ4.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ4.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ4.get(i).setDistanceFromSick(minDist);
            pListQ4.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ5.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ5.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ5.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ5.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ5.get(i).setDistanceFromSick(minDist);
            pListQ5.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ6.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ6.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ6.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ6.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ6.get(i).setDistanceFromSick(minDist);
            pListQ6.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ7.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ7.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ7.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ7.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ7.get(i).setDistanceFromSick(minDist);
            pListQ7.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListQ8.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQ8.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQ8.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ8.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListQ8.get(i).setDistanceFromSick(minDist);
            pListQ8.get(i).setClosestSickIndex(closestSickIndex);
        }
        for(int i = 0; i < pListTravel.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListTravel.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListTravel.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListTravel.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pListTravel.get(i).setDistanceFromSick(minDist);
            pListTravel.get(i).setClosestSickIndex(closestSickIndex);
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updatePList()
    {
        for(int i = 0; i < pListQ1.size(); i++)
        {
            boolean isHealthy = pListQ1.get(i).getIsHealthy();
            pListQ1.get(i).checkCondition();
            if(!pListQ1.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ1.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ1.get(i).move();
            if(!pListQ1.get(i).getHasDisease() && !pListQ1.get(i).getIsHealthy())
            {
                pListQ1.remove(pListQ1.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ2.size(); i++)
        {
            boolean isHealthy = pListQ2.get(i).getIsHealthy();
            pListQ2.get(i).checkCondition();
            if(!pListQ2.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ2.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ2.get(i).move();
            if(!pListQ2.get(i).getHasDisease() && !pListQ2.get(i).getIsHealthy())
            {
                pListQ2.remove(pListQ2.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ3.size(); i++)
        {
            boolean isHealthy = pListQ3.get(i).getIsHealthy();
            pListQ3.get(i).checkCondition();
            if(!pListQ3.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ3.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ3.get(i).move();
            if(!pListQ3.get(i).getHasDisease() && !pListQ3.get(i).getIsHealthy())
            {
                pListQ3.remove(pListQ3.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ4.size(); i++)
        {
            boolean isHealthy = pListQ4.get(i).getIsHealthy();
            pListQ4.get(i).checkCondition();
            if(!pListQ4.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ4.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ4.get(i).move();
            if(!pListQ4.get(i).getHasDisease() && !pListQ4.get(i).getIsHealthy())
            {
                pListQ4.remove(pListQ4.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ5.size(); i++)
        {
            boolean isHealthy = pListQ5.get(i).getIsHealthy();
            pListQ5.get(i).checkCondition();
            if(!pListQ5.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ5.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ5.get(i).move();
            if(!pListQ5.get(i).getHasDisease() && !pListQ5.get(i).getIsHealthy())
            {
                pListQ5.remove(pListQ5.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ6.size(); i++)
        {
            boolean isHealthy = pListQ6.get(i).getIsHealthy();
            pListQ6.get(i).checkCondition();
            if(!pListQ6.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ6.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ6.get(i).move();
            if(!pListQ6.get(i).getHasDisease() && !pListQ6.get(i).getIsHealthy())
            {
                pListQ6.remove(pListQ6.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ7.size(); i++)
        {
            boolean isHealthy = pListQ7.get(i).getIsHealthy();
            pListQ7.get(i).checkCondition();
            if(!pListQ7.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ7.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ7.get(i).move();
            if(!pListQ7.get(i).getHasDisease() && !pListQ7.get(i).getIsHealthy())
            {
                pListQ7.remove(pListQ7.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListQ8.size(); i++)
        {
            boolean isHealthy = pListQ8.get(i).getIsHealthy();
            pListQ8.get(i).checkCondition();
            if(!pListQ8.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQ8.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQ8.get(i).move();
            if(!pListQ8.get(i).getHasDisease() && !pListQ8.get(i).getIsHealthy())
            {
                pListQ8.remove(pListQ8.get(i));
                numPeople--;
            }
        }
        for(int i = 0; i < pListTravel.size(); i++)
        {
            boolean isHealthy = pListTravel.get(i).getIsHealthy();
            pListTravel.get(i).checkCondition();
            if(!pListTravel.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListTravel.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListTravel.get(i).move();
            if(!pListTravel.get(i).getHasDisease() && !pListTravel.get(i).getIsHealthy())
            {
                pListTravel.remove(pListTravel.get(i));
                numPeople--;
            }
        }
    }

    public ArrayList<Person> getPList() {
        return pList;
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

    public ArrayList<Person> getpListQ5() {
        return pListQ5;
    }

    public ArrayList<Person> getpListQ6() {
        return pListQ6;
    }

    public ArrayList<Person> getpListQ7() {
        return pListQ7;
    }

    public ArrayList<Person> getpListQ8() {
        return pListQ8;
    }

    public ArrayList<Person> getpListTravel() {
        return pListTravel;
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

    public Rectangle getQ5Dimens() {
        return q5Dimens;
    }

    public Rectangle getQ6Dimens() {
        return q6Dimens;
    }

    public Rectangle getQ7Dimens() {
        return q7Dimens;
    }

    public Rectangle getQ8Dimens() {
        return q8Dimens;
    }

    public Rectangle getTravelDimens() {
        return travelDimens;
    }

    public void setQ1Dimens(Rectangle rect) {
        q1Dimens = rect;
    }

    public void setQ2Dimens(Rectangle rect) {
        q2Dimens = rect;
    }

    public void setQ3Dimens(Rectangle rect) {
        q3Dimens = rect;
    }

    public void setQ4Dimens(Rectangle rect) {
        q4Dimens = rect;
    }

    public void setQ5Dimens(Rectangle rect) {
        q5Dimens = rect;
    }

    public void setQ6Dimens(Rectangle rect) {
        q6Dimens = rect;
    }

    public void setQ7Dimens(Rectangle rect) {
        q7Dimens = rect;
    }

    public void setQ8Dimens(Rectangle rect) {
        q8Dimens = rect;
    }

    public void setTravelDimens(Rectangle rect) {
        travelDimens = rect;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public int getSocialDistanceValue()
    {
        return socialDistanceValue;
    }
}
