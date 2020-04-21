import javax.swing.*;

public class Frame extends JFrame {

    private BoardPanel boardPanel;
    private JPanel mainPanel;
    private TallyPanel tallyPanel;

    public Frame(Board board, Dimensions tallyDimens)
    {
        mainPanel = new JPanel();
        this.setSize((int)(board.dimens.xLen *1.25),(int)(1.25* board.dimens.yLen));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        boardPanel = new BoardPanel(board);
        tallyPanel = new TallyPanel(tallyDimens);

        mainPanel.add(boardPanel);
        //mainPanel.add(tallyPanel); TODO add this back in

        add(mainPanel);

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
