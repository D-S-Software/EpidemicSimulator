import javax.swing.*;
import java.awt.*;

public class GUI {

    private JPanel topPanel, leftPanel, rightPanel;
    private TitlePanel titlePanel;
    private BoardPanel boardPanel;
    private ControlPanel controlPanel;
    private TalleyPanel talleyPanel;
    private GraphPanel graphPanel;
    private int preWidth, preHeight;
    private JLabel numHealthyLabel = new JLabel();
    private JLabel numSickLabel = new JLabel();
    private JLabel numRecoveredLabel = new JLabel();
    private JLabel numDeadLabel = new JLabel();
    private JButton startButton = new JButton("Start");
    private GUI gui = this;

    public GUI(int preWidth, int preHeight)
    {
        this.preHeight = preHeight;
        this.preWidth = preWidth;

        JFrame frame = new JFrame("GridBag");
        frame.setPreferredSize(new Dimension(preWidth, preHeight));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 20;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(2, 2, 2, 2);

        topPanel = new JPanel(new GridBagLayout());
        addTitlePanel();
        frame.add(topPanel, gbcMain);

        gbcMain.gridy = 1;
        gbcMain.gridwidth = 1;
        gbcMain.weighty = 40;

        leftPanel = new JPanel(new GridBagLayout());
        addPeoplePanel();
        addControlPanel();
        frame.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.weightx = 15;

        rightPanel = new JPanel(new GridBagLayout());
        addTallyPanel();
        addGraphPanel();
        frame.add(rightPanel, gbcMain);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void addTitlePanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        titlePanel = new TitlePanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        titlePanel.add(new JLabel("Epidemic Simulator"));
        titlePanel.setBackground(Color.GREEN);
        topPanel.add(titlePanel, gbc);
    }
    private void addPeoplePanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        boardPanel = new BoardPanel();
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        boardPanel.setBackground(Color.WHITE);
        leftPanel.add(boardPanel, gbc);
    }
    private void addControlPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        controlPanel = new ControlPanel(new GridLayout(2, 1));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        controlPanel.add(new JLabel("Control Panel"));
        controlPanel.add(startButton);
        controlPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(controlPanel, gbc);
    }
    private void addTallyPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        talleyPanel = new TalleyPanel(new GridLayout(2, 2));
        talleyPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        talleyPanel.add(numHealthyLabel);
        talleyPanel.add(numRecoveredLabel);
        talleyPanel.add(numSickLabel);
        talleyPanel.add(numDeadLabel);
        talleyPanel.setBackground(Color.ORANGE);
        rightPanel.add(talleyPanel, gbc);
    }
    private void addGraphPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        graphPanel = new GraphPanel();
        graphPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        graphPanel.add(new JLabel("Graph Panel"));
        graphPanel.setBackground(Color.YELLOW);
        rightPanel.add(graphPanel, gbc);
    }

    public BoardPanel getBoardPanel()
    {
        return boardPanel;
    }
    public Rectangle getBoardRec()
    {
        return boardPanel.getBounds();
    }

    public JLabel getNumHealthyLabel()
    {
        return numHealthyLabel;
    }

    public JLabel getNumSickLabel() {
        return numSickLabel;
    }

    public JLabel getNumRecoveredLabel() {
        return numRecoveredLabel;
    }

    public JLabel getNumDeadLabel() {
        return numDeadLabel;
    }
}
