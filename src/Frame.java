import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Panel peoplePanel;
    private JPanel mainPanel;
    private JPanel tallyPanel;

    public Frame(Board board)
    {
        mainPanel = new JPanel();
        this.setSize((int)(board.dimens.xLen *1.25),(int)(1.25* board.dimens.yLen));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        peoplePanel = new Panel(board);
        tallyPanel = new JPanel();

        tallyPanel.setBounds(1000, 0, 50, 450);

        mainPanel.add(peoplePanel);
        mainPanel.add(tallyPanel);

        add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Panel getPeoplePanel()
    {
        return peoplePanel;
    }
}
