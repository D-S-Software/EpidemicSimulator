import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Panel peoplePanel;
    private JPanel tallyPanel;

    public Frame(Board board)
    {
        peoplePanel = new Panel(board);
        this.setSize((int)(board.dimens.xLen *1.25),(int)(1.25* board.dimens.yLen));
        this.add(peoplePanel);

        /**tallyPanel = new JPanel(new BorderLayout());
        tallyPanel.setSize(100, 450);
        this.add(tallyPanel);*/

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Panel getPeoplePanel()
    {
        return peoplePanel;
    }
}
