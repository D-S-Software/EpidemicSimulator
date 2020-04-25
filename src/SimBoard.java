import java.awt.*;
import java.util.ArrayList;

public abstract class SimBoard {

    private Rectangle dimens, q1Dimens, q2Dimens, q3Dimens, q4Dimens;
    private ArrayList<Person> pList, pListQ1, pListQ2, pListQ3, pListQ4;
    public final int circleRad = 8;


    public abstract void updateDistanceFromSick();

    public abstract void updatePerson();

    public abstract Rectangle getDimens();

    public abstract int getNumPeople();


    public void updateAllDimens(Rectangle updatedRect)
    {
        dimens = updatedRect;
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
}
