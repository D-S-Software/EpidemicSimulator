package gui;

import lib.CustomColor;

import javax.swing.*;
import java.awt.*;

public class TitleFrame extends JFrame {

    Formatter formatter;
    JPanel textPanel;
    JFrame frame;

    /**
     * Creates the TitleScreen window when the application is launched
     */
    public TitleFrame(JFrame frame){
        super();
        this.frame = frame;
        formatter = new Formatter();
        formatter.formatFrame(this, CustomColor.BACKGROUND, null, new GridLayout(), "corona.jpg");

        addTitleScreenPanel();

        pack();
        setLocationByPlatform(true);
        setLocation(0,0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Creates the Title Screen
     */
    private void addTitleScreenPanel()
    {
        textPanel = new JPanel();
        formatter.formatPanel(textPanel, CustomColor.BACKGROUND, null, new BoxLayout(textPanel,BoxLayout.Y_AXIS));
        textPanel.add(Box.createRigidArea(new Dimension(0, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4)));

        JLabel titleText = new JLabel();
        formatter.formatLabel(titleText, "TitleText.png", 0,0);
        textPanel.add(titleText);
        titleText.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(Box.createVerticalStrut((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4));

        JButton begin = new JButton();
        formatter.formatButton(begin,new Color(35,35,35), "StartPic.png");
        begin.addActionListener(e -> showSimulation());
        textPanel.add(begin);
        begin.setAlignmentX(Component.CENTER_ALIGNMENT);

        getContentPane().add(textPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    /**
     * Helper method for addTitleScreenPanel. Used to bring the user to the main frame.
     */
    private void showSimulation()
    {
        frame.setVisible(true);
        setVisible(false);
    }
}