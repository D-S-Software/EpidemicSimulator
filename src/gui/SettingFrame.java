package gui;

import backend.disease.Disease;
import backend.disease.Disease1;
import backend.disease.Disease2;
import backend.disease.Disease3;
import backend.disease.Disease4;
import lib.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingFrame extends JFrame implements ActionListener{

    private int boardTypeNum = 1, minPreExistingConditionsNum, maxPreExistingConditionsNum, diseaseSel;
    private double asymptomaticChanceNum, socialDistanceChanceNum, quarantineChanceNum, travelersPer, socialDistanceValueNum, minAgeNum, maxAgeNum, timeUntilQuarantineNum, reinfectRateNum, antiBodyTimeNum;
    private boolean quarBoardBool = false;
    private JRadioButton choice1, choice2, choice3, choice4, custom;
    private ButtonGroup g1;
    private JTextField contagiousRange, contagiousPercent, baseMortalityRate, baseMinTimeSick, baseMaxTimeSick, startPercentHealthy;
    private JLabel contagiousRangeLabel, contagiousPercentLabel, baseMortalityRateLabel, baseMinTimeSickLabel, baseMaxTimeSickLabel, startPercentHealthyLabel;
    private Disease disease;
    private Formatter formatter;
    private GridBagConstraints gbcMain, gbc;

    JTextField travelers, timeUntilQuarantine, percentQuarantine, asymptomaticChance, socialDistanceValue, percentSocialDist, minAge, maxAge, minConditions, maxConditions, reinfectRate, antiBodyTime;

    JRadioButton generalBoard, quadBoard, eightBoard, quarButton, regButton;

    JPanel centerPanel, mainPanel;
    ControlPanel controlPanel;

    /**
     * Creates a setting frame for the parameters of the simulation when the gear button is clicked
     * @param controlPanel The controlPanel obj used to pause the simulation when the settings panel is open
     */
    public SettingFrame(ControlPanel controlPanel)
    {
        setVisible(false);
        this.controlPanel = controlPanel;
        gbcMain = new GridBagConstraints();
        gbc = new GridBagConstraints();
        formatter = new Formatter();
        formatter.formatFrame(this, CustomColor.BACKGROUND, new Dimension(1120, 680), new GridBagLayout(), "virus1Logo.png");
        formatter.setMenuBar(this);

        mainPanel = new JPanel();
        formatter.formatPanel(mainPanel, CustomColor.BACKGROUND, null, new GridBagLayout());

        formatter.setGBC(gbcMain,0,0,1,1,5,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        addLeftPanel();

        formatter.setGBC(gbcMain,1,0,1,1,1,1);
        addCenterPanel();

        formatter.setGBC(gbcMain,2,0,1,1,10,1);
        addRightPanel();

        formatter.setGBC(gbcMain,0,1,3,1,10,2);
        addBottomPanel();

        add(mainPanel);

        Timer checkOpen = new Timer(10, this);
        checkOpen.start();

        pack();
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creates and adds the left panel for the setting frame
     */
    private void addLeftPanel()
    {
        JPanel leftPanel = new JPanel();
        formatter.formatPanel(leftPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8,8,8,8),new GridBagLayout());

        JPanel leftTopPanel = new JPanel();
        formatter.formatPanel(leftTopPanel,CustomColor.SPACE_CADET_LIGHT,null,null);
        leftTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel boardSelection = new JLabel("Select Board");
        formatter.formatLabel(boardSelection,CustomColor.LIGHT_GRAY,18.0f,null);

        leftTopPanel.add(boardSelection);

        generalBoard = new JRadioButton("Mono    ");
        formatter.formatRadioButton(generalBoard,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,16.0f);
        generalBoard.setSelected(true);

        quadBoard = new JRadioButton("Quad    ");
        formatter.formatRadioButton(quadBoard,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,16.0f);

        eightBoard = new JRadioButton("Octo    ");
        formatter.formatRadioButton(eightBoard,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,16.0f);

        quarButton = new JRadioButton("Quarantine");
        formatter.formatRadioButton(quarButton,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,16.0f);

        regButton = new JRadioButton("Standard");
        formatter.formatRadioButton(regButton,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,CustomColor.ON_BUTTON_LABEL,16.0f);
        regButton.setSelected(true);

        ButtonGroup g1 = new ButtonGroup();
        g1.add(generalBoard);
        g1.add(quadBoard);
        g1.add(eightBoard);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(quarButton);
        g2.add(regButton);

        JLabel monoLabel = new JLabel();
        formatter.formatLabel(monoLabel,"SimBoardMono.png", 250,140);
        monoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generalBoard.setSelected(true);
            }
        });

        JLabel quadLabel = new JLabel();
        formatter.formatLabel(quadLabel,"SimBoardQuad.png", 250,140);
        quadLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quadBoard.setSelected(true);
            }
        });

        JLabel octoLabel = new JLabel();
        formatter.formatLabel(octoLabel,"SimBoardOcto.png", 250,140);
        octoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eightBoard.setSelected(true);
            }
        });

        formatter.setGBC(gbc,0,0,2,1,5,.1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        leftPanel.add(leftTopPanel, gbc);

        formatter.setGBC(gbc,0,1,1,1,0,1);
        leftPanel.add(generalBoard, gbc);

        formatter.setGBC(gbc,1,1,1,1,10,1);
        leftPanel.add(monoLabel, gbc);

        formatter.setGBC(gbc,0,2,1,1,0,1);
        leftPanel.add(quadBoard, gbc);

        formatter.setGBC(gbc,1,2,1,1,10,1);
        leftPanel.add(quadLabel, gbc);

        formatter.setGBC(gbc,0,3,1,1,0,1);
        leftPanel.add(eightBoard, gbc);

        formatter.setGBC(gbc,1,3,1,1,10,1);
        leftPanel.add(octoLabel, gbc);

        formatter.setGBC(gbc,0,4,2,1,5,1);

        JPanel quarPanel = new JPanel();
        formatter.formatPanel(quarPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8,8,8,8),new GridLayout(1,2));
        quarPanel.add(quarButton);
        quarPanel.add(regButton);

        leftPanel.add(quarPanel, gbc);

        mainPanel.add(leftPanel, gbcMain);
    }

    /**
     * Creates the center panel for the setting frame
     */
    private void addCenterPanel()
    {
        centerPanel = new JPanel();
        formatter.formatPanel(centerPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8,8,8,8), new GridBagLayout());

        JPanel centerTitlePanel = new JPanel();
        formatter.formatPanel(centerTitlePanel,CustomColor.SPACE_CADET_LIGHT,null, null);
        centerTitlePanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel diseaseSelection = new JLabel("Select Disease");
        formatter.formatLabel(diseaseSelection,CustomColor.LIGHT_GRAY,18.0f,null);

        centerTitlePanel.add(diseaseSelection);

        choice1 = new JRadioButton("Disease 1");
        formatter.formatRadioButton(choice1,CustomColor.DAVYS_GRAY,CustomColor.LIGHT_GRAY,CustomColor.ON_BUTTON_LABEL,16.0f);
        choice1.setSelected(true);
        choice2 = new JRadioButton("Disease 2");
        formatter.formatRadioButton(choice2, CustomColor.BUTTON, CustomColor.LIGHT_GRAY,CustomColor.ON_BUTTON_LABEL,16.0f);
        choice3 = new JRadioButton("Disease 3");
        formatter.formatRadioButton(choice3,CustomColor.BUTTON,CustomColor.LIGHT_GRAY,CustomColor.ON_BUTTON_LABEL,16.0f);
        choice4 = new JRadioButton("Disease 4");
        formatter.formatRadioButton(choice4,CustomColor.BUTTON,CustomColor.LIGHT_GRAY,CustomColor.ON_BUTTON_LABEL,16.0f);
        custom = new JRadioButton("Custom");
        formatter.formatRadioButton(custom,CustomColor.BUTTON,CustomColor.LIGHT_GRAY,CustomColor.ON_BUTTON_LABEL,16.0f);

        g1 = new ButtonGroup();
        g1.add(choice1);
        g1.add(choice2);
        g1.add(choice3);
        g1.add(choice4);
        g1.add(custom);

        JLabel pic1Label = new JLabel();
        formatter.formatLabel(pic1Label,"virus1.jpg", 40,40);
        pic1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice1.setSelected(true);
            }
        });
        JLabel pic2Label = new JLabel();
        formatter.formatLabel(pic2Label, "virus2.jpg", 40,40);
        pic2Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice2.setSelected(true);
            }
        });
        JLabel pic3Label = new JLabel();
        formatter.formatLabel(pic3Label, "virus3.jpg", 40,40);
        pic3Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice3.setSelected(true);
            }
        });
        JLabel pic4Label = new JLabel();
        formatter.formatLabel(pic4Label,"virus4.jpg",40,40);
        pic4Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice4.setSelected(true);
            }
        });

        formatter.setGBC(gbc,0,0,2,1,1,.2,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        centerPanel.add(centerTitlePanel, gbc);

        formatter.setGBC(gbc,0,1,1,1,1,1);
        centerPanel.add(choice1, gbc);

        formatter.setGBC(gbc,1,1,1,1,1,1);
        centerPanel.add(pic1Label, gbc);

        formatter.setGBC(gbc,0,2,1,1,1,1);
        centerPanel.add(choice2, gbc);

        formatter.setGBC(gbc,1,2,1,1,1,1);
        centerPanel.add(pic2Label, gbc);

        formatter.setGBC(gbc,0,3,1,1,1,1);
        centerPanel.add(choice3, gbc);

        formatter.setGBC(gbc, 1,3,1,1,1,1);
        centerPanel.add(pic3Label, gbc);

        formatter.setGBC(gbc,0,4,1,1,1,1);
        centerPanel.add(choice4, gbc);

        formatter.setGBC(gbc,1,4,1,1,1,1);
        centerPanel.add(pic4Label, gbc);

        formatter.setGBC(gbc,0,5,1,1,1,2);
        centerPanel.add(custom, gbc);

        contagiousPercent = new JTextField(1);
        formatter.formatTextField(contagiousPercent,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        contagiousRange = new JTextField(1);
        formatter.formatTextField(contagiousRange,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        baseMortalityRate = new JTextField(1);
        formatter.formatTextField(baseMortalityRate,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        baseMinTimeSick = new JTextField(1);
        formatter.formatTextField(baseMinTimeSick,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        baseMaxTimeSick = new JTextField(1);
        formatter.formatTextField(baseMaxTimeSick,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        startPercentHealthy = new JTextField(1);
        formatter.formatTextField(startPercentHealthy,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60, 10),null);

        contagiousPercentLabel = new JLabel("<html>Contagious (%)</html>");
        formatter.formatLabel(contagiousPercentLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        contagiousRangeLabel = new JLabel("<html>Contagious Range<br>(1-20)</html>");
        formatter.formatLabel(contagiousRangeLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        baseMortalityRateLabel = new JLabel("Mortality (%)");
        formatter.formatLabel(baseMortalityRateLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        baseMinTimeSickLabel = new JLabel("Min Sick (s)");
        formatter.formatLabel(baseMinTimeSickLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        baseMaxTimeSickLabel = new JLabel("Max Sick (s)");
        formatter.formatLabel(baseMaxTimeSickLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        startPercentHealthyLabel = new JLabel("Start Healthy (%)");
        formatter.formatLabel(startPercentHealthyLabel,CustomColor.LIGHT_GRAY,15.0f,null);

        formatter.setGBC(gbc,0,6,1,1,1,1);
        centerPanel.add(contagiousPercentLabel, gbc);

        formatter.setGBC(gbc,1,6,1,1,1,1);
        centerPanel.add(contagiousPercent, gbc);

        formatter.setGBC(gbc,0,7,1,1,1,1);
        centerPanel.add(baseMinTimeSickLabel, gbc);

        formatter.setGBC(gbc,1,7,1,1,1,1);
        centerPanel.add(baseMinTimeSick, gbc);

        formatter.setGBC(gbc,0,8,1,1,1,1);
        centerPanel.add(baseMortalityRateLabel, gbc);

        formatter.setGBC(gbc,1,8,1,1,1,1);
        centerPanel.add(baseMortalityRate, gbc);

        formatter.setGBC(gbc,0,9,1,1,1,1);
        centerPanel.add(contagiousRangeLabel, gbc);

        formatter.setGBC(gbc,1,9,1,1,1,1);
        centerPanel.add(contagiousRange, gbc);

        formatter.setGBC(gbc,0,10,1,1,1,1);
        centerPanel.add(baseMaxTimeSickLabel, gbc);

        formatter.setGBC(gbc,1,10,1,1,1,1);
        centerPanel.add(baseMaxTimeSick, gbc);

        formatter.setGBC(gbc,0,11,1,1,1,1);
        centerPanel.add(startPercentHealthyLabel, gbc);

        formatter.setGBC(gbc,1,11,1,1,1,1);
        centerPanel.add(startPercentHealthy, gbc);

        mainPanel.add(centerPanel, gbcMain);
    }

    /**
     * Creates and adds the right panel for the setting frame
     */
    private void addRightPanel()
    {
        JPanel rightPanel = new JPanel();
        formatter.formatPanel(rightPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8,8,8,8),new GridBagLayout());

        JPanel rightTopPanel = new JPanel();
        formatter.formatPanel(rightTopPanel,CustomColor.SPACE_CADET_LIGHT,null,null);
        rightTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel paramLabel = new JLabel("Select the Parameters");
        formatter.formatLabel(paramLabel,CustomColor.LIGHT_GRAY,18.0f,null);

        rightTopPanel.add(paramLabel);

        travelers = new JTextField(100);
        formatter.formatTextField(travelers,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"8");

        timeUntilQuarantine = new JTextField(1);
        formatter.formatTextField(timeUntilQuarantine,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"3");

        percentQuarantine = new JTextField(1);
        formatter.formatTextField(percentQuarantine,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"80");

        asymptomaticChance = new JTextField(1);
        formatter.formatTextField(asymptomaticChance,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10), "0");

        socialDistanceValue = new JTextField(1);
        formatter.formatTextField(socialDistanceValue,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10), "30");

        percentSocialDist = new JTextField(1);
        formatter.formatTextField(percentSocialDist,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"0");

        reinfectRate = new JTextField(1);
        formatter.formatTextField(reinfectRate,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"0");

        antiBodyTime = new JTextField(1);
        formatter.formatTextField(antiBodyTime,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"35");

        minAge = new JTextField(1);
        formatter.formatTextField(minAge,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"20");

        maxAge = new JTextField(1);
        formatter.formatTextField(maxAge,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"80");

        minConditions = new JTextField(1);
        formatter.formatTextField(minConditions,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"0");

        maxConditions = new JTextField(1);
        formatter.formatTextField(maxConditions,CustomColor.FIELD,CustomColor.ON_BUTTON_LABEL,null,15.0f,new Dimension(60,10),"3");

        JLabel travelersLabel = new JLabel("Travelers (%)");
        formatter.formatLabel(travelersLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel timeQuarLabel = new JLabel("Time until sick quarantine (s)");
        formatter.formatLabel(timeQuarLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel percentQuarLabel = new JLabel("Sick who quarantine (%)");
        formatter.formatLabel(percentQuarLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel asPercentLabel = new JLabel("Asymptomatic People (%)");
        formatter.formatLabel(asPercentLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel socDistValLabel = new JLabel("Social Distance");
        formatter.formatLabel(socDistValLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel socialDisPercentLabel = new JLabel("Social Dist. Participation (%)  ");
        formatter.formatLabel(socialDisPercentLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel reinfectRateLabel = new JLabel("Can be reinfected (%)  ");
        formatter.formatLabel(reinfectRateLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel antiBodyTimeLabel = new JLabel("How long antibodies last (s)  ");
        formatter.formatLabel(antiBodyTimeLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel minAgeLabel = new JLabel("Min Age");
        formatter.formatLabel(minAgeLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel maxAgeLabel = new JLabel("Max Age");
        formatter.formatLabel(maxAgeLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel minCondLabel = new JLabel("Min Conditions");
        formatter.formatLabel(minCondLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel maxCondLabel = new JLabel("Max Conditions");
        formatter.formatLabel(maxCondLabel,CustomColor.LIGHT_GRAY,16.0f,null);

        JLabel travelersA = new JLabel("<html>     The percent of people who can move <br/>     without bound in a divided board</html>");
        formatter.formatLabelMin(travelersA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel timeUntilQuarantineA = new JLabel("<html>     The amount of time until a <br/>     sick person quarantines</html>");
        formatter.formatLabelMin(timeUntilQuarantineA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel percentQuarantineA = new JLabel("<html>     The percent of sick people <br/>     who will quarantine</html>");
        formatter.formatLabelMin(percentQuarantineA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel asymptomaticChanceA = new JLabel("<html>     The percent of people asymptomatic <br/>     (no quarantine or dying, yet contagious)</html>");
        formatter.formatLabelMin(asymptomaticChanceA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel socialDistanceValueA = new JLabel("<html>     The amount of space needed during <br/>     social distancing (30 recom.)</html>");
        formatter.formatLabelMin(socialDistanceValueA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel percentSocialDistA = new JLabel("<html>     Percent of people who social <br/>     distance (0 to turn off)</html>");
        formatter.formatLabelMin(percentSocialDistA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel reinfectRateA = new JLabel("<html>     Percent of people who can be reinfected<br/>     when antibodies wear off (0 to turn off)</html>");
        formatter.formatLabelMin(reinfectRateA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel antiBodyTimeA = new JLabel("<html>     How long antibodies last <br/>     (when % reinfect > 0)</html>");
        formatter.formatLabelMin(antiBodyTimeA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel minAgeA = new JLabel("<html>     The min possible age of a person <br/>     (affects total sick time and mortality rate)</html>");
        formatter.formatLabelMin(minAgeA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel maxAgeA = new JLabel("<html>     The max possible age of a person <br>     (affects total sick time and mortality rate)</html>");
        formatter.formatLabelMin(maxAgeA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel minConditionsA = new JLabel("<html>     The min possible preexisting condition <br/>      a person can have (affects mortality rate)</html>");
        formatter.formatLabelMin(minConditionsA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        JLabel maxConditionsA = new JLabel("<html>     The max possible preexisting condition <br/>     a person can have (affects mortality rate)</html>");
        formatter.formatLabelMin(maxConditionsA,CustomColor.LIGHT_GRAY,13.0f,new Dimension(250,40));

        double weightXLabel = 5;
        double weightXField = 5;
        double weightXArea = 5;

        formatter.setGBC(gbc,0,0,3,1,10,.1);
        rightPanel.add(rightTopPanel, gbc);

        formatter.setGBC(gbc,0,1,1,1,weightXLabel,1);
        rightPanel.add(travelersLabel, gbc);

        formatter.setGBC(gbc,1,1,1,1,weightXField,1);
        rightPanel.add(travelers, gbc);

        formatter.setGBC(gbc,2,1,1,1,weightXArea,1);
        rightPanel.add(travelersA, gbc);

        formatter.setGBC(gbc,0,2,1,1,weightXLabel,1);
        rightPanel.add(timeQuarLabel, gbc);

        formatter.setGBC(gbc,1,2,1,1,weightXField,1);
        rightPanel.add(timeUntilQuarantine, gbc);

        formatter.setGBC(gbc,2,2,1,1,weightXArea,1);
        rightPanel.add(timeUntilQuarantineA, gbc);

        formatter.setGBC(gbc,0,3,1,1,weightXLabel,1);
        rightPanel.add(percentQuarLabel, gbc);

        formatter.setGBC(gbc,1,3,1,1,weightXField,1);
        rightPanel.add(percentQuarantine, gbc);

        formatter.setGBC(gbc,2,3,1,1,weightXArea,1);
        rightPanel.add(percentQuarantineA, gbc);

        formatter.setGBC(gbc,0,4,1,1,weightXLabel,1);
        rightPanel.add(asPercentLabel, gbc);

        formatter.setGBC(gbc,1,4,1,1,weightXField,1);
        rightPanel.add(asymptomaticChance, gbc);

        formatter.setGBC(gbc,2,4,1,1,weightXArea,1);
        rightPanel.add(asymptomaticChanceA, gbc);

        formatter.setGBC(gbc,0,5,1,1,weightXLabel,1);
        rightPanel.add(socDistValLabel, gbc);

        formatter.setGBC(gbc,1,5,1,1,weightXField,1);
        rightPanel.add(socialDistanceValue, gbc);

        formatter.setGBC(gbc,2,5,1,1,weightXArea,1);
        rightPanel.add(socialDistanceValueA, gbc);

        formatter.setGBC(gbc,0,6,1,1,weightXLabel,1);
        rightPanel.add(socialDisPercentLabel, gbc);

        formatter.setGBC(gbc,1,6,1,1,weightXField,1);
        rightPanel.add(percentSocialDist, gbc);

        formatter.setGBC(gbc,2,6,1,1,weightXArea,1);
        rightPanel.add(percentSocialDistA, gbc);

        formatter.setGBC(gbc,0,7,1,1,weightXLabel,1);
        rightPanel.add(minAgeLabel, gbc);

        formatter.setGBC(gbc,1,7,1,1,weightXField,1);
        rightPanel.add(minAge, gbc);

        formatter.setGBC(gbc,2,7,1,1,weightXArea,1);
        rightPanel.add(minAgeA, gbc);

        formatter.setGBC(gbc,0,8,1,1,weightXLabel,1);
        rightPanel.add(maxAgeLabel, gbc);

        formatter.setGBC(gbc,1,8,1,1,weightXField,1);
        rightPanel.add(maxAge, gbc);

        formatter.setGBC(gbc,2,8,1,1,weightXArea,1);
        rightPanel.add(maxAgeA, gbc);

        formatter.setGBC(gbc,0,9,1,1,weightXLabel,1);
        rightPanel.add(minCondLabel, gbc);

        formatter.setGBC(gbc,1,9,1,1,weightXField,1);
        rightPanel.add(minConditions, gbc);

        formatter.setGBC(gbc,2,9,1,1,weightXArea,1);
        rightPanel.add(minConditionsA, gbc);

        formatter.setGBC(gbc,0,10,1,1,weightXLabel,1);
        rightPanel.add(maxCondLabel, gbc);

        formatter.setGBC(gbc,1,10,1,1,weightXField,1);
        rightPanel.add(maxConditions, gbc);

        formatter.setGBC(gbc,2,10,1,1,weightXArea,1);
        rightPanel.add(maxConditionsA, gbc);

        formatter.setGBC(gbc,0,11,1,1,weightXLabel,1);
        rightPanel.add(reinfectRateLabel, gbc);

        formatter.setGBC(gbc,1,11,1,1,weightXField,1);
        rightPanel.add(reinfectRate, gbc);

        formatter.setGBC(gbc,2,11,1,1,weightXArea,1);
        rightPanel.add(reinfectRateA, gbc);

        formatter.setGBC(gbc,0,12,1,1,weightXLabel,1);
        rightPanel.add(antiBodyTimeLabel, gbc);

        formatter.setGBC(gbc,1,12,1,1,weightXField,1);
        rightPanel.add(antiBodyTime, gbc);

        formatter.setGBC(gbc,2,12,1,1,weightXArea,1);
        rightPanel.add(antiBodyTimeA, gbc);

        mainPanel.add(rightPanel, gbcMain);

        checkDiseaseFields();
        addKeyBindings();
    }

    /**
     * Creates and adds the bottom panel for the setting frame
     */
    private void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        formatter.formatPanel(bottomPanel,CustomColor.BACKGROUND,new Rectangle(8,8,8,8),new GridBagLayout());

        JButton continueSim = new JButton("Continue");
        formatter.formatButton(continueSim,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,null,16.0f);

        continueSim.addActionListener(e -> closeSettings());

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbcMain);
    }

    /**
     * Adds keybindings to the setting frame - including 's' and 'enter' keys to close the window
     */
    private void addKeyBindings()
    {
        InputMap inputMap = ((JPanel)this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JPanel)this.getContentPane()).getActionMap();

        Action closeSettings = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeSettings();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("S"), "Close Settings");
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "Close Settings");
        actionMap.put("Close Settings", closeSettings);
    }

    /**Checks if the setting frame is being used and updates the board type and parameters that are selected each tick
     * and sets the text for the disease parameter panel based on the disease parameters
     * @param e event each tick
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!isVisible())
        {
            selectBoard();
            selectParams();
        }
        checkDiseaseFields();
    }

    /**
     * Helper Method for filling in the text fields when a user changes the selected disease
     */
    private void checkDiseaseFields()
    {
        if(custom.isSelected() && diseaseSel != 5)
        {
            contagiousPercent.setText("");
            contagiousRange.setText("");
            baseMortalityRate.setText("");
            baseMinTimeSick.setText("");
            baseMaxTimeSick.setText("");
            startPercentHealthy.setText("");
            diseaseSel = 5;
        }
        if(choice1.isSelected() && diseaseSel != 1)
        {
            disease = new Disease1();
            setDiseaseParameters();
            diseaseSel = 1;
        }
        if(choice2.isSelected() && diseaseSel != 2)
        {
            disease = new Disease2();
            setDiseaseParameters();
            diseaseSel = 2;
        }
        if(choice3.isSelected() && diseaseSel != 3)
        {
            disease = new Disease3();
            setDiseaseParameters();
            diseaseSel = 3;
        }
        if(choice4.isSelected() && diseaseSel != 4)
        {
            disease = new Disease4();
            setDiseaseParameters();
            diseaseSel = 4;
        }
    }

    /**
     * Helper Method for Disease Parameters in the Settings window
     */
    private void setDiseaseParameters()
    {
        contagiousPercent.setText(disease.getContagiousPercent() + "");
        contagiousRange.setText(disease.getContagiousRange() + "");
        baseMortalityRate.setText(disease.getBaseMortalityRate() + "");
        baseMinTimeSick.setText("" + Math.round(disease.getBaseMinTimeSick()/100.0));
        baseMaxTimeSick.setText("" + Math.round(disease.getBaseMaxTimeSick()/100.0));
        startPercentHealthy.setText(disease.getStartPercentHealthy() + "");
    }

    /**
     * Collected the data from the board selection panel
     */
    private void selectBoard()
    {
        if(generalBoard.isSelected())
            boardTypeNum = 1;
        else if(quadBoard.isSelected())
            boardTypeNum = 4;
        else if(eightBoard.isSelected())
            boardTypeNum = 8;

        if(quarButton.isSelected())
            quarBoardBool = true;
        else if(regButton.isSelected())
            quarBoardBool = false;
    }

    /**
     * Collects the data from the parameters board
     */
    private void selectParams()
    {
        travelersPer = Double.parseDouble(travelers.getText()) / 100;
        timeUntilQuarantineNum = Double.parseDouble(timeUntilQuarantine.getText()) * 100;
        quarantineChanceNum = Double.parseDouble(percentQuarantine.getText()) / 100;
        asymptomaticChanceNum = Double.parseDouble(asymptomaticChance.getText()) / 100;
        socialDistanceChanceNum = Double.parseDouble(percentSocialDist.getText()) / 100;
        socialDistanceValueNum = Double.parseDouble(socialDistanceValue.getText());
        minAgeNum = Double.parseDouble(minAge.getText());
        maxAgeNum = Double.parseDouble(maxAge.getText());
        minPreExistingConditionsNum = Integer.parseInt(minConditions.getText());
        maxPreExistingConditionsNum = Integer.parseInt(maxConditions.getText());
        reinfectRateNum = Double.parseDouble(reinfectRate.getText()) / 100;
        antiBodyTimeNum = Double.parseDouble(antiBodyTime.getText()) * 100;
    }

    /**
     * Closes the Settings window
     */
    public void closeSettings()
    {
        try
        {
            if(getTravelers().getText().equals("") || getTimeUntilQuarantine().getText().equals("") || getPercentQuarantine().getText().equals("") || getAsymptomaticChance().getText().equals("")
                    || getSocialDistanceValue().getText().equals("") || getPercentSocialDist().getText().equals("") || getMinAge().getText().equals("") || getMaxAge().getText().equals("")
                    || getMinConditions().getText().equals("") || getMaxConditions().getText().equals("") || getReinfectRate().getText().equals("") || getAntiBodyTime().getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters in settings before starting!");
            }
            else if(Double.parseDouble(getMinAge().getText()) > Double.parseDouble(getMaxAge().getText()) || Integer.parseInt(getMinConditions().getText()) > Integer.parseInt(getMaxConditions().getText()))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please make sure Min Age is less than or equal to Max Age and Min Conditions is less than or equal to Max Conditions!");
            }
            else if(contagiousPercent.getText().equals("") || contagiousRange.getText().equals("")
                    || baseMortalityRate.getText().equals("") || baseMinTimeSick.getText().equals("")
                    || baseMaxTimeSick.getText().equals("") || startPercentHealthy.getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters for Custom Disease before starting!");
            }
            else if(Double.parseDouble(contagiousRange.getText()) > 20 || Double.parseDouble(contagiousRange.getText()) < 1)
            {
                JOptionPane.showMessageDialog(new JFrame(), "The contagious range must be between 1 - 20");
            }
            else if(Double.parseDouble(baseMinTimeSick.getText()) > Double.parseDouble(baseMaxTimeSick.getText()))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please make sure Min Time Sick is less than or equal to Max Time Sick!");
            }
            else if(controlPanel.isPlaying())
            {
                JOptionPane.showMessageDialog(new JFrame(), "Reset simulation to enable changes to settings.");
                controlPanel.resumeSim();
                setVisible(false);
            }
            else setVisible(false);
        }
        catch (java.lang.NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters are numbers and filled in correctly and Condition counts are integers!");
        }
    }

    /** Getter and Setter methods*/

    public double getTravelersPer()
    {
        return travelersPer;
    }

    public int getBoardTypeNum() {
        return boardTypeNum;
    }

    public double getMinAgeNum() {
        return minAgeNum;
    }

    public double getMaxAgeNum() {
        return maxAgeNum;
    }

    public int getMinPreExistingConditionsNum() {
        return minPreExistingConditionsNum;
    }

    public int getMaxPreExistingConditionsNum() {
        return maxPreExistingConditionsNum;
    }

    public double getSocialDistanceValueNum() {
        return socialDistanceValueNum;
    }

    public double getTimeUntilQuarantineNum() {
        return timeUntilQuarantineNum;
    }

    public double getAsymptomaticChanceNum() {
        return asymptomaticChanceNum;
    }

    public double getQuarantineChanceNum() {
        return quarantineChanceNum;
    }

    public double getSocialDistanceChanceNum() {
        return socialDistanceChanceNum;
    }

    public double getReinfectRateNum() {
        return reinfectRateNum;
    }

    public double getAntiBodyTimeNum() {
        return antiBodyTimeNum;
    }

    public boolean isQuarBoardBool() {
        return quarBoardBool;
    }
    public JTextField getTravelers()
    {
        return travelers;
    }
    public JTextField getTimeUntilQuarantine()
    {
        return timeUntilQuarantine;
    }
    public JTextField getPercentQuarantine()
    {
        return percentQuarantine;
    }
    public JTextField getAsymptomaticChance()
    {
        return asymptomaticChance;
    }
    public JTextField getSocialDistanceValue()
    {
        return socialDistanceValue;
    }
    public JTextField getPercentSocialDist()
    {
        return percentSocialDist;
    }
    public JTextField getMinAge()
    {
        return minAge;
    }
    public JTextField getMaxAge()
    {
        return maxAge;
    }
    public JTextField getMinConditions()
    {
        return minConditions;
    }
    public JTextField getMaxConditions()
    {
        return maxConditions;
    }
    public JTextField getReinfectRate()
    {
        return reinfectRate;
    }
    public JTextField getAntiBodyTime()
    {
        return antiBodyTime;
    }
    public JTextField getBaseMaxTimeSick() {
        return baseMaxTimeSick;
    }
    public JTextField getBaseMinTimeSick() {
        return baseMinTimeSick;
    }
    public JTextField getContagiousRange() {
        return contagiousRange;
    }
    public JTextField getContagiousPercent() {
        return contagiousPercent;
    }
    public JTextField getBaseMortalityRate() {
        return baseMortalityRate;
    }
    public JTextField getStartPercentHealthy() {
        return startPercentHealthy;
    }
}
