package backend.simboard;

import backend.Person;
import backend.disease.Disease;

import java.awt.*;
import java.util.ArrayList;

public abstract class SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, q5Dimens, q6Dimens, q7Dimens, q8Dimens, travelDimens;
    private ArrayList<Rectangle> dimensList;
    private ArrayList<Integer> rNot;

    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListQ5, pListQ6, pListQ7, pListQ8, pListTravel;
    private ArrayList<ArrayList<Person>> listPList; //may contain anywhere from just pList to all of the pLists depending on mono, quad or octo

    private Disease disease;

    private double socialDistanceChance, asymptomaticChance, socialDistanceValue, minAge, maxAge, travelers, reinfectRate, quarantineChance, antiBodyTime, congestionValue = 1;
    private int numPeople, minPreExistingConditions, maxPreExistingConditions;

    /** Creates a SimBoard to simulate the actions of people that are displayed on SimBoardPanel
     *
     * @param disease The disease object used for a simulation
     * @param dimens The rectangle that represents the area a person can move in
     * @param numPeople The total number of people in the simulation
     * @param asymptomaticChance The percent of people who are asymptomatic
     * @param socialDistanceValue The number of pixels each person tries to stay apart by when social distancing is enabled
     * @param socialDistanceChance The percent of people who participate in social distancing
     * @param minAge The minimum age of a person in the simulation
     * @param maxAge The maximum age of a person in the simulation
     * @param minPreExistingConditions The minimum pre-existing conditions of a person in the simulation
     * @param maxPreExistingConditions The maximum pre-existing conditions of a person in the simulation
     * @param travelersPer The percent of people who travel without bound in a quad or octo board
     * @param reinfectRate The percentage of people that can be reinfected once they loose resistance
     * @param quarantineChance The percent chance that a person will quarantine when sick
     * @param antiBodyTime The time it takes for antibodies to expire
     */
    public SimBoard(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, double socialDistanceValue, double socialDistanceChance, double minAge, double maxAge,
                    int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, double reinfectRate, double quarantineChance, double antiBodyTime)
    {
        this.disease = disease;
        this.numPeople = numPeople;
        this.asymptomaticChance = asymptomaticChance;
        this.socialDistanceValue = socialDistanceValue;
        this.socialDistanceChance = socialDistanceChance;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minPreExistingConditions = minPreExistingConditions;
        this.maxPreExistingConditions = maxPreExistingConditions;
        this.travelers = (int)(numPeople*travelersPer);
        this.quarantineChance = quarantineChance;

        this.dimens = dimens;
        q1Dimens = new Rectangle();
        q2Dimens = new Rectangle();
        q3Dimens = new Rectangle();
        q4Dimens = new Rectangle();
        q5Dimens = new Rectangle();
        q6Dimens = new Rectangle();
        q7Dimens = new Rectangle();
        q8Dimens = new Rectangle();
        travelDimens = new Rectangle();

        rNot = new ArrayList<>();
        dimensList = new ArrayList<>();
        constructDimensList();
        updateDimensList(dimens);

        this.reinfectRate = reinfectRate;
        this.antiBodyTime = antiBodyTime;

        pList = new ArrayList<>();
        pListQ1 = new ArrayList<>();
        pListQ2 = new ArrayList<>();
        pListQ3 = new ArrayList<>();
        pListQ4 = new ArrayList<>();
        pListQ5 = new ArrayList<>();
        pListQ6 = new ArrayList<>();
        pListQ7 = new ArrayList<>();
        pListQ8 = new ArrayList<>();
        pListTravel= new ArrayList<>();

        listPList = new ArrayList<>();
        constructListPList();
        boolean hasInfected = false;

        // Makes sure that at least one person is infected in each simulation
        if(disease.getStartPercentHealthy()/100.0 < 1)
        {
            for(int i = 1; i < listPList.size(); i++)
                for(int j = 0; j < listPList.get(i).size(); j++)
                    if (!listPList.get(i).get(j).getIsHealthy()) {
                        hasInfected = true;
                        break;
                    }
            if(!hasInfected)
            {
                listPList.get(1).get(1).setHasDisease();
                listPList.get(1).get(1).setIsSick();
                listPList.get(1).get(1).setStartedSick(true);
            }
        }
    }

    /**
     * Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board
     */
    public abstract void constructDimensList();

    /**
     * Creates an ArrayList of ArrayLists of people to be used when calling update methods
     */
    public abstract void constructListPList();

    /**
     * Updates the dimensions of each rectangle each tick to keep each person in the correct zone
     * @param updatedRect The current rectangle for the bounds of the SimBoardPanel
     */
    public abstract void updateDimensList(Rectangle updatedRect);

    /**
     * Draws a line to separate each subsection
     * @param g2D graphics object used to draw the line
     */
    public abstract void drawSepLines(Graphics2D g2D);

    /**
     * Helper method used in the constructListPList method
     * @param ListPList The ArrayList of ArrayLists being constructed
     * @param startIndex The fist ArrayList of people to be added
     */
    public void constructPListTotal(ArrayList<ArrayList<Person>> ListPList, int startIndex)
    {
        for(int i = startIndex; i < ListPList.size(); i++)
            constructPListTotalIteration(ListPList.get(i));
    }

    /**
     * Helper method for constructPListTotal
     * @param pListQN The current ArrayList of people being created
     */
    private void constructPListTotalIteration(ArrayList<Person> pListQN)
    {
        pList.addAll(pListQN);
    }

    /**
     * Constructs each Person and adds them to an ArrayList in the listPlist ArrayList
     */
    public void constructPListNonTravel()
    {
        int i = 0;
        while(i < numPeople - travelers)
        {
            for (int j = 1; j < listPList.size() - 1; j++)
            {
                if(i < numPeople - travelers)
                {
                    listPList.get(j).add(constructPerson(dimensList.get(j), j)); //each pList in the listPList has a corresponding dimens object in dimensList
                    i++;
                }
            }
        }
    }

    /**
     * Constructs the ArrayList of people representing the travels (people without boundaries)
     */
    public void constructPListTravel()
    {
        for(int i = 0; i < travelers; i++)
        {
            pListTravel.add(constructPerson(dimensList.get(dimensList.size() - 1), dimensList.size() - 1));
        }
    }

    /**Constructs the Person object (Helper method for the constructPList methods)
     *
     * @param dimensQN The rectangle object that represents the dimensions of the current person's range
     * @param quadLocation The "home" location of the person to return to after quarantine
     * @return The newly constructed Person
     */
    public Person constructPerson(Rectangle dimensQN, int quadLocation)
    {
        boolean asymptomatic = (Math.random() < asymptomaticChance);
        boolean isSocialDistancing = (Math.random() < socialDistanceChance);
        //MAYBE make generate traits method
        int personalAge = (int) (minAge + (maxAge - minAge) * Math.random());
        int personalConditions = (int) (minPreExistingConditions + (maxPreExistingConditions - minPreExistingConditions) * Math.random());

        boolean canReinfect;
        canReinfect = Math.random() < reinfectRate;

        Person p = new Person(personalAge, personalConditions, generateXCoord(dimensQN), generateYCoord(dimensQN), dimensQN, disease, asymptomatic, isSocialDistancing, antiBodyTime, canReinfect, quarantineChance);
        p.setQuadLocation(quadLocation);
        return p;
    }

    /**Creates a random X-Coord for a person in their respective Dimension
     *
     * @param dimens The dimensions of the person
     * @return The random X-Coord
     */
    private int generateXCoord(Rectangle dimens)
    {
        return dimens.x + (int)(dimens.width * Math.random());
    }

    /**Creates a random Y-Coord for a person in their respective Dimension
     *
     * @param dimens The dimensions of the person
     * @return The random Y-Coord
     */
    private int generateYCoord(Rectangle dimens)
    {
        return dimens.y + (int)(dimens.height * Math.random());
    }

    /** Updates the simboard by checking the distance from sick for each peron, updating each person list, checking quarantine and social distance
     * and drawing the person objects on the SimBoardPanel
     * @param g2D Grapgics object from SimBoardPanel used to draw the peron objects
     */
    public void updateBoard(Graphics2D g2D)
    {
        updateDistanceFromSick();

        updateListPList();

        updateRNot();

        if(this instanceof Quarantinable)
        {
            ((Quarantinable) this).quarantineCheck();
            ((Quarantinable) this).drawQuarLine(g2D);
        }

        if(this instanceof SimBoardQuad || this instanceof SimBoardOcto)
            this.drawSepLines(g2D);

        drawListPList(g2D);

        socialDistanceUpdate();
    }

    /**
     * Calculates the minimum distance a healthy person is from a sick person
     */
    public void updateDistanceFromSick()
    {
        for(int i = 1; i < getListPList().size(); i++)
            updateDistanceFromSickIteration(getListPList().get(i));
    }

    /**Helper method for updateDistanceFromSick method
     *
     * @param pListQN The ArrayList of People to update distance from sick for
     */
    public void updateDistanceFromSickIteration(ArrayList<Person> pListQN)
    {
        for (Person person : pListQN) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (person.getIsHealthy())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(person.getXPos() - pList.get(j).getXPos(), 2) + Math.pow(person.getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0.0))
                minDist = 0.1;
            person.setDistanceFromSick(minDist);
            person.setClosestSickIndex(closestSickIndex);
        }
    }

    /**
     * Updates the ArrayList of people and checks for sickness and removed them when dead. Also controls the
     * moving to centers feature and determines who is the closest sick person to each person (needed for rNot calculation)
     */
    public void updateListPList()
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            updatePList(getListPList().get(i));
        }
    }

    /**Helper method for the updateListPlist method
     *
     * @param pListQN The ArrayList of People to update
     */
    public void updatePList(ArrayList<Person> pListQN)
    {
        for(int i = 0; i < pListQN.size(); i++)
        {
            boolean isHealthy = (pListQN.get(i).getIsHealthy() && !pListQN.get(i).getHasDisease());
            pListQN.get(i).checkCondition();
            if(!pListQN.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                if(!pList.get(pListQN.get(i).getClosestSickIndex()).isHasRecoveredOnce()) //Checks if the person who spread the disease has not already recovered once
                    pList.get(pListQN.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            if(Math.random() > congestionValue) //Change at any given tick a person goes to the center
                pListQN.get(i).setTarget((pListQN.get(i).getDimens().x) + (pListQN.get(i).getDimens().width/2), (pListQN.get(i).getDimens().y) + (pListQN.get(i).getDimens().height/2));
            else if(congestionValue == 1) // If toggleCenters is turned off then all people lose their target
                pListQN.get(i).removeTarget();
            pListQN.get(i).move();
            if(!pListQN.get(i).getHasDisease() && !pListQN.get(i).getIsHealthy())
            {
                pListQN.remove(pListQN.get(i));
                setNumPeople(numPeople -1);
            }
        }
    }

    /**
     * Calculates the total number of people each person (who was an original case) infects when sick
     */
    private void updateRNot()
    {
        rNot = new ArrayList<>();
        for (Person person : pList)
            if (person.hasStartedSick()) {
                rNot.add(person.getOthersInfected());
            }
    }

    /** Draws each person on the simboard
     *
     * @param g2D Graphics object used to draw each person
     */
    public void drawListPList(Graphics2D g2D)
    {
        for(int i = 1; i < listPList.size(); i++)
        {
            drawPList(listPList.get(i), i, g2D);
        }
    }

    /** Helper method for the drawListPList method
     *
     * @param pList The current ArrayList of people being drawn
     * @param dimensNum The dimension of the people in the current ArrayList
     * @param g2D The graphics object being used to draw the people
     */
    public void drawPList(ArrayList<Person> pList, int dimensNum, Graphics2D g2D)
    {
        for (Person person : pList) {
            if (person.isIsoSick()) {
                person.updateDimens(dimensList.get(dimensNum));
            }
            person.draw(g2D);
        }
    }

    /**
     * Checks if each person who is social distancing is the minimum distance apart. If not, it finds a direction to separate as much as possible
     */
    private void socialDistanceUpdate()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            for(int j = 0; j < pList.size(); j++)
            {
                double xDiff = pList.get(i).getXPos() - pList.get(j).getXPos();
                double yDiff = pList.get(i).getYPos() - pList.get(j).getYPos();

                if(i != j && pList.get(i).isSocialDistancing() && Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) < getSocialDistanceValue())
                {
                    if(xDiff == 0)
                    {
                        if(pList.get(i).getYPos() > pList.get(j).getYPos())
                            changeDirHelper(i, j, Math.PI/2., -(Math.PI/2.));
                        else
                            changeDirHelper(i, j, -(Math.PI/2.), Math.PI/2.);
                    }
                    else if(yDiff == 0)
                    {
                        if(pList.get(i).getXPos() > pList.get(j).getXPos())
                            changeDirHelper(i, j, 0, -Math.PI);
                        else
                            changeDirHelper(i, j, -Math.PI, 0);
                    }
                    else if(xDiff > 5 && yDiff > 5)
                    {
                        changeDirHelper(i, j, 0, -Math.PI);
                        pList.get(i).resetTimeSinceDirChange();
                        pList.get(j).resetTimeSinceDirChange();
                    }
                    else
                    {
                        double angle = Math.atan(yDiff/xDiff);

                        if(pList.get(i).getXPos() > pList.get(j).getXPos())
                            changeDirHelper(i, j, angle, angle+Math.PI);
                        else
                            changeDirHelper(i, j, angle+Math.PI, angle);
                    }
                }
                pList.get(i).addTimeSinceDirChange();
                pList.get(j).addTimeSinceDirChange();
            }
        }
    }

    /** Helper method for socialDistanceUpdate()
     *
     * @param i First person being checked
     * @param j Second person being checked
     * @param angleOne Target angle for person one
     * @param angleTwo Target angle for person two
     */
    private void changeDirHelper(int i, int j, double angleOne, double angleTwo)
    {
        if(pList.get(i).getTimeSinceDirChange() > 500)
            pList.get(i).changeDirectionAngle(angleOne);
        if(pList.get(j).getTimeSinceDirChange() > 500)
            pList.get(j).changeDirectionAngle(angleTwo);
    }

    /** Switches between making each person (that is told to social distance) actively social distance and having freedom to move
     *
     * @param SocialDist Boolean value used to switch social distancing on and off
     */
    public void toggleSocDist(boolean SocialDist)
    {
        if(!SocialDist)
        {
            for (Person person : pList) {
                person.setIsSocialDistancing(false);
                if (person.getHasTarget()) //Replaces the target cords for people who had a target
                {
                    int[] tarCords = person.getTarget();
                    person.setTarget(tarCords[0], tarCords[1]);
                }
            }
        }
        else
            for (Person person : pList)
                person.setIsSocialDistancing(person.getIsSocialDistancingSaved());
    }

    /**
     * Switches between having people move to the center and purely random motion
     * @param movingToTarget Boolean for if people should move to center
     */
    public void toggleMovingTarget(boolean movingToTarget)
    {
        if(!movingToTarget)
            congestionValue = 1;
        else
            congestionValue = .9975;
    }

    /**
     * Used to make every person on the simboard social distance
     */
    public void everyoneSocialDistance()
    {
        for (Person person : pList) {
            person.setIsSocialDistancing(true);
        }
    }

    /** Getter and Setter Methods*/

    public ArrayList<Person> getPList() {
        return pList;
    }

    public ArrayList<Person> getPListQ1() {
        return pListQ1;
    }

    public ArrayList<Person> getPListQ2() {
        return pListQ2;
    }

    public ArrayList<Person> getPListQ3() {
        return pListQ3;
    }

    public ArrayList<Person> getPListQ4() {
        return pListQ4;
    }

    public ArrayList<Person> getPListQ5() {
        return pListQ5;
    }

    public ArrayList<Person> getPListQ6() {
        return pListQ6;
    }

    public ArrayList<Person> getPListQ7() {
        return pListQ7;
    }

    public ArrayList<Person> getPListQ8() {
        return pListQ8;
    }

    public ArrayList<Person> getPListTravel() {
        return pListTravel;
    }

    public ArrayList<ArrayList<Person>> getListPList() {
        return listPList;
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

    public ArrayList<Rectangle> getDimensList() {
        return dimensList;
    }

    public ArrayList<Integer> getRNot() {
        return rNot;
    }

    public int getNumPeople() {
        return numPeople;}

    public double getSocialDistanceValue() {
        return socialDistanceValue;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public void setDimens(Rectangle dimens) {
        this.dimens = dimens;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public double getAntibodyTime(){ return antiBodyTime; }
}
