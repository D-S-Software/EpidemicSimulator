import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private JPanel mainPanel;
    private JPanel topPanel; //title panel
    private JPanel bottomPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private BoardPanel boardPanel;
    private TallyPanel tallyPanel;

    public Frame(Board board, Dimensions tallyDimens)
    {
        this.setSize(2*board.dimens.xLen,(65*board.dimens.xLen /48)); //TODO FIX

        mainPanel = new JPanel(); //total panel
        topPanel = new JPanel();
        topPanel.setSize(2*board.dimens.xLen, board.dimens.xLen/4);
        bottomPanel = new JPanel();
        bottomLeftPanel = new JPanel();
        bottomRightPanel= new JPanel();

        boardPanel = new BoardPanel(board);
        tallyPanel = new TallyPanel(tallyDimens);

        bottomLeftPanel.setLayout(new GridLayout(2,1));
        bottomLeftPanel.add(boardPanel);

        bottomRightPanel.setLayout(new GridLayout(2,1));
        bottomRightPanel.add(tallyPanel);

        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.add(bottomLeftPanel);
        bottomPanel.add(bottomRightPanel);

        mainPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        this.add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public BoardPanel getBoardPanel()
    {
        return boardPanel;
    }

    public TallyPanel getTallyPanel()
    {
        return tallyPanel;
    }
}
