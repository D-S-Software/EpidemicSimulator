import java.util.ArrayList;

public class Board {

    private int base;
    private int height;
    private ArrayList<Person> personArrayList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.*/
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

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.*/
    public Board(int base, int height, ArrayList<Person> personArrayList)
    {
        this.base = base;
        this.height = height;
        this.personArrayList = personArrayList;
    }

}
