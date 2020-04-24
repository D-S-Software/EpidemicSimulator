import Library.CustomColor;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private JPanel topPanel, leftPanel, rightPanel;
    private TitlePanel titlePanel;
    private BoardPanel boardPanel;
    private ControlPanel controlPanel;
    private TallyPanel tallyPanel;

    private MyXYChart myXYChart;
    private MyXYChart2 myXYChart2;
    private XYChartPanel xyChartPanel;
    private XYChartPanel2 xyChartPanel2;
    private MyPieChart myPieChart;
    private PieChartPanel pieChartPanel;

    private int preWidth, preHeight;
    private GUI gui = this;
    private JFrame frame;
    private Statistics stats;
    private boolean showPieFirst = true;


    public GUI(int preWidth, int preHeight)
    {
        this.preHeight = preHeight;
        this.preWidth = preWidth;

        frame = new JFrame("EpidemicSimulator");
        frame.getContentPane().setBackground(CustomColor.EERIE_BLACK);
        ImageIcon pic1 = new ImageIcon("corona.jpg");
        Image image1 = pic1.getImage();
        frame.setIconImage(image1);
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
        topPanel.setBackground(CustomColor.EERIE_BLACK);
        addTitlePanel();
        frame.add(topPanel, gbcMain);

        gbcMain.gridy = 1;
        gbcMain.gridwidth = 1;
        gbcMain.weighty = 40;

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.EERIE_BLACK);
        addBoardPanel();
        addControlPanel();
        frame.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.weightx = .1;

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.EERIE_BLACK);
        addTallyPanel();
        addXYChartPanel2();
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
        titleFont.setForeground(CustomColor.SILVER);
        titlePanel.add(titleFont);
        titlePanel.setBackground(CustomColor.BLOOD_RED); //TODO Add Title Panel Color
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
        boardPanel.setBackground(CustomColor.JET);
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
        controlPanel.getMainPanel().setBackground(CustomColor.EERIE_BLACK); //TODO Add Control Panel Color
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
        tallyPanel.setBackground(CustomColor.ALLOY_ORANGE);

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
        xyChartPanel.setBackground(Color.YELLOW); //Being over-rid by Graph background TODO Do we need to change the color here?
        xyChartPanel.setVisible(!showPieFirst);
        rightPanel.add(xyChartPanel, gbc);
    }
    private void addXYChartPanel2()
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

        myXYChart2 = new MyXYChart2();

        xyChartPanel2 = new XYChartPanel2(myXYChart2.getXYChart(), this);
        xyChartPanel2.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        xyChartPanel2.setBackground(Color.YELLOW); //Being over-rid by Graph background TODO Do we need to change the color here?
        xyChartPanel2.setVisible(!showPieFirst);
        rightPanel.add(xyChartPanel2, gbc);
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
       // pieChartPanel.setBackground(CustomColor.JET); //Being over-rid by Graph background TODO Do we need to change the color here?

        pieChartPanel.setVisible(showPieFirst);
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

    public XYChartPanel2 getXyChartPanel2()
    {
        return xyChartPanel2;
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
        getXYChartPanel().setGraphDelay(stats.getGraphDelay());
        getXyChartPanel2().setGraphDelay(stats.getGraphDelay());
    }
}
