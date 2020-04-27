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

    public SettingFrame()
    {
        setBackground(CustomColor.BACKGROUND);
        setPreferredSize(new Dimension(850, 600));

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

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void addLeftPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel boardSelection = new JLabel("Select Board");

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
        //generalBoard.setIcon(single);

        quadBoard = new JRadioButton("Quad    ");
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        //quadBoard.setIcon(quad);

        eightBoard = new JRadioButton("Octo    ");
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        //eightBoard.setIcon(oct);

        quarButton = new JRadioButton("Quarantine");
        quarButton.setBackground(CustomColor.BUTTON);
        quarButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        quarButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        regButton = new JRadioButton("Standard");
        regButton.setBackground(CustomColor.BUTTON);
        regButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        regButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

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

        leftPanel.add(boardSelection, gbc);

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
        quarPanel.add(quarButton);
        quarPanel.add(regButton);

        leftPanel.add(quarPanel, gbc);

        mainPanel.add(leftPanel, gbcMain);
    }

    private void addRightPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel paramLabel = new JLabel("Select the Parameters");

        travelers = new JTextField(100);
        travelers.setText(".02");
        travelers.setMinimumSize(new Dimension(60, 10));
        timeUntilQuarantine = new JTextField(100);
        timeUntilQuarantine.setText("300");
        timeUntilQuarantine.setMinimumSize(new Dimension(60, 10));
        percentQuarantine = new JTextField(100);
        percentQuarantine.setText(".8");
        percentQuarantine.setMinimumSize(new Dimension(60, 10));
        asymptomaticChance = new JTextField(100);
        asymptomaticChance.setText("0");
        asymptomaticChance.setMinimumSize(new Dimension(60, 10));
        socialDistanceValue = new JTextField(100);
        socialDistanceValue.setText("50");
        socialDistanceValue.setMinimumSize(new Dimension(60, 10));
        percentSocialDist = new JTextField(100);
        percentSocialDist.setText(".9");
        percentSocialDist.setMinimumSize(new Dimension(60, 10));
        minAge = new JTextField(100);
        minAge.setText("20");
        minAge.setMinimumSize(new Dimension(60, 10));
        maxAge = new JTextField(100);
        maxAge.setText("80");
        maxAge.setMinimumSize(new Dimension(60, 10));
        minConditions = new JTextField(100);
        minConditions.setText("0");
        minConditions.setMinimumSize(new Dimension(60, 10));
        maxConditions = new JTextField(100);
        maxConditions.setText("3");
        maxConditions.setMinimumSize(new Dimension(60, 10));

        JLabel travelersLabel = new JLabel("Travelers");
        JLabel timeQuarLabel = new JLabel("Time until Quarantine");
        JLabel percentQuarLabel = new JLabel("Percent Quarantine");
        JLabel asPercentLabel = new JLabel("Percent Asymptomatic");
        JLabel socDistValLabel = new JLabel("Social Distance");
        JLabel socialDisPercentLabel = new JLabel("Percent Social Distancing   ");
        JLabel minAgeLabel = new JLabel("Min Age");
        JLabel maxAgeLabel = new JLabel("Max Age");
        JLabel minCondLabel = new JLabel("Min Conditions");
        JLabel maxCondLabel = new JLabel("Max Conditions");

        JLabel travelersA = new JLabel("<html>     The percent of people who can move <br/>     without bound in a divided board</html>");
        travelersA.setMinimumSize(new Dimension(250, 40));
        JLabel timeUntilQuarantineA = new JLabel("<html>     The amount of time until a <br/>     sick person quarantines</html>");
        timeUntilQuarantineA.setMinimumSize(new Dimension(250, 40));
        JLabel percentQuarantineA = new JLabel("<html>     The percent of sick people <br/>     who will quarantine</html>");
        percentQuarantineA.setMinimumSize(new Dimension(250, 40));
        JLabel asymptomaticChanceA = new JLabel("<html>     The percent of people who are asymptomatic <br/>     (no quarantine or dying, yet contagious)</html>");
        asymptomaticChanceA.setMinimumSize(new Dimension(250, 40));
        JLabel socialDistanceValueA = new JLabel("<html>     The amount of space needed during <br/>     social distancing (50 recom.)</html>");
        socialDistanceValueA.setMinimumSize(new Dimension(250, 40));
        JLabel percentSocialDistA = new JLabel("<html>     Percent of people who social <br/>     distance (0 to turn off)</html>");
        percentQuarantineA.setMinimumSize(new Dimension(250, 40));
        JLabel minAgeA = new JLabel("<html>     The min possible age of a person <br/>     (affects total sick time and mortality rate)</html>");
        minAgeA.setMinimumSize(new Dimension(250, 40));
        JLabel maxAgeA = new JLabel("<html>     The max possible age of a person <br>     (affects total sick time and mortality rate)</html>");
        maxAgeA.setMinimumSize(new Dimension(250, 40));
        JLabel minConditionsA = new JLabel("<html>     The min possible preexisting condition <br/>      a person can have (affects mortality rate)</html>");
        minConditionsA.setMinimumSize(new Dimension(250, 40));
        JLabel maxConditionsA = new JLabel("<html>     The max possible preexisting condition <br/>     a person can have (affects mortality rate)</html>");
        maxConditionsA.setMinimumSize(new Dimension(250, 40));

        double weightXLabel = 5;
        double weightXFeild = 5;
        double weightXArea = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = .1;

        rightPanel.add(paramLabel);

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

    public void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(CustomColor.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton continueSim = new JButton("Continue");
        continueSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbcMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        selectBoard();
        selectParams();
    }

    private void selectBoard()
    {
        if(generalBoard.isSelected())
            boardTypeNum = 1;
        if(quadBoard.isSelected())
            boardTypeNum = 4;
        if(eightBoard.isSelected())
            boardTypeNum = 8;

        if(quarButton.isSelected())
            quarBoardBool = true;
        if(regButton.isSelected())
            quarBoardBool = false;
    }

    private void selectParams()
    {
        travelersPer = Double.parseDouble(travelers.getText());
        timeUntilQuarantineNum = Integer.parseInt(timeUntilQuarantine.getText());
        quarantineChanceNum = Double.parseDouble(percentQuarantine.getText());
        asymptomaticChanceNum = Double.parseDouble(asymptomaticChance.getText());
        socialDistanceChanceNum = Double.parseDouble(percentSocialDist.getText());
        socialDistanceValueNum = Integer.parseInt(socialDistanceValue.getText());
        minAgeNum = Integer.parseInt(minAge.getText());
        maxAgeNum = Integer.parseInt(maxAge.getText());
        minPreExistingConditionsNum = Integer.parseInt(minConditions.getText());
        maxPreExistingConditionsNum = Integer.parseInt(maxConditions.getText());
    }

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
