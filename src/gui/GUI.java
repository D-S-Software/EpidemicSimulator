package gui;

import backend.Statistics;
import gui.chart.*;
import lib.CustomColor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    private Formatter formatter;
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
        TitleFrame titleFrame = new TitleFrame(frame);
        formatter = new Formatter();
        formatter.formatFrame(frame, CustomColor.BACKGROUND, null, new GridBagLayout(), "virus1Logo.png");

        GridBagConstraints gbcMain = new GridBagConstraints();
        formatter.setGBC(gbcMain, 0,0,2,1,10,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2,2,2,2));

        topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(CustomColor.BACKGROUND);
        addTitlePanel();
        frame.add(topPanel, gbcMain);

        formatter.setGBC(gbcMain, 0,1,1,1,10,40);

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.BACKGROUND);
        leftPanel.setPreferredSize(new Dimension(600,450));
        addBoardPanel();
        addControlPanel();
        frame.add(leftPanel, gbcMain);

        formatter.setGBC(gbcMain, 1,1,1,1,1,40);

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.BACKGROUND);
        addTallyPanel();
        addChartPanels();
        frame.add(rightPanel, gbcMain);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(false);

        tallyPanel.setShowCases(showCasesFirst);

        frame.setFocusTraversalKeysEnabled(false);
        addKeyBindings();
        titleFrame.setCanStart();
    }

    /**
     * Creates and adds the Title Panel for the main gui
     */
    private void addTitlePanel()
    {
        formatter.setGBC(gbc,0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5));
        titlePanel = new TitlePanel();
        formatter.formatPanel(titlePanel,CustomColor.BLOOD_RED,new Rectangle(8,8,8,8),null);

        JLabel titleFont = new JLabel();
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/SimTitle.png"));
        Image image1 = pic1.getImage();
        ImageIcon edit1 = new ImageIcon(image1);
        titleFont.setIcon(edit1);

        titlePanel.add(titleFont);
        topPanel.add(titlePanel, gbc);
    }

    /**
     * Creates and adds the simBoardPanel for the main gui
     */
    private void addBoardPanel()
    {
        formatter.setGBC(gbc,0,0,1,1,1,80,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5));
        simBoardPanel = new SimBoardPanel();
        leftPanel.add(simBoardPanel, gbc);
    }

    /**
     * Creates and adds the Control Panel for the main gui
     */
    private void addControlPanel()
    {
        formatter.setGBC(gbc,0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,5,0,5));
        controlPanel = new ControlPanel(this);
        leftPanel.add(controlPanel.getMainPanel(), gbc);
    }

    /**
     * Creates and adds the Tally Panel for the main gui
     */
    private void addTallyPanel()
    {
        formatter.setGBC(gbc,0,0,1,1,1,5,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5));
        tallyPanel = new TallyPanel(this);
        rightPanel.add(tallyPanel, gbc);
    }

    /**
     * Creates and adds the Graph Panels for the main gui.
     */
    private void addChartPanels()
    {
        formatter.setGBC(gbc,0,1,1,1,1,20,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5));

        myXYChart = new MyXYChart(); // This is not in the constructor because a gui object must be created first, then gui.stats() is called in Engine. Then setStats(stats) can be called here.
        xyChartPanel = new XYChartPanel(myXYChart.getXYChart(), this);
        xyChartPanel.setVisible(!showPieFirst & !showCasesFirst);
        rightPanel.add(xyChartPanel, gbc);

        myXYChart2 = new MyXYChart2();
        xyChartPanel2 = new XYChartPanel2(myXYChart2.getXYChart(), this);
        xyChartPanel2.setVisible(!showPieFirst && showCasesFirst);
        rightPanel.add(xyChartPanel2, gbc);

        myPieChart = new MyPieChart();
        pieChartPanel = new PieChartPanel(myPieChart.getMyPieChart(),this);
        pieChartPanel.setVisible(showPieFirst);
        rightPanel.add(pieChartPanel, gbc);
    }

    /**
     * Adds keybindings to the main frame - including multiple keys for accessing the control panel
     */
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