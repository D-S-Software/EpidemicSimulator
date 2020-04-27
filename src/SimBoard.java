import java.awt.*;
import java.util.ArrayList;

public abstract class SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens, q5Dimens, q6Dimens, q7Dimens, q8Dimens;
    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4, pListQ5, pListQ6, pListQ7, pListQ8, pListTravel;
    public final int circleRad = 8;


    public abstract void updateDistanceFromSick();

    public abstract void updatePerson();

    public abstract Rectangle getDimens();

    public abstract int getNumPeople();

    public abstract void updateAllDimens(Rectangle updatedRect);

    public abstract int getSocialDistanceValue();


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
}
