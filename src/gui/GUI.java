package gui;

import backend.Statistics;
import gui.chart.*;
import lib.CustomColor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.Font.BOLD;

public class GUI {

    private JPanel topPanel, leftPanel, rightPanel;
    private TitlePanel titlePanel;
    private SimBoardPanel simBoardPanel;
    private ControlPanel controlPanel;
    private TallyPanel tallyPanel;

    private MyXYChart myXYChart;
    private MyXYChart2 myXYChart2;
    private XYChartPanel xyChartPanel;
    private XYChartPanel2 xyChartPanel2;
    private MyPieChart myPieChart;
    private PieChartPanel pieChartPanel;

    private GUI gui = this;
    private JFrame frame;
    private Statistics stats;
    private boolean showPieFirst = true, showCasesFirst = true;

    private GridBagConstraints gbc = new GridBagConstraints();

    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    /** Creates the main gui for the simulation
     */
    public GUI()
    {
        frame = new JFrame("EpidemicSimulator");
        frame.getContentPane().setBackground(CustomColor.BACKGROUND);
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/corona.jpg"));
        Image image1 = pic1.getImage();
        frame.setIconImage(image1);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(2, 2, 2, 2);

        topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(CustomColor.BACKGROUND);
        addTitlePanel();
        frame.add(topPanel, gbcMain);

        gbcMain.gridy = 1;
        gbcMain.gridwidth = 1;
        gbcMain.weighty = 40;

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.BACKGROUND);
        addBoardPanel();
        addControlPanel();
        leftPanel.setPreferredSize(new Dimension(600,450));
        frame.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.weightx = 1;

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.BACKGROUND);
        addTallyPanel();
        addXYChartPanel2();
        addXYChartPanel();
        addPieChartPanel();
        frame.add(rightPanel, gbcMain);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        tallyPanel.setShowCases(showCasesFirst);

        frame.setFocusTraversalKeysEnabled(false);
        addKeyBindings();
    }

    /**
     * Creates and adds the Title Panel for the main gui
     */
    private void addTitlePanel()
    {
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
        //titleFont.setFont(titleFont.getFont ().deriveFont (32.0f));
        titleFont.setFont(new Font("Helvetica",BOLD, 36));
        titleFont.setForeground(CustomColor.SILVER);
        titlePanel.add(titleFont);
        titlePanel.setBackground(CustomColor.BLOOD_RED);
        topPanel.add(titlePanel, gbc);
    }

    /**
     * Creates and adds the simBoardPanel for the main gui
     */
    private void addBoardPanel()
    {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 40;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        simBoardPanel = new SimBoardPanel();
        simBoardPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        leftPanel.add(simBoardPanel, gbc);
    }

    /**
     * Creates and adds the Control Panel for the main gui
     */
    private void addControlPanel()
    {
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
        controlPanel.getMainPanel().setBackground(CustomColor.BACKGROUND);
        leftPanel.add(controlPanel.getMainPanel(), gbc);
    }

    /**
     * Creates and adds the Tally Panel for the main gui
     */
    private void addTallyPanel()
    {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        tallyPanel = new TallyPanel(this, new GridLayout(3, 3));
        tallyPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);

        rightPanel.add(tallyPanel, gbc);
    }

    /**
     * Creates and adds the Line Graph (All stats) Panel for the main gui. This is created after the first simulation runs with an engine constructor
     */
    private void addXYChartPanel()
    {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        myXYChart = new MyXYChart(); // This is not in the constructor because a gui object must be created first, then gui.stats() is called in Engine. Then setStats(stats) can be called here.

        xyChartPanel = new XYChartPanel(myXYChart.getXYChart(), this);
        xyChartPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        xyChartPanel.setVisible(!showPieFirst & !showCasesFirst);
        rightPanel.add(xyChartPanel, gbc);
    }

    /**
     * Creates and adds the Line Graph (total cases) Panel for the main gui. This is created after the first simulation runs with an engine constructor
     */
    private void addXYChartPanel2()
    {
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
        xyChartPanel2.setVisible(!showPieFirst && showCasesFirst);
        rightPanel.add(xyChartPanel2, gbc);
    }

    /**
     * Creates and adds the Pie chart Panel for the main gui. This is created after the first simulation runs with an engine constructor
     */
    private void addPieChartPanel()
    {
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

        pieChartPanel.setVisible(showPieFirst);
        rightPanel.add(pieChartPanel, gbc);
    }

    private void addKeyBindings()
    {
        InputMap inputMap = ((JPanel)frame.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JPanel)frame.getContentPane()).getActionMap();

        Action openSettings = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.settingButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("S"), "Open Settings");
        actionMap.put("Open Settings", openSettings);

        Action openInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.infoButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("I"), "Open Info");
        actionMap.put("Open Info", openInfo);

        Action playPause = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.playPauseButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "Play/Pause");
        actionMap.put("Play/Pause", playPause);

        Action speedUp = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.speedUpButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "Speed Up");
        actionMap.put("Speed Up", speedUp);

        Action slowDown = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.slowDownButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "Slow Down");
        actionMap.put("Slow Down", slowDown);

        Action startSim = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.startSim();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "Start Sim");
        actionMap.put("Start Sim", startSim);

        Action switchGraph = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tallyPanel.switchGraphButton();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("TAB"), "Switch Graph");
        actionMap.put("Switch Graph", switchGraph);
    }

    /** Getter and Setter Methods*/

    public SimBoardPanel getSimBoardPanel()
    {
        return simBoardPanel;
    }

    public TallyPanel getTallyPanel()
    {
        return tallyPanel;
    }

    public Rectangle getSimBoardRec()
    {
        return simBoardPanel.getBounds();
    }

    public XYChartPanel getXYChartPanel(){
        return xyChartPanel;
    }

    public XYChartPanel2 getXYChartPanel2()
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

    public ControlPanel getControlPanel(){return controlPanel;}

    public void setStats(Statistics stats)
    {
        this.stats = stats;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}