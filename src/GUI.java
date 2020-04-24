import javax.swing.*;
import java.awt.*;

public class GUI {

    private JPanel topPanel, leftPanel, rightPanel;
    private TitlePanel titlePanel;
    private BoardPanel boardPanel;
    private ControlPanel controlPanel;
    private TallyPanel tallyPanel;

    private MyXYChart myXYChart;
    private XYChartPanel xyChartPanel;
    private MyPieChart myPieChart;
    private PieChartPanel pieChartPanel;

    private int preWidth, preHeight;
    private GUI gui = this;
    private JFrame frame;
    private Statistics stats;


    public GUI(int preWidth, int preHeight)
    {
        this.preHeight = preHeight;
        this.preWidth = preWidth;

        frame = new JFrame("EpidemicSimulator");
        frame.setPreferredSize(new Dimension(preWidth, preHeight));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = .1;
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
        addBoardPanel();
        addControlPanel();
        frame.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.weightx = .1;

        rightPanel = new JPanel(new GridBagLayout());
        addTallyPanel();
        addXYChartPanel();
        addPieChartPanel();
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
        JLabel titleFont = new JLabel("Epidemic Simulator");
        titleFont.setFont(titleFont.getFont ().deriveFont (32.0f));
        titlePanel.add(titleFont);
        titlePanel.setBackground(Color.GREEN);
        topPanel.add(titlePanel, gbc);
    }
    private void addBoardPanel()
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
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        controlPanel = new ControlPanel(this);
        controlPanel.getMainPanel().setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        controlPanel.getMainPanel().setBackground(Color.LIGHT_GRAY);
        leftPanel.add(controlPanel.getMainPanel(), gbc);
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
        tallyPanel = new TallyPanel(this, new GridLayout(2, 3));

        rightPanel.add(tallyPanel, gbc);
    }
    private void addXYChartPanel()
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

        myXYChart = new MyXYChart(); /** This is not in the constructor because a GUI object must be created first,
                                     *then gui.stats() is called in Engine. Then myChart.setStats(stats) can be called here.*/

        xyChartPanel = new XYChartPanel(myXYChart.getXYChart(), this);
        xyChartPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        xyChartPanel.setBackground(Color.YELLOW); //Being over-rid by Graph background
        xyChartPanel.setVisible(false);
        rightPanel.add(xyChartPanel, gbc);
    }

    private void addPieChartPanel()
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

        myPieChart = new MyPieChart();

        pieChartPanel = new PieChartPanel(myPieChart.getMyPieChart(),this);
        pieChartPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        pieChartPanel.setBackground(Color.YELLOW); //Being over-rid by Graph background
        rightPanel.add(pieChartPanel, gbc);
    }

    public BoardPanel getBoardPanel()
    {
        return boardPanel;
    }

    public TallyPanel getTallyPanel()
    {
        return tallyPanel;
    }

    public Rectangle getBoardRec()
    {
        return boardPanel.getBounds();
    }

    public XYChartPanel getXYChartPanel(){
        return xyChartPanel;
    }

    public PieChartPanel getPieChartPanel()
    {
        return pieChartPanel;
    }

    public Statistics getStats()
    {
        return stats;
    }

    public void setStats(Statistics stats)
    {
        this.stats = stats;
    }
}
