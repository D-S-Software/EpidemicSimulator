import java.awt.*;
import java.util.ArrayList;

public class SimBoardQuad extends SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, travelDimens;
    private ArrayList<Rectangle> dimensList;
    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListTravel;
    private ArrayList<ArrayList<Person>> listPList;
    private int numPeople;
    private boolean asymptomatic, isSocialDistancing;
    private int socialDistanceValue;

    public SimBoardQuad(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                        int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer) {

        super(disease, dimens,  numPeople,asymptomaticChance, socialDistanceValue,  socialDistanceChance,  minAge,  maxAge,
                minPreExistingConditions, maxPreExistingConditions);
        //this.dimens = dimens;

        // this.socialDistanceValue = socialDistanceValue;
        this.travelDimens = dimens;
        // this.numPeople = numPeople;
        updateAllDimens(dimens);

        // pList = new ArrayList<>();
        //pListQ1 = new ArrayList<>();
       // pListQ2 = new ArrayList<>();
      //  pListQ3 = new ArrayList<>();
      //  pListQ4 = new ArrayList<>();
      //  pListTravel = new ArrayList<>();

      //  listPList.add(pList);
      //  listPList.add(pListQ1);
      //  listPList.add(pListQ2);
      //  listPList.add(pListQ3);
       // listPList.add(pListQ4);
       // listPList.add(pListTravel);

        int travelers = (int) (numPeople * travelersPer);


    }

    public void constructDimensList() {
        getDimensList().add(dimens);
        getDimensList().add(q1Dimens);
        getDimensList().add(q2Dimens);
        getDimensList().add(q3Dimens);
        getDimensList().add(q4Dimens);
        getDimensList().add(travelDimens);

    }

    public void constructListPList() {
        int k = 0;
        for (int i = 0; i < numPeople - travelers; i++) {

            if (Math.random() < asymptomaticChance)
                asymptomatic = true;
            else
                asymptomatic = false;

            if (Math.random() < socialDistanceChance)
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


            Person person = new Person(personalAge, personalConditions, xPos1, yPos1, dimens, disease, circleRad, asymptomatic, isSocialDistancing);

            k++;
            if (k == 1) {
                person.setDimens(q1Dimens);
                person.setxPos(xPos1);
                person.setyPos(yPos1);
                person.setQuadLocation(1);
                pListQ1.add(person);
            }
            if (k == 2) {
                person.setDimens(q2Dimens);
                person.setxPos(xPos2);
                person.setyPos(yPos2);
                person.setQuadLocation(2);
                pListQ2.add(person);
            }
            if (k == 3) {
                person.setDimens(q3Dimens);
                person.setxPos(xPos3);
                person.setyPos(yPos3);
                person.setQuadLocation(3);
                pListQ3.add(person);
            }
            if (k == 4) {
                person.setDimens(q4Dimens);
                person.setxPos(xPos4);
                person.setyPos(yPos4);
                person.setQuadLocation(4);
                pListQ4.add(person);
                k = 0;
            }
        }

        for (int i = 0; i < travelers; i++) {
            if (Math.random() < asymptomaticChance)
                asymptomatic = true;
            else
                asymptomatic = false;

            if (Math.random() < socialDistanceChance)
                isSocialDistancing = true;
            else
                isSocialDistancing = false;

            int personalAge = (int) (minAge + (maxAge - minAge) * Math.random());
            int personalConditions = (int) (minPreExistingConditions + (maxPreExistingConditions - minPreExistingConditions) * Math.random());
            int xPos = dimens.x + (int) (dimens.width * Math.random());
            int yPos = dimens.y + (int) (dimens.height * Math.random());

            pListTravel.add(new Person(personalAge, personalConditions, xPos, yPos, travelDimens, disease, circleRad, asymptomatic, isSocialDistancing));
        }

        for (int i = 0; i < pListQ1.size(); i++)
            pList.add(pListQ1.get(i));
        for (int i = 0; i < pListQ2.size(); i++)
            pList.add(pListQ2.get(i));
        for (int i = 0; i < pListQ3.size(); i++)
            pList.add(pListQ3.get(i));
        for (int i = 0; i < pListQ4.size(); i++)
            pList.add(pListQ4.get(i));
        for (int i = 0; i < pListTravel.size(); i++)
            pList.add(pListTravel.get(i));

    }

    public void updateAllDimens(Rectangle updatedRect) {
        dimens = updatedRect;

        int width = (dimens.width - dimens.x) / 2 - circleRad;
        int height = (dimens.height - dimens.y) / 2 - circleRad;

        int newXStart = (dimens.width - dimens.x) / 2 + circleRad;
        int newYStart = (dimens.height - dimens.y) / 2 + circleRad;

        q1Dimens = new Rectangle(dimens.x, dimens.y, width, height);
        q2Dimens = new Rectangle(newXStart, dimens.y, width, height);
        q3Dimens = new Rectangle(dimens.x, newYStart, width, height);
        q4Dimens = new Rectangle(newXStart, newYStart, width, height);
        travelDimens = dimens;
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick() {
        for (int i = 0; i < pListQ1.size(); i++) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (!pListQ1.get(i).getHasDisease())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(pListQ1.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ1.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0))
                minDist = 0.1;
            pListQ1.get(i).setDistanceFromSick(minDist);
            pListQ1.get(i).setClosestSickIndex(closestSickIndex);
        }
        for (int i = 0; i < pListQ2.size(); i++) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (!pListQ2.get(i).getHasDisease())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(pListQ2.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ2.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0))
                minDist = 0.1;
            pListQ2.get(i).setDistanceFromSick(minDist);
            pListQ2.get(i).setClosestSickIndex(closestSickIndex);
        }
        for (int i = 0; i < pListQ3.size(); i++) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (!pListQ3.get(i).getHasDisease())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(pListQ3.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ3.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0))
                minDist = 0.1;
            pListQ3.get(i).setDistanceFromSick(minDist);
            pListQ3.get(i).setClosestSickIndex(closestSickIndex);
        }
        for (int i = 0; i < pListQ4.size(); i++) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (!pListQ4.get(i).getHasDisease())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(pListQ4.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListQ4.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0))
                minDist = 0.1;
            pListQ4.get(i).setDistanceFromSick(minDist);
            pListQ4.get(i).setClosestSickIndex(closestSickIndex);
        }
        for (int i = 0; i < pListTravel.size(); i++) {
            double minDist = Math.sqrt(Math.pow(dimens.width, 2) + Math.pow(dimens.height, 2));
            int closestSickIndex = 0;

            if (!pListTravel.get(i).getHasDisease())
                for (int j = 0; j < pList.size(); j++)
                    if (pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy()) {
                        double distTest = Math.sqrt(Math.pow(pListTravel.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pListTravel.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if (distTest < minDist) {
                            minDist = distTest;
                            closestSickIndex = j;
                        }
                    }
            Double dist = minDist;
            if (dist.equals(0))
                minDist = 0.1;
            pListTravel.get(i).setDistanceFromSick(minDist);
            pListTravel.get(i).setClosestSickIndex(closestSickIndex);
        }
    }

    public void updateListPList() {
        for (int i = 1; i < getListPList().size(); i++) {
            updatePList(getListPList().get(i), getListPList().get(0));
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */

}

