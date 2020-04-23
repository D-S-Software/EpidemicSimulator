import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

   public final Dimensions dimens;
   private ArrayList<Person> pList;
   private GUI gui;

    public Board(GUI gui, Disease disease, int xOrigin, int yOrigin, int xLen, int yLen, int numPeople)
    {
        this.dimens = new Dimensions(xOrigin, yOrigin, xLen, yLen);
        this.gui = gui;

        gui.getBoardPanel().add(this);
        gui.getFrame().pack();

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = xOrigin + (int)(xLen*Math.random());
            int yPos = yOrigin + (int)(yLen*Math.random());

            pList.add(new Person(20, false, xPos, yPos, dimens, disease));
        }
    }

    public Board(GUI gui, Disease disease, Dimensions dimens, int numPeople)
    {
        this(gui, disease, dimens.getxOrigin(), dimens.getyOrigin(), dimens.getxLen(), dimens.getyLen(), numPeople);
    }

    /**
     * Finds the closest sick person to each healthy person and returns the distance between them
     * Based on the default board size this is from 0 to 1000ish
     */
    public void updateDistanceFromSick()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            double minDist = Math.sqrt(Math.pow(dimens.getxLen(), 2) + Math.pow(dimens.getyLen(), 2));

            if(!pList.get(i).getHasDisease())
                for(int j = 0; j < pList.size(); j++)
                    if(i != j && pList.get(j).getHasDisease() && !pList.get(j).getIsHealthy())
                    {
                        double distTest = Math.sqrt(Math.pow(pList.get(i).getXPos() - pList.get(j).getXPos(), 2) + Math.pow(pList.get(i).getYPos() - pList.get(j).getYPos(), 2));
                        if(distTest < minDist)
                            minDist = distTest;
                    }
            Double dist = minDist;
            if(dist.equals(0))
                minDist = 0.1;
            pList.get(i).setDistanceFromSick(minDist);
        }
    }

    /** For all the Person objects in board, they are checked for sickness, moved and removed if dead */
    public void updatePerson()
    {
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).checkCondition();
            pList.get(i).move();
            if(!pList.get(i).getHasDisease() && !pList.get(i).getIsHealthy())
                pList.remove(pList.get(i));
        }
    }

    public ArrayList<Person> getPList()
    {
        return pList;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;

        updateDistanceFromSick();

        updatePerson();

        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).updateDimens(gui.getBoardRec()); //Checks the borders of the BoardPanel each tick to update each person move / bounce method
            pList.get(i).draw(g2D);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
