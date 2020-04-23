import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Engine implements ActionListener {

    private Timer clock;
    private GUI gui;
    private Board simBoard;

    public Engine(GUI gui, Disease disease, int numPeople, int baseXLen, int baseYLen)
    {
        this.gui = gui;

        Dimensions boardDimens = new Dimensions(0, 0, baseXLen/2, (baseYLen+50)/2);

        simBoard = new Board(disease, boardDimens, numPeople);

        Statistics stats = new Statistics(simBoard, gui, numPeople);

        clock = new Timer(10, gui.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(this);
    }

    public void updateBoard()
    {
        simBoard.updateDistanceFromSick();

        simBoard.updatePerson();

        for(int i = 0; i < simBoard.getPList().size(); i++)
        {
            simBoard.getPList().get(i).updateDimens(gui.getBoardRec()); //Checks the borders of the BoardPanel each tick to update each person move / bounce method
            simBoard.getPList().get(i).draw(gui.getBoardPanel().getGraphics());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBoard();
    }

   public Timer getClock()
   {
       return clock;
   }


}
