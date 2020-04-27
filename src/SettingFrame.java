import Library.CustomColor;

import javax.swing.*;
import java.awt.*;

public class SettingFrame extends JFrame {

    public SettingFrame()
    {
        JFrame settingsFrame = new JFrame();
        settingsFrame.getContentPane().setBackground(CustomColor.BACKGROUND);
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/corona.jpg"));
        Image image1 = pic1.getImage();
        settingsFrame.setIconImage(image1);
        settingsFrame.setPreferredSize(new Dimension(400, 200));
        settingsFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 0;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(2, 2, 2, 2);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        leftPanel.setBackground(CustomColor.JET);

        JLabel boardSelection = new JLabel("Select Board");

        JRadioButton generalBoard = new JRadioButton("Single Board");
        generalBoard.setBackground(CustomColor.BUTTON);
        generalBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        generalBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        JRadioButton quadBoard = new JRadioButton("Quad Board");
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        JRadioButton eightBoard = new JRadioButton("Octo Board");
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

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

        mainPanel.add(leftPanel);

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 2;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 0;
        gbcMain.weighty = 1;

        JPanel rightPanel = new JPanel(new GridLayout(6, 4));





        settingsFrame.pack();
        settingsFrame.setLocationByPlatform(true);
        settingsFrame.setVisible(true);
    }

}
