import java.util.ArrayList;

public class Board {

    private int base;
    private int height;
    private ArrayList<Person> personArrayList;

    public Board(int base, int height, Person personModel, int numPeople)
    {
        this.base = base;
        this.height = height;

        personArrayList = new ArrayList<Person>();
        for(int i = 0; i < numPeople; i++)
        {
            personArrayList.add(new Person(personModel));
        }

    }

}
