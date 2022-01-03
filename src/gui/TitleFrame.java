package gui;

import backend.Music;
import lib.CustomColor;
import javax.swing.*;
import java.awt.*;

public class TitleFrame extends JFrame {

    Formatter formatter;
    JPanel textPanel;
    JFrame frame;
    JButton begin;
    boolean canStart = false;

    /**
     * Creates the TitleScreen window when the application is launched
     */
    public TitleFrame(JFrame frame){
        super();
        this.frame = frame;
        formatter = new Formatter();
        formatter.formatFrame(this, CustomColor.BACKGROUND, null, new GridLayout(), "virus1Logo.png");

        addTitleScreenPanel();

        Music.changeSong();
        Music.play();

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

        begin = new JButton();
        formatter.formatButton(begin,new Color(35,35,35), "startPic.png");
        begin.addActionListener(e -> showSimulation());
        begin.setVisible(false);
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
        if(canStart)
        {
            frame.setVisible(true);
            setVisible(false);
        }
    }

    /**
     * Gui can let the title screen know that everything is finished loading and the simulation can start
     */
    public void setCanStart()
    {
        canStart = true;
        begin.setVisible(true);
    }
}