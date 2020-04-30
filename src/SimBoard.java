import java.awt.*;
import java.util.ArrayList;

public abstract class SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, q5Dimens, q6Dimens, q7Dimens, q8Dimens, travelDimens;
    private ArrayList<Rectangle> dimensList;

    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListQ5, pListQ6, pListQ7, pListQ8, pListTravel;
    private ArrayList<ArrayList<Person>> listPList; //may contain anywhere from just pList to all of the pLists depending on mono, quad or octo

    private Disease disease;

    private double socialDistanceChance, asymptomaticChance;
    private int numPeople, socialDistanceValue, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions;
    public final int circleRad = 8;


    public SimBoard(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance, int minAge, int maxAge,
                    int minPreExistingConditions, int maxPreExistingConditions)
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
        updateAllDimens(dimens);
        constructDimensList();

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

    public abstract void constructDimensList();

    public abstract void constructListPList();

    public abstract void updateListPList();

    public abstract void updateDistanceFromSick();

    public abstract void updateAllDimens(Rectangle updatedRect);


    public void drawPList(ArrayList<Person> pList, int dimensNum, Graphics2D g2D) //maybe make private
    {
        for(int j = 0; j < pList.size(); j++)
        {
            if(pList.get(j).isIsoSick() == false)
                pList.get(j).updateDimens(dimensList.get(dimensNum));
            pList.get(j).draw(g2D);
        }
    }

    public void drawListPList(Graphics2D g2D)
    {
        for(int i = 1; i < listPList.size(); i++)
        {
            drawPList(listPList.get(i), i, g2D);
        }
    }

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
    public void everyoneSocialDistance()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).setIsSocialDistancing(true);
        }
    }

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
            if(dist.equals(0))
                minDist = 0.1;
            pListQN.get(i).setDistanceFromSick(minDist);
            pListQN.get(i).setClosestSickIndex(closestSickIndex);
        }
    }

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

    public ArrayList<Person> getPList()
    {
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

    public void setListPList(ArrayList<ArrayList<Person>> listPLists) {
        this.listPList = listPLists;
    }

    public Rectangle getDimens()
    {
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

    public void setTravelDimens(Rectangle travelDimens) {
        this.travelDimens = travelDimens;
    }

    public ArrayList<Rectangle> getDimensList() {
        return dimensList;
    }

    public void setDimensList(ArrayList<Rectangle> dimensList) {
        this.dimensList = dimensList;
    }

    public int getNumPeople(){ return numPeople;}

    public int getSocialDistanceValue() {
        return socialDistanceValue;
    }

    public void setSocialDistanceValue(int socialDitanceValue) {
        this.socialDistanceValue = socialDitanceValue;
    }

    public double getSocialDistanceChance() {
        return socialDistanceChance;
    }

    public void setSocialDistanceChance(double socialDistanceChance) {
        this.socialDistanceChance = socialDistanceChance;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public double getAsymptomaticChance() {
        return asymptomaticChance;
    }

    public void setAsymptomaticChance(double asymptomaticChance) {
        this.asymptomaticChance = asymptomaticChance;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinPreExistingConditions() {
        return minPreExistingConditions;
    }

    public void setMinPreExistingConditions(int minPreExistingConditions) {
        this.minPreExistingConditions = minPreExistingConditions;
    }

    public int getMaxPreExistingConditions() {
        return maxPreExistingConditions;
    }

    public void setMaxPreExistingConditions(int maxPreExistingConditions) {
        this.maxPreExistingConditions = maxPreExistingConditions;
    }

    public void setDimens(Rectangle dimens) {
        this.dimens = dimens;
    }

    public void setQ1Dimens(Rectangle q1Dimens) {
        this.q1Dimens = q1Dimens;
    }

    public void setQ2Dimens(Rectangle q2Dimens) {
        this.q2Dimens = q2Dimens;
    }

    public void setQ3Dimens(Rectangle q3Dimens) {
        this.q3Dimens = q3Dimens;
    }

    public void setQ4Dimens(Rectangle q4Dimens) {
        this.q4Dimens = q4Dimens;
    }

    public void setQ5Dimens(Rectangle q5Dimens) {
        this.q5Dimens = q5Dimens;
    }

    public void setQ6Dimens(Rectangle q6Dimens) {
        this.q6Dimens = q6Dimens;
    }

    public void setQ7Dimens(Rectangle q7Dimens) {
        this.q7Dimens = q7Dimens;
    }

    public void setQ8Dimens(Rectangle q8Dimens) {
        this.q8Dimens = q8Dimens;
    }

    public void setpList(ArrayList<Person> pList) {
        this.pList = pList;
    }

    public void setpListQ1(ArrayList<Person> pListQ1) {
        this.pListQ1 = pListQ1;
    }

    public void setpListQ2(ArrayList<Person> pListQ2) {
        this.pListQ2 = pListQ2;
    }

    public void setpListQ3(ArrayList<Person> pListQ3) {
        this.pListQ3 = pListQ3;
    }

    public void setpListQ4(ArrayList<Person> pListQ4) {
        this.pListQ4 = pListQ4;
    }

    public void setpListQ5(ArrayList<Person> pListQ5) {
        this.pListQ5 = pListQ5;
    }

    public void setpListQ6(ArrayList<Person> pListQ6) {
        this.pListQ6 = pListQ6;
    }

    public void setpListQ7(ArrayList<Person> pListQ7) {
        this.pListQ7 = pListQ7;
    }

    public void setpListQ8(ArrayList<Person> pListQ8) {
        this.pListQ8 = pListQ8;
    }

    public void setpListTravel(ArrayList<Person> pListTravel) {
        this.pListTravel = pListTravel;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public void setTravelersPer(double travelersPer) {
        this.travelersPer = travelersPer;
    }

}
