import Library.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingFrame extends JFrame implements ActionListener{

    private int boardTypeNum = 1, socialDistanceValueNum, minAgeNum, maxAgeNum, minPreExistingConditionsNum, maxPreExistingConditionsNum, timeUntilQuarantineNum;
    private double asymptomaticChanceNum, socialDistanceChanceNum, quarantineChanceNum,travelersPer;
    private boolean quarBoardBool = false;

    JTextField travelers, timeUntilQuarantine, percentQuarantine, asymptomaticChance, socialDistanceValue, percentSocialDist, minAge, maxAge, minConditions, maxConditions;

    JRadioButton generalBoard, quadBoard, eightBoard, quarButton, regButton;

    GridBagConstraints gbcMain = new GridBagConstraints();
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JMenuBar mb;
    JPanel p;
    int pX, pY;

    /**
     * Creates a setting frame for the parameters of the simulation when the gear button is clicked
     */
    public SettingFrame()
    {
        setBackground(CustomColor.BACKGROUND);
        setPreferredSize(new Dimension(920, 630));
        getContentPane().setBackground(CustomColor.BACKGROUND);

        mainPanel.setBackground(CustomColor.BACKGROUND);

        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){}

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        // Create JMenuBar
        mb=new JMenuBar();
        mb.setBackground(CustomColor.CINEROUS);
        mb.setLayout(new BorderLayout());

        // Create panel
        p=new JPanel();
        p.setPreferredSize(new Dimension(10, 25));
        p.setOpaque(false);

        // To west, mac style!
        mb.add(p,BorderLayout.WEST);

        // Add mouse listener for JMenuBar mb
        mb.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me)
            {
                pX=me.getX();
                pY=me.getY();
            }
        });

        // Add MouseMotionListener for detecting drag
        mb.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent me)
            {
                setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
            }
        });

        // Set the menu bar
        setJMenuBar(mb);

        setLayout(new GridBagLayout());
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 5;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(2, 2, 2, 2);

        addLeftPanel();

        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 1;

        addRightPanel();

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 2;

        addBottomPanel();

        add(mainPanel);

        Timer checkOpen = new Timer(10, this);
        checkOpen.start();

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creates and adds the left panel for the setting frame
     */
    private void addLeftPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        leftTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel boardSelection = new JLabel("Select Board");
        boardSelection.setForeground(CustomColor.LIGHT_GRAY);
        boardSelection.setFont(boardSelection.getFont ().deriveFont (18.0f));

        leftTopPanel.add(boardSelection);

        ImageIcon singlePic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardMono.png"));
        Image singleIm = singlePic.getImage();
        Image singleIm2 = singleIm.getScaledInstance(250,140, java.awt.Image.SCALE_SMOOTH);
        ImageIcon single = new ImageIcon(singleIm2);

        ImageIcon quadPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardQuad.png"));
        Image quadIm = quadPic.getImage();
        Image quadIm2 = quadIm.getScaledInstance(250,140, java.awt.Image.SCALE_SMOOTH);
        ImageIcon quad = new ImageIcon(quadIm2);

        ImageIcon octPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardOcto.png"));
        Image octIm = octPic.getImage();
        Image octIm2 = octIm.getScaledInstance(250,140, java.awt.Image.SCALE_SMOOTH);
        ImageIcon oct = new ImageIcon(octIm2);

        generalBoard = new JRadioButton("Mono    ");
        generalBoard.setBackground(CustomColor.BUTTON);
        generalBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        generalBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        generalBoard.setForeground(CustomColor.LIGHT_GRAY);
        generalBoard.setFont(generalBoard.getFont ().deriveFont (16.0f));

        quadBoard = new JRadioButton("Quad    ");
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        quadBoard.setForeground(CustomColor.LIGHT_GRAY);
        quadBoard.setFont(quadBoard.getFont ().deriveFont (16.0f));

        eightBoard = new JRadioButton("Octo    ");
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        eightBoard.setForeground(CustomColor.LIGHT_GRAY);
        eightBoard.setFont(eightBoard.getFont ().deriveFont (16.0f));

        quarButton = new JRadioButton("Quarantine");
        quarButton.setBackground(CustomColor.BUTTON);
        quarButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        quarButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        quarButton.setForeground(CustomColor.LIGHT_GRAY);
        quarButton.setFont(quarButton.getFont ().deriveFont (16.0f));

        regButton = new JRadioButton("Standard");
        regButton.setBackground(CustomColor.BUTTON);
        regButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        regButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        regButton.setForeground(CustomColor.LIGHT_GRAY);
        regButton.setFont(regButton.getFont ().deriveFont (16.0f));

        ButtonGroup g1 = new ButtonGroup();
        g1.add(generalBoard);
        g1.add(quadBoard);
        g1.add(eightBoard);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(quarButton);
        g2.add(regButton);

        JLabel monoLabel = new JLabel();
        monoLabel.setIcon(single);
        JLabel quadLabel = new JLabel();
        quadLabel.setIcon(quad);
        JLabel octoLabel = new JLabel();
        octoLabel.setIcon(oct);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 5;
        gbc.weighty = .1;

        leftPanel.add(leftTopPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;

        leftPanel.add(generalBoard, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = 1;

        leftPanel.add(monoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;

        leftPanel.add(quadBoard, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = 1;

        leftPanel.add(quadLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;

        leftPanel.add(eightBoard, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = 1;

        leftPanel.add(octoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 5;
        gbc.weighty = 1;

        JPanel quarPanel = new JPanel(new GridLayout(1, 2));
        quarPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        quarPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        quarPanel.add(quarButton);
        quarPanel.add(regButton);

        leftPanel.add(quarPanel, gbc);

        mainPanel.add(leftPanel, gbcMain);
    }

    /**
     * Creates and adds the right panel for the setting frame
     */
    private void addRightPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        rightTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel paramLabel = new JLabel("Select the Parameters");
        paramLabel.setForeground(CustomColor.LIGHT_GRAY);
        paramLabel.setFont(paramLabel.getFont ().deriveFont (18.0f));

        rightTopPanel.add(paramLabel);

        travelers = new JTextField(100);
        travelers.setText("20");
        travelers.setBackground(CustomColor.FIELD);
        travelers.setForeground(CustomColor.ON_BUTTON_LABEL);
        travelers.setMinimumSize(new Dimension(60, 10));
        travelers.setFont(travelers.getFont ().deriveFont (15.0f));

        timeUntilQuarantine = new JTextField(100);
        timeUntilQuarantine.setText("3");
        timeUntilQuarantine.setBackground(CustomColor.FIELD);
        timeUntilQuarantine.setForeground(CustomColor.ON_BUTTON_LABEL);
        timeUntilQuarantine.setMinimumSize(new Dimension(60, 10));
        timeUntilQuarantine.setFont(timeUntilQuarantine.getFont ().deriveFont (15.0f));

        percentQuarantine = new JTextField(100);
        percentQuarantine.setText("80");
        percentQuarantine.setBackground(CustomColor.FIELD);
        percentQuarantine.setForeground(CustomColor.ON_BUTTON_LABEL);
        percentQuarantine.setMinimumSize(new Dimension(60, 10));
        percentQuarantine.setFont(percentQuarantine.getFont ().deriveFont (15.0f));

        asymptomaticChance = new JTextField(100);
        asymptomaticChance.setText("0");
        asymptomaticChance.setBackground(CustomColor.FIELD);
        asymptomaticChance.setForeground(CustomColor.ON_BUTTON_LABEL);
        asymptomaticChance.setMinimumSize(new Dimension(60, 10));
        asymptomaticChance.setFont(asymptomaticChance.getFont ().deriveFont (15.0f));

        socialDistanceValue = new JTextField(100);
        socialDistanceValue.setText("50");
        socialDistanceValue.setBackground(CustomColor.FIELD);
        socialDistanceValue.setForeground(CustomColor.ON_BUTTON_LABEL);
        socialDistanceValue.setMinimumSize(new Dimension(60, 10));
        socialDistanceValue.setFont(socialDistanceValue.getFont ().deriveFont (15.0f));

        percentSocialDist = new JTextField(100);
        percentSocialDist.setText("0");
        percentSocialDist.setBackground(CustomColor.FIELD);
        percentSocialDist.setForeground(CustomColor.ON_BUTTON_LABEL);
        percentSocialDist.setMinimumSize(new Dimension(60, 10));
        percentSocialDist.setFont(percentSocialDist.getFont ().deriveFont (15.0f));

        minAge = new JTextField(100);
        minAge.setText("20");
        minAge.setBackground(CustomColor.FIELD);
        minAge.setForeground(CustomColor.ON_BUTTON_LABEL);
        minAge.setMinimumSize(new Dimension(60, 10));
        minAge.setFont(minAge.getFont ().deriveFont (15.0f));

        maxAge = new JTextField(100);
        maxAge.setText("80");
        maxAge.setBackground(CustomColor.FIELD);
        maxAge.setForeground(CustomColor.ON_BUTTON_LABEL);
        maxAge.setMinimumSize(new Dimension(60, 10));
        maxAge.setFont(maxAge.getFont ().deriveFont (15.0f));

        minConditions = new JTextField(100);
        minConditions.setText("0");
        minConditions.setBackground(CustomColor.FIELD);
        minConditions.setForeground(CustomColor.ON_BUTTON_LABEL);
        minConditions.setMinimumSize(new Dimension(60, 10));
        minConditions.setFont(minConditions.getFont ().deriveFont (15.0f));

        maxConditions = new JTextField(100);
        maxConditions.setText("3");
        maxConditions.setBackground(CustomColor.FIELD);
        maxConditions.setForeground(CustomColor.ON_BUTTON_LABEL);
        maxConditions.setMinimumSize(new Dimension(60, 10));
        maxConditions.setFont(maxConditions.getFont ().deriveFont (15.0f));

        JLabel travelersLabel = new JLabel("Travelers (%)");
        travelersLabel.setForeground(CustomColor.LIGHT_GRAY);
        travelersLabel.setFont(travelersLabel.getFont ().deriveFont (16.0f));

        JLabel timeQuarLabel = new JLabel("Time until sick quarantine (s)");
        timeQuarLabel.setForeground(CustomColor.LIGHT_GRAY);
        timeQuarLabel.setFont(timeQuarLabel.getFont ().deriveFont (16.0f));

        JLabel percentQuarLabel = new JLabel("Sick who quarantine (%)");
        percentQuarLabel.setForeground(CustomColor.LIGHT_GRAY);
        percentQuarLabel.setFont(percentQuarLabel.getFont ().deriveFont (16.0f));

        JLabel asPercentLabel = new JLabel("Asymptomatic People (%)");
        asPercentLabel.setForeground(CustomColor.LIGHT_GRAY);
        asPercentLabel.setFont(asPercentLabel.getFont ().deriveFont (16.0f));

        JLabel socDistValLabel = new JLabel("Social Distance");
        socDistValLabel.setForeground(CustomColor.LIGHT_GRAY);
        socDistValLabel.setFont(socDistValLabel.getFont ().deriveFont (16.0f));

        JLabel socialDisPercentLabel = new JLabel("Social Dist. Participation (%)  ");
        socialDisPercentLabel.setForeground(CustomColor.LIGHT_GRAY);
        socialDisPercentLabel.setFont(socialDisPercentLabel.getFont ().deriveFont (16.0f));

        JLabel minAgeLabel = new JLabel("Min Age");
        minAgeLabel.setForeground(CustomColor.LIGHT_GRAY);
        minAgeLabel.setFont(minAgeLabel.getFont ().deriveFont (16.0f));

        JLabel maxAgeLabel = new JLabel("Max Age");
        maxAgeLabel.setForeground(CustomColor.LIGHT_GRAY);
        maxAgeLabel.setFont(maxAgeLabel.getFont ().deriveFont (16.0f));

        JLabel minCondLabel = new JLabel("Min Conditions");
        minCondLabel.setForeground(CustomColor.LIGHT_GRAY);
        minCondLabel.setFont(minCondLabel.getFont ().deriveFont (16.0f));

        JLabel maxCondLabel = new JLabel("Max Conditions");
        maxCondLabel.setForeground(CustomColor.LIGHT_GRAY);
        maxCondLabel.setFont(maxCondLabel.getFont ().deriveFont (16.0f));

        JLabel travelersA = new JLabel("<html>     The percent of people who can move <br/>     without bound in a divided board</html>");
        travelersA.setMinimumSize(new Dimension(250, 40));
        travelersA.setForeground(CustomColor.LIGHT_GRAY);
        travelersA.setFont(travelersA.getFont ().deriveFont (13.0f));

        JLabel timeUntilQuarantineA = new JLabel("<html>     The amount of time until a <br/>     sick person quarantines</html>");
        timeUntilQuarantineA.setMinimumSize(new Dimension(250, 40));
        timeUntilQuarantineA.setForeground(CustomColor.LIGHT_GRAY);
        timeUntilQuarantineA.setFont(timeUntilQuarantineA.getFont ().deriveFont (13.0f));

        JLabel percentQuarantineA = new JLabel("<html>     The percent of sick people <br/>     who will quarantine</html>");
        percentQuarantineA.setMinimumSize(new Dimension(250, 40));
        percentQuarantineA.setForeground(CustomColor.LIGHT_GRAY);
        percentQuarantineA.setFont(percentQuarantineA.getFont ().deriveFont (13.0f));

        JLabel asymptomaticChanceA = new JLabel("<html>     The percent of people asymptomatic <br/>     (no quarantine or dying, yet contagious)</html>");
        asymptomaticChanceA.setMinimumSize(new Dimension(250, 40));
        asymptomaticChanceA.setForeground(CustomColor.LIGHT_GRAY);
        asymptomaticChanceA.setFont(asymptomaticChanceA.getFont ().deriveFont (13.0f));

        JLabel socialDistanceValueA = new JLabel("<html>     The amount of space needed during <br/>     social distancing (50 recom.)</html>");
        socialDistanceValueA.setMinimumSize(new Dimension(250, 40));
        socialDistanceValueA.setForeground(CustomColor.LIGHT_GRAY);
        socialDistanceValueA.setFont(socialDistanceValueA.getFont ().deriveFont (13.0f));

        JLabel percentSocialDistA = new JLabel("<html>     Percent of people who social <br/>     distance (0 to turn off)</html>");
        percentSocialDistA.setMinimumSize(new Dimension(250, 40));
        percentSocialDistA.setForeground(CustomColor.LIGHT_GRAY);
        percentSocialDistA.setFont(percentSocialDistA.getFont ().deriveFont (13.0f));

        JLabel minAgeA = new JLabel("<html>     The min possible age of a person <br/>     (affects total sick time and mortality rate)</html>");
        minAgeA.setMinimumSize(new Dimension(250, 40));
        minAgeA.setForeground(CustomColor.LIGHT_GRAY);
        minAgeA.setFont(minAgeA.getFont ().deriveFont (13.0f));

        JLabel maxAgeA = new JLabel("<html>     The max possible age of a person <br>     (affects total sick time and mortality rate)</html>");
        maxAgeA.setMinimumSize(new Dimension(250, 40));
        maxAgeA.setForeground(CustomColor.LIGHT_GRAY);
        maxAgeA.setFont(maxAgeA.getFont ().deriveFont (13.0f));

        JLabel minConditionsA = new JLabel("<html>     The min possible preexisting condition <br/>      a person can have (affects mortality rate)</html>");
        minConditionsA.setMinimumSize(new Dimension(250, 40));
        minConditionsA.setForeground(CustomColor.LIGHT_GRAY);
        minConditionsA.setFont(minConditionsA.getFont ().deriveFont (13.0f));

        JLabel maxConditionsA = new JLabel("<html>     The max possible preexisting condition <br/>     a person can have (affects mortality rate)</html>");
        maxConditionsA.setMinimumSize(new Dimension(250, 40));
        maxConditionsA.setForeground(CustomColor.LIGHT_GRAY);
        maxConditionsA.setFont(maxConditionsA.getFont ().deriveFont (13.0f));

        double weightXLabel = 5;
        double weightXFeild = 5;
        double weightXArea = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = .1;

        rightPanel.add(rightTopPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(travelersLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(travelers, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(travelersA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(timeQuarLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(timeUntilQuarantine, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(timeUntilQuarantineA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(percentQuarLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(percentQuarantine, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(percentQuarantineA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(asPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(asymptomaticChance, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(asymptomaticChanceA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(socDistValLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(socialDistanceValue, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(socialDistanceValueA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(socialDisPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(percentSocialDist, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(percentSocialDistA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(minAgeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(minAge, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(minAgeA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(maxAgeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(maxAge, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(maxAgeA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(minCondLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(minConditions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(minConditionsA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(maxCondLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(maxConditions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(maxConditionsA, gbc);

        mainPanel.add(rightPanel, gbcMain);
    }

    /**
     * Creates and adds the bottom panel for the setting frame
     */
    public void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(CustomColor.BACKGROUND);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton continueSim = new JButton("Continue");
        continueSim.setBackground(CustomColor.BUTTON);
        continueSim.setFont(continueSim.getFont ().deriveFont (16.0f));
        continueSim.setForeground(CustomColor.ON_BUTTON_LABEL);

        continueSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(getTravelers().getText().equals("") || getTimeUntilQuarantine().getText().equals("") || getPercentQuarantine().getText().equals("") || getAsymptomaticChance().getText().equals("")
                        || getSocialDistanceValue().getText().equals("") || getPercentSocialDist().getText().equals("") || getMinAge().getText().equals("") || getMaxAge().getText().equals("")
                        || getMinConditions().getText().equals("") || getMaxConditions().getText().equals(""))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters in settings before starting!");
                }
                else if(Integer.parseInt(getTravelers().getText()) < 0 || Integer.parseInt(getTimeUntilQuarantine().getText()) < 0
                        || Integer.parseInt(getPercentQuarantine().getText()) < 0 || Integer.parseInt(getAsymptomaticChance().getText()) < 0
                        || Integer.parseInt(getSocialDistanceValue().getText()) < 0 || Integer.parseInt(getPercentSocialDist().getText()) < 0 || Integer.parseInt(getMaxAge().getText()) < 0
                        || Integer.parseInt(getMaxAge().getText()) < 0 || Integer.parseInt(getMinConditions().getText()) < 0 || Integer.parseInt(getMaxConditions().getText()) < 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters are greater than 0!");
                }
                else setVisible(false);
            }
        });

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbcMain);
    }

    /**Checks if the setting frame is being used and updates the board type and parameters that are selected each tick
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!isVisible())
        {
            selectBoard();
            selectParams();
        }
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
        timeUntilQuarantineNum = Integer.parseInt(timeUntilQuarantine.getText()) * 100;
        quarantineChanceNum = Double.parseDouble(percentQuarantine.getText()) / 100;
        asymptomaticChanceNum = Double.parseDouble(asymptomaticChance.getText()) / 100;
        socialDistanceChanceNum = Double.parseDouble(percentSocialDist.getText()) / 100;
        socialDistanceValueNum = Integer.parseInt(socialDistanceValue.getText());
        minAgeNum = Integer.parseInt(minAge.getText());
        maxAgeNum = Integer.parseInt(maxAge.getText());
        minPreExistingConditionsNum = Integer.parseInt(minConditions.getText());
        maxPreExistingConditionsNum = Integer.parseInt(maxConditions.getText());
    }

    /** Getter and Setter methods*/

    public double getTravelersPer()
    {
        return travelersPer;
    }

    public int getBoardTypeNum() {
        return boardTypeNum;
    }

    public int getMinAgeNum() {
        return minAgeNum;
    }

    public int getMaxAgeNum() {
        return maxAgeNum;
    }

    public int getMinPreExistingConditionsNum() {
        return minPreExistingConditionsNum;
    }

    public int getMaxPreExistingConditionsNum() {
        return maxPreExistingConditionsNum;
    }

    public int getSocialDistanceValueNum() {
        return socialDistanceValueNum;
    }

    public int getTimeUntilQuarantineNum() {
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
}
