import Library.CustomColor;

import javax.swing.*;
import java.awt.*;

public class SettingFrame extends JFrame {

    public SettingFrame()
    {
        setBackground(CustomColor.BACKGROUND);
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/corona.jpg"));
        Image image1 = pic1.getImage();
        setIconImage(image1);
        setPreferredSize(new Dimension(400, 200));
        setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 5;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(2, 2, 2, 2);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        leftPanel.setBackground(CustomColor.JET);

        JLabel boardSelection = new JLabel("Select Board");

        ImageIcon singlePic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardMono.png"));
        Image singleIm = singlePic.getImage();
        Image singleIm2 = singleIm.getScaledInstance(100,60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon single = new ImageIcon(singleIm2);

        ImageIcon quadPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardQuad.png"));
        Image quadIm = quadPic.getImage();
        Image quadIm2 = quadIm.getScaledInstance(100,60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon quad = new ImageIcon(quadIm2);

        ImageIcon octPic = new ImageIcon(ClassLoader.getSystemResource("res/SimBoardOcto.png"));
        Image octIm = octPic.getImage();
        Image octIm2 = octIm.getScaledInstance(100,60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon oct = new ImageIcon(octIm2);

        JRadioButton generalBoard = new JRadioButton();
        generalBoard.setBackground(CustomColor.BUTTON);
        generalBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        generalBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        generalBoard.setIcon(single);

        JRadioButton quadBoard = new JRadioButton();
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        quadBoard.setIcon(quad);

        JRadioButton eightBoard = new JRadioButton();
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        eightBoard.setIcon(oct);

        JRadioButton quarButton = new JRadioButton("Quarantine Enabled");
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

        leftPanel.add(boardSelection);
        leftPanel.add(generalBoard);
        leftPanel.add(quadBoard);
        leftPanel.add(eightBoard);

        JPanel quarPanel = new JPanel(new GridLayout(1, 2));
        quarPanel.add(quarButton);
        quarPanel.add(regButton);

        leftPanel.add(quarPanel);

        mainPanel.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 1;

        JPanel rightPanel = new JPanel(new GridLayout(6, 4));
        rightPanel.setBackground(CustomColor.JET);

        JLabel paramLabel = new JLabel("Select the Parameters");

        JTextField delay = new JTextField(1);
        JTextField travelers = new JTextField(1);
        JTextField timeUntilQuarantine = new JTextField(1);
        JTextField percentQuarantine = new JTextField(1);
        JTextField asymptomaticChance = new JTextField(1);
        JTextField socialDistanceValue = new JTextField(1);
        JTextField percentSocialDist = new JTextField(1);
        JTextField minAge = new JTextField(1);
        JTextField maxAge = new JTextField(1);
        JTextField minConditions = new JTextField(1);
        JTextField maxConditions = new JTextField(1);

        JLabel delayLabel = new JLabel("Delay");
        JLabel travelersLabel = new JLabel("Travelers");
        JLabel timeQuarLabel = new JLabel("Time until Quarantine");
        JLabel percentQuarLabel = new JLabel("Percent Quarantine");
        JLabel asPercentLabel = new JLabel("Percent Asymptomatic");
        JLabel socDistValLabel = new JLabel("Social Distance");
        JLabel socialDisPercentLabel = new JLabel("Percent Social Distancing");
        JLabel minAgeLabel = new JLabel("Min Age");
        JLabel maxAgeLabel = new JLabel("Max Age");
        JLabel minCondLabel = new JLabel("Min Conditions");
        JLabel maxCondLabel = new JLabel("Max Conditions");

        rightPanel.add(delayLabel);
        rightPanel.add(delay);
        rightPanel.add(travelersLabel);
        rightPanel.add(travelers);

        rightPanel.add(timeQuarLabel);
        rightPanel.add(timeUntilQuarantine);
        rightPanel.add(percentQuarLabel);
        rightPanel.add(percentQuarantine);

        rightPanel.add(asPercentLabel);
        rightPanel.add(asymptomaticChance);
        rightPanel.add(socDistValLabel);
        rightPanel.add(socialDistanceValue);

        rightPanel.add(socialDisPercentLabel);
        rightPanel.add(percentSocialDist);
        rightPanel.add(minAgeLabel);
        rightPanel.add(minAge);

        rightPanel.add(maxAgeLabel);
        rightPanel.add(maxAge);
        rightPanel.add(minCondLabel);
        rightPanel.add(minConditions);
        rightPanel.add(maxCondLabel);
        regButton.add(maxConditions);

        mainPanel.add(rightPanel, gbcMain);


        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }
}
