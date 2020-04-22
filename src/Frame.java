import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.Flow;

public class Frame extends JFrame {

    private JPanel mainPanel;
    private JPanel topPanel;
    private TitlePanel titlePanel;
    private JPanel leftPanel;
    private JPanel leftGridPanel;
    private JPanel rightPanel;
    private JPanel rightGridPanel;
    private BoardPanel boardPanel;
    private TallyPanel tallyPanel;
    private GraphPanel graphPanel;
    private ControlPanel controlPanel;

    private int buffer;

    public Frame(Board board, Dimensions boardDimens, int boardWidth)
    {
        buffer = (int)(boardWidth / 12.0);

        this.setSize(2*boardDimens.xLen,(65*boardDimens.xLen /48));
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(2, 2));
        mainContainer.setBackground(Color.BLUE);
        mainContainer.setLayout(new GridLayout(1, 2, 5, 5));
        //this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(4));
        leftPanel.setBackground(Color.RED);

        leftGridPanel = new JPanel();
        leftGridPanel.setLayout(new GridLayout(2, 1, 10, 10));
        leftGridPanel.setBackground(Color.PINK);

        boardPanel = new BoardPanel(board);
        controlPanel = new ControlPanel();
        leftGridPanel.add(boardPanel);
        leftGridPanel.add(controlPanel);

        leftPanel.add(leftGridPanel);
        mainContainer.add(leftPanel);

        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(4));
        rightPanel.setBackground(Color.DARK_GRAY);

        rightGridPanel = new JPanel();
        rightGridPanel.setLayout(new GridLayout(2, 1, 10, 10));
        rightGridPanel.setBackground(Color.BLACK);

        tallyPanel = new TallyPanel();
        graphPanel = new GraphPanel();
        rightGridPanel.add(tallyPanel);
        rightGridPanel.add(graphPanel);

        rightPanel.add(rightGridPanel);
        mainContainer.add(rightPanel);







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
