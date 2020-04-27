import Library.CustomColor;

import javax.swing.*;
import java.awt.*;

public class SettingFrame extends JFrame {

    GridBagConstraints gbcMain = new GridBagConstraints();
    JPanel mainPanel = new JPanel(new GridBagLayout());

    public SettingFrame()
    {
        setBackground(CustomColor.BACKGROUND);

        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/corona.jpg"));
        Image image1 = pic1.getImage();
        setIconImage(image1);

        setPreferredSize(new Dimension(1200, 750));
        setResizable(false);

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

        add(mainPanel);

        pack();
        setLocationByPlatform(true);
        setVisible(true);
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
        Image singleIm2 = singleIm.getScaledInstance(400,200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon single = new ImageIcon(singleIm2);

        ImageIcon quadPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardQuad.png"));
        Image quadIm = quadPic.getImage();
        Image quadIm2 = quadIm.getScaledInstance(400,200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon quad = new ImageIcon(quadIm2);

        ImageIcon octPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardOcto.png"));
        Image octIm = octPic.getImage();
        Image octIm2 = octIm.getScaledInstance(400,200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon oct = new ImageIcon(octIm2);

        JRadioButton generalBoard = new JRadioButton("Mono    ");
        generalBoard.setBackground(CustomColor.BUTTON);
        generalBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        generalBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        //generalBoard.setIcon(single);

        JRadioButton quadBoard = new JRadioButton("Quad    ");
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        //quadBoard.setIcon(quad);

        JRadioButton eightBoard = new JRadioButton("Octo    ");
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        //eightBoard.setIcon(oct);

        JRadioButton quarButton = new JRadioButton("Quarantine");
        quarButton.setBackground(CustomColor.BUTTON);
        quarButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        quarButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        JRadioButton regButton = new JRadioButton("Standard");
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
        gbc.weighty = 0;

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

        JTextField travelers = new JTextField(100);
        travelers.setPreferredSize(new Dimension(50, 10));
        JTextField timeUntilQuarantine = new JTextField(100);
        timeUntilQuarantine.setPreferredSize(new Dimension(50, 10));
        JTextField percentQuarantine = new JTextField(100);
        percentQuarantine.setPreferredSize(new Dimension(50, 10));
        JTextField asymptomaticChance = new JTextField(100);
        asymptomaticChance.setPreferredSize(new Dimension(50, 10));
        JTextField socialDistanceValue = new JTextField(100);
        socialDistanceValue.setPreferredSize(new Dimension(50, 10));
        JTextField percentSocialDist = new JTextField(100);
        percentSocialDist.setPreferredSize(new Dimension(50, 10));
        JTextField minAge = new JTextField(100);
        minAge.setPreferredSize(new Dimension(50, 10));
        JTextField maxAge = new JTextField(100);
        maxAge.setPreferredSize(new Dimension(50, 10));
        JTextField minConditions = new JTextField(100);
        minConditions.setPreferredSize(new Dimension(50, 10));
        JTextField maxConditions = new JTextField(100);
        maxConditions.setPreferredSize(new Dimension(50, 10));

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

        JTextArea travelersA = new JTextArea("\nThe number of people who can move \nwithout bound in a divided board");
        JTextArea timeUntilQuarantineA = new JTextArea("\nThe amount of time until a \nsick person quarantines");
        JTextArea percentQuarantineA = new JTextArea("\nThe percent of sick people \nwho will quarantine");
        JTextArea asymptomaticChanceA = new JTextArea("\nThe percent of people who are asymptomatic \n(no quarantine or dying, yet contagious)");
        JTextArea socialDistanceValueA = new JTextArea("\nThe amount of space needed during \nsocial distancing (50 recom.)");
        JTextArea percentSocialDistA = new JTextArea("\nPercent of people who social \ndistance (0 to turn off)");
        JTextArea minAgeA = new JTextArea("\nThe min possible age of a \nperson (affects total sick time \nand mortality rate)");
        JTextArea maxAgeA = new JTextArea("\nThe max possible age of a \nperson (affects total sick time and \nmortality rate)");
        JTextArea minConditionsA = new JTextArea("\nThe min possible preexisting condition \na person can have (affects mortality rate)");
        JTextArea maxConditionsA = new JTextArea("\nThe max possible preexisting condition \na person can have (affects mortality rate)");

        double weightXLabel = 0;
        double weightXFeild = 10;
        double weightXArea = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(travelersLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(travelers, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(travelersA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(timeQuarLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(timeUntilQuarantine, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(timeUntilQuarantineA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(percentQuarLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(percentQuarantine, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(percentQuarantineA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(asPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(asymptomaticChance, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(asymptomaticChanceA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(socDistValLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(socialDistanceValue, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(socialDistanceValueA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(socialDisPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(percentSocialDist, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(percentSocialDistA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(minAgeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(minAge, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(minAgeA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(maxAgeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(maxAge, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(maxAgeA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(minCondLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(minConditions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(minConditionsA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(maxCondLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(maxConditions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(maxConditionsA, gbc);

        mainPanel.add(rightPanel, gbcMain);
    }
}
