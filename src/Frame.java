import javax.swing.*;

public class Frame extends JFrame {

    private BoardPanel boardPanel;
    private JPanel mainPanel;
    //private TallyPanel tallyPanel; TODO Add this back in

    public Frame(Board board, Dimensions tallyDimens)
    {
        mainPanel = new JPanel();
        this.setSize((int)(board.dimens.xLen *1.25),(int)(1.25* board.dimens.yLen));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        boardPanel = new BoardPanel(board);
        //tallyPanel = new TallyPanel(tallyDimens); TODO Add this back in

        mainPanel.add(boardPanel);
       // mainPanel.add(tallyPanel); TODO Add this back in

        add(mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public BoardPanel getBoardPanel()
    {
        return boardPanel;
    }

    /** TODO Add this back in
    public TallyPanel getTallyPanel()
    {
        return tallyPanel;
    }*/
}
