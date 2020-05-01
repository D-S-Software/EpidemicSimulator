package backend.simboard;

import backend.Person;
import backend.disease.Disease;

import java.awt.*;
import java.util.ArrayList;

public abstract class SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, q5Dimens, q6Dimens, q7Dimens, q8Dimens, travelDimens;
    private ArrayList<Rectangle> dimensList;

    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListQ5, pListQ6, pListQ7, pListQ8, pListTravel;
    private ArrayList<ArrayList<Person>> listPList; //may contain anywhere from just pList to all of the pLists depending on mono, quad or octo

    private Disease disease;

    private double socialDistanceChance, asymptomaticChance;
    private int numPeople, socialDistanceValue, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelers;

    /** Creates a backend.simboard.backend.simboard to simulate the actions of people that are displayed on gui.gui.SimBoardPanel
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
     */
    public SimBoard(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                    int minPreExistingConditions, int maxPreExistingConditions, double travelersPer)
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

        dimensList = new ArrayList<>();
        constructDimensList();
        updateDimensList(dimens);


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
     * @param updatedRect The current rectangle for the bounds of the backend.simboard.backend.simboard Panel
     */
    public abstract void updateDimensList(Rectangle updatedRect);

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
        for(int i = 0; i < pListQN.size(); i++)
            pList.add(pListQN.get(i));
    }

    /**
     * Constructs each backend.Person and adds them to an ArrayList in the listPlist ArrayList
     */
    public void constructPListNonTravel()
    {
        int i = 0;
        while(i < numPeople - travelers)
        {
            for (int j = 1; j < listPList.size() - 1; j++)
            {
                listPList.get(j).add(constructPerson(dimensList.get(j), j)); //each pList in the listPList has a corresponding dimens object in dimensList
                i++;
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

    /**Constructs the backend.Person object (Helper method for the constructPList methods)
     *
     * @param dimensQN The rectangle object that represents the dimensions of the current person's range
     * @param quadLocation The "home" location of the person to return to after quarantine
     * @return The newly constructed backend.Person
     */
    public Person constructPerson(Rectangle dimensQN, int quadLocation)
    {
        boolean asymptomatic = (Math.random() < asymptomaticChance);
        boolean isSocialDistancing = (Math.random() < socialDistanceChance);
        //MAYBE make generate traits method
        int personalAge = (int) (minAge + (maxAge - minAge) * Math.random());
        int personalConditions = (int) (minPreExistingConditions + (maxPreExistingConditions - maxPreExistingConditions) * Math.random());


        Person p = new Person(personalAge, personalConditions, generateXCoord(dimensQN), generateYCoord(dimensQN), dimensQN, disease, asymptomatic, isSocialDistancing);
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
     * and drawing the person objects on the gui.gui.SimBoardPanel
     * @param g2D Grapgics object from gui.gui.SimBoardPanel used to draw the peron objects
     */
    public void updateBoard(Graphics2D g2D)
    {
        this.updateDistanceFromSick();

        this.updateListPList();

        if(this instanceof Quarantinable)
        {
            ((Quarantinable) this).quarantineCheck();
            ((Quarantinable) this).drawQuarLine(g2D);
        }

        this.drawListPList(g2D);

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

    /**
     * Helper method for updateDistanceFromSick method
     */
    public void updateDistanceFromSickIteration(ArrayList<Person> pListQN)
    {
        for(int i = 0; i < pListQN.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if(!pListQN.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pListQN.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQN.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                        {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if(dist.equals(0.0))
                minDist = 0.1;
            pListQN.get(i).setDistanceFromSick(minDist);
            pListQN.get(i).setClosestSickIndex(closestSickIndex);
        }
    }

    /**
     * Updates the ArrayList of people and checks for sickness and removed them when dead
     */
    public void updateListPList()
    {
        for(int i = 1; i < getListPList().size(); i++)
        {
            updatePList(getListPList().get(i));
        }
    }

    /**
     * Helper method for the updateListPlist method
     */
    public void updatePList(ArrayList<Person> pListQN)
    {
        for(int i = 0; i < pListQN.size(); i++)
        {
            boolean isHealthy = pListQN.get(i).getIsHealthy();
            pListQN.get(i).checkCondition();
            if(!pListQN.get(i).getIsHealthy() && isHealthy) //Checks if a person becomes sick
                pList.get(pListQN.get(i).getClosestSickIndex()).addOthersInfected(); //Counts how many person someone infects
            pListQN.get(i).move();
            if(!pListQN.get(i).getHasDisease() && !pListQN.get(i).getIsHealthy())
            {
                pListQN.remove(pListQN.get(i));
                setNumPeople(getNumPeople() -1);
            }
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
        for(int j = 0; j < pList.size(); j++)
        {
            if(!pList.get(j).isIsoSick())
            {
                pList.get(j).updateDimens(dimensList.get(dimensNum));
            }
            pList.get(j).draw(g2D);
        }
    }

    /**
     * Checks if each person who is social distancing is the minimum distance apart. If not, it chooses a random direction to go in to separate as much as possible
     */
    private void socialDistanceUpdate()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            for(int j = 0; j < pList.size(); j++)
            {
                if(i != j && pList.get(i).isSocialDistancing() && Math.sqrt(Math.pow(pList.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pList.get(i).getYPos() - pList.get(j).getYPos(), 2)) < getSocialDistanceValue())
                    pList.get(i).changeDirectionAngle();
            }
        }
    }

    /** Switches between making each person (that is told to social distance) actively social distance and having freedom to move
     *
     * @param SocialDist Boolean value used to switch social distancing on and off
     */
    public void toggleSocDist(boolean SocialDist)
    {
        if(!SocialDist)
        {
            for(int i = 0; i < pList.size(); i++)
            {
                pList.get(i).setIsSocialDistancing(false);
            }
        }
        else
        {
            for(int i = 0; i < pList.size(); i++)
            {
                pList.get(i).setIsSocialDistancing(pList.get(i).getIsSocialDistancingSaved());
            }
        }
    }

    /**
     * Used to make every person on the simboard social distance
     */
    public void everyoneSocialDistance()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).setIsSocialDistancing(true);
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

    public int getNumPeople() {
        return numPeople;}

    public int getSocialDistanceValue() {
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
}
