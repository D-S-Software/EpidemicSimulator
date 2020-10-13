package gui;

import backend.Engine;
import backend.Music;
import backend.disease.Disease;
import lib.CustomColor;
import backend.disease.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements ActionListener{

    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private JButton start, playPause, reset, toggleMusic, toggleSocDist, slowDown, speedUp;
    private JTextField numPeopleField;
    private JLabel numPeopleLabel, numSimulationLabel;
    private Disease disease;
    private Engine simEngine;
    private GUI gui;
    private Timer checkTick;
    private Music backgroundMusic;
    private SettingFrame settingFrame;
    private ArrayList<Music> musicSongs = new ArrayList<>();
    private ControlPanel controlPanel = this;

    private int boardType, minPreExistingConditions, maxPreExistingConditions, numPeople, numStatsFile = 0, diseaseSel = 5, previousSong = 1;
    private double asymptomaticChance, socialDistanceChance, quarantineChance, travelersPer, socialDistanceValue, minAge, maxAge, timeUntilQuarantine, reinfectRate, antiBodyTime;
    private boolean toPause = true, canStart = true, musicPlaying = true, isPlaying = false, isSocialDist, quarBoard;

    /**Creates a control Panel object for the gui
     *
     * @param gui The gui object needed to create the engine object when the user starts the simulation
     */
    public ControlPanel(GUI gui)
    {
        this.gui = gui;

        settingFrame = new SettingFrame(this);
        settingFrame.setVisible(false);

        addSimSettingPanel();
        addButtonPanel();
        addInfoPanel();

        musicSongs.add(new Music("BlackOps.wav"));
        musicSongs.add(new Music("BreakingBad.wav"));
        musicSongs.add(new Music("Ceta (Rimworld OST).wav"));
        musicSongs.add(new Music("I Like It Here (Rimworld OST).wav"));
        musicSongs.add(new Music("Moving On (Rimworld OST).wav"));
        musicSongs.add(new Music("Night And Day (Rimworld OST).wav"));
        musicSongs.add(new Music("Riding Out (Rimworld OST).wav"));
        musicSongs.add(new Music("Rough Trail (Rimworld OST).wav"));
        musicSongs.add(new Music("Tribal Assembly (Rimworld OST).wav"));

        changeSong();

        checkTick = new Timer(10, this);
        checkTick.start();
    }

    /**
     * Changes the current background song
     */
    private void changeSong()
    {
        int randomIndex = (int)(musicSongs.size()*Math.random());
        if(randomIndex == previousSong)
            changeSong();
        else
        {
            previousSong = randomIndex;
            backgroundMusic = musicSongs.get(randomIndex);
        }
    }

    /**
     * Creates and adds the panel for the info and toggle music buttons
     */
    private void addInfoPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 2));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);

        ImageIcon picInfo = new ImageIcon(ClassLoader.getSystemResource("res/info.png"));
        Image image = picInfo.getImage();
        JButton info = new JButton((new ImageIcon(image.getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH))));
        info.setBackground(CustomColor.BUTTON);
        info.setFont(info.getFont ().deriveFont (18.0f));
        info.setForeground(CustomColor.ON_BUTTON_LABEL);
        info.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InfoFrame infoFrame = new InfoFrame(controlPanel);
                simEngine.getClock().stop();
                backgroundMusic.pause();
            }
        });

        toggleMusic = new JButton("<html>Toggle<br/>Music</html>");
        toggleMusic.setBackground(CustomColor.BUTTON);
        toggleMusic.setFont(toggleMusic.getFont ().deriveFont (18.0f));
        toggleMusic.setForeground(CustomColor.ON_BUTTON_LABEL);
        toggleMusic.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        toggleMusic.setToolTipText("Click to pause / Double click to change song");

        toggleMusic.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2)
                {
                    backgroundMusic.stop();
                    changeSong();
                    backgroundMusic.play();
                }
                if(e.getClickCount()==1)
                {
                    if(musicPlaying)
                    {
                        backgroundMusic.pause();
                        toggleMusic.setBackground(CustomColor.DARK_RED);
                        musicPlaying = false;
                    }
                    else
                    {
                        backgroundMusic.resume();
                        toggleMusic.setBackground(CustomColor.BUTTON);
                        musicPlaying = true;
                    }
                }
            }
        });

        p.add(info);
        p.add(toggleMusic);
        mainPanel.add(p, gbc);
    }

    /**
     * Creates and adds the panel for social distancing, speed up and slow down, and the setting button
     */
    private void addSimSettingPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 4));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);

        toggleSocDist = new JButton("<html>Social<br/>Distancing</html>");
        toggleSocDist.setBackground(CustomColor.BUTTON);
        toggleSocDist.setFont(toggleSocDist.getFont ().deriveFont (18.0f));
        toggleSocDist.setForeground(CustomColor.ON_BUTTON_LABEL);
        toggleSocDist.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        toggleSocDist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isPlaying)
                {
                    if(isSocialDist)
                    {
                        gui.getSimBoardPanel().getSimBoard().toggleSocDist(false);
                        toggleSocDist.setToolTipText("No one is social distancing");
                        isSocialDist = false;
                        toggleSocDist.setBackground(CustomColor.BUTTON);
                    }
                    else
                    {
                        if(settingFrame.getSocialDistanceChanceNum() == 0.0)
                        {
                            gui.getSimBoardPanel().getSimBoard().everyoneSocialDistance();
                            toggleSocDist.setToolTipText("Everyone is social distancing");
                        }
                        else
                        {
                            gui.getSimBoardPanel().getSimBoard().toggleSocDist(true);
                            toggleSocDist.setToolTipText(settingFrame.getSocialDistanceChanceNum()*100 + " % of people are set social distancing");
                        }
                        isSocialDist = true;
                        toggleSocDist.setBackground(CustomColor.ARTICHOKE_GREEN);
                    }
                }
            }
        });

        ImageIcon pic = new ImageIcon(ClassLoader.getSystemResource("res/setttingsIcon.png"));
        Image image = pic.getImage();
        JButton settings = new JButton((new ImageIcon(image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH))));
        settings.setBackground(CustomColor.BUTTON);
        settings.setForeground(CustomColor.ON_BUTTON_LABEL);
        settings.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                settingFrame.setVisible(true);
                if(simEngine != null)
                    simEngine.getClock().stop();
                backgroundMusic.pause();
            }
        });

        speedUp = new JButton("Speed up");
        speedUp.setBackground(CustomColor.BUTTON);
        speedUp.setFont(speedUp.getFont ().deriveFont (18.0f));
        speedUp.setForeground(CustomColor.ON_BUTTON_LABEL);
        speedUp.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        speedUp.setToolTipText("Increase Speed");

        speedUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isPlaying)
                {
                    simEngine.speedUp();
                    slowDown.setToolTipText("Slow Down");
                    slowDown.setVisible(true);

                    if(simEngine.getDelay() == 0)
                    {
                        speedUp.setToolTipText("Max Speed");
                        speedUp.setVisible(false);
                    }
                }
            }
        });

        slowDown = new JButton("Slow Down");
        slowDown.setBackground(CustomColor.BUTTON);
        slowDown.setFont(slowDown.getFont ().deriveFont (18.0f));
        slowDown.setForeground(CustomColor.ON_BUTTON_LABEL);
        slowDown.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        slowDown.setToolTipText("Slow Down");

        slowDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isPlaying)
                {
                    simEngine.slowDown();
                    speedUp.setToolTipText("Increase Speed");
                    speedUp.setVisible(true);

                    if(simEngine.getDelay() == 19)
                    {
                        slowDown.setToolTipText("Min Speed");
                        slowDown.setVisible(false);
                    }
                }
            }
        });

        p.add(toggleSocDist);
        p.add(speedUp);
        p.add(slowDown);
        p.add(settings);

        mainPanel.add(p, gbc);
    }

    /**
     * Creates and adds the panel for the number of people text field and the start / pause / reset buttons
     */
    private void addButtonPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 5));

        numPeopleLabel = new JLabel("<html>Number<br/>of People: </html>");
        numPeopleLabel.setFont(numPeopleLabel.getFont().deriveFont(17.0f));
        numPeopleLabel.setForeground(CustomColor.ON_BLOOD_RED_LABEL);
        p.add(numPeopleLabel);

        numPeopleField = new JTextField(1);
        numPeopleField.setBackground(CustomColor.FIELD);
        numPeopleField.setFont(numPeopleField.getFont ().deriveFont (20.0f));
        numPeopleField.setForeground(CustomColor.ON_BUTTON_LABEL);
        numPeopleField.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        p.add(numPeopleField);

        start = new JButton("Start");
        start.setBackground(CustomColor.BUTTON);
        start.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        start.setFont(start.getFont ().deriveFont (18.0f));
        start.setForeground(CustomColor.ON_BUTTON_LABEL);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startSim();
            }
        });

        playPause = new JButton("Play/Pause");
        playPause.setBackground(CustomColor.BUTTON);
        playPause.setFont(playPause.getFont ().deriveFont (18.0f));
        playPause.setForeground(CustomColor.ON_BUTTON_LABEL);
        playPause.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        playPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(disease != null && !canStart)
                {
                    if(toPause)
                    {
                        simEngine.getClock().stop();
                        isPlaying = false;
                        backgroundMusic.pause();
                        musicPlaying = false;
                        playPause.setBackground(CustomColor.DARK_RED);
                        toPause = false;
                    }
                    else
                    {
                        simEngine.getClock().start();
                        isPlaying = true;
                        backgroundMusic.resume();
                        musicPlaying = true;
                        playPause.setBackground(CustomColor.BUTTON);
                        toPause = true;
                    }
                    gui.getTallyPanel().showGraphModeButton();
                }
            }
        });

        reset = new JButton("Reset");
        reset.setBackground(CustomColor.BUTTON);
        reset.setFont(reset.getFont ().deriveFont (18.0f));
        reset.setForeground(CustomColor.ON_BUTTON_LABEL);
        reset.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                simEngine.getClock().stop();
                isPlaying = false;

                gui.getTallyPanel().setNumHealthyLabel("NumHealthy: ");
                gui.getTallyPanel().setNumSickLabel("NumSick: ");
                gui.getTallyPanel().setNumRecoveredLabel("NumRecovered: ");
                gui.getTallyPanel().setNumDeadLabel("NumDead: ");

                disease = null;
                canStart = true;

                gui.getSimBoardPanel().setReset(true);

                gui.getSimBoardPanel().repaint();

                gui.getXYChartPanel().resetXY();
                gui.getXYChartPanel2().resetXY();
                gui.getPieChartPanel().resetPie();

                speedUp.setToolTipText("Increase Speed");
                slowDown.setToolTipText("Slow Down");

                gui.getStats().getCreateFile().closeFile();

                backgroundMusic.stop();
                toggleSocDist.setBackground(CustomColor.BUTTON);
                playPause.setBackground(CustomColor.BUTTON);
            }
        });

        numSimulationLabel = new JLabel("Simulation:   ");
        numSimulationLabel.setFont(numSimulationLabel.getFont ().deriveFont (16.0f));

        p.add(start);
        p.add(playPause);
        p.add(reset);
        p.add(numSimulationLabel);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);
        mainPanel.add(p, gbc);
    }

    /**
     * Sets the text for the disease parameter panel based on the disease parameters
     * @param e event each tick
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(settingFrame.getCustom().isSelected() && diseaseSel != 5)
        {
            settingFrame.getContagiousPercent().setText("");
            settingFrame.getContagiousRange().setText("");
            settingFrame.getBaseMortalityRate().setText("");
            settingFrame.getBaseMinTimeSick().setText("");
            settingFrame.getBaseMaxTimeSick().setText("");
            settingFrame.getStartPercentHealthy().setText("");
            diseaseSel = 5;
        }
        if(settingFrame.getChoice1().isSelected() && diseaseSel != 1)
        {
            disease = new Disease1();
            settingFrame.getContagiousPercent().setText(disease.getContagiousPercent() * 100 + "");
            settingFrame.getContagiousRange().setText("" + disease.getContagiousRange());
            settingFrame.getBaseMortalityRate().setText(disease.getBaseMortalityRate() * 100 + "");
            settingFrame.getBaseMinTimeSick().setText("" + disease.getBaseMinTimeSick() / 100);
            settingFrame.getBaseMaxTimeSick().setText("" + disease.getBaseMaxTimeSick() / 100);
            settingFrame.getStartPercentHealthy().setText(disease.getStartPercentHealthy() * 100 + "");
            diseaseSel = 1;
        }
        if(settingFrame.getChoice2().isSelected() && diseaseSel != 2)
        {
            disease = new Disease2();
            settingFrame.getContagiousPercent().setText(disease.getContagiousPercent() * 100 + "");
            settingFrame.getContagiousRange().setText("" + disease.getContagiousRange());
            settingFrame.getBaseMortalityRate().setText(disease.getBaseMortalityRate() * 100 + "");
            settingFrame.getBaseMinTimeSick().setText("" + disease.getBaseMinTimeSick() / 100);
            settingFrame.getBaseMaxTimeSick().setText("" + disease.getBaseMaxTimeSick() / 100);
            settingFrame.getStartPercentHealthy().setText(disease.getStartPercentHealthy() * 100 + "");
            diseaseSel = 2;
        }
        if(settingFrame.getChoice3().isSelected() && diseaseSel != 3)
        {
            disease = new Disease3();
            settingFrame.getContagiousPercent().setText(disease.getContagiousPercent() * 100 + "");
            settingFrame.getContagiousRange().setText("" + disease.getContagiousRange());
            settingFrame.getBaseMortalityRate().setText(disease.getBaseMortalityRate() * 100 + "");
            settingFrame.getBaseMinTimeSick().setText("" + disease.getBaseMinTimeSick() / 100);
            settingFrame.getBaseMaxTimeSick().setText("" + disease.getBaseMaxTimeSick() / 100);
            settingFrame.getStartPercentHealthy().setText(disease.getStartPercentHealthy() * 100 + "");
            diseaseSel = 3;
        }
        if(settingFrame.getChoice4().isSelected() && diseaseSel != 4)
        {
            disease = new Disease4();
            settingFrame.getContagiousPercent().setText(disease.getContagiousPercent() * 100 + "");
            settingFrame.getContagiousRange().setText("" + disease.getContagiousRange());
            settingFrame.getBaseMortalityRate().setText(disease.getBaseMortalityRate() * 100 + "");
            settingFrame.getBaseMinTimeSick().setText("" + disease.getBaseMinTimeSick() / 100);
            settingFrame.getBaseMaxTimeSick().setText("" + disease.getBaseMaxTimeSick() / 100);
            settingFrame.getStartPercentHealthy().setText(disease.getStartPercentHealthy() * 100 + "");
            diseaseSel = 4;
        }

        if(backgroundMusic.getClip().getMicrosecondPosition() == backgroundMusic.getClip().getMicrosecondLength()-1)
        {
            backgroundMusic.stop();
            changeSong();
            backgroundMusic.play();
        }
    }

    /**
     * Starts a simulation if possible (ie checks to make sure parameters are sufficient)
     */
    private void startSim()
    {
        if(canStart)
        {
            boolean goodToStart = true;
            if(settingFrame.isVisible())
            {
                JOptionPane.showMessageDialog(new JFrame(), "Finish filling in and closing the settings before starting the simulation.");
            }
            else
            {
                try
                {
                    disease = new Disease(Double.parseDouble(settingFrame.getContagiousRange().getText()), Double.parseDouble(settingFrame.getContagiousPercent().getText()) / 100,
                            Double.parseDouble(settingFrame.getBaseMortalityRate().getText()) / 100, Double.parseDouble(settingFrame.getBaseMinTimeSick().getText()) *100,
                            Double.parseDouble(settingFrame.getBaseMaxTimeSick().getText()) *100, Double.parseDouble(settingFrame.getStartPercentHealthy().getText()) / 100);
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters for Disease in settings are numbers and filled in correctly!");
                    goodToStart = false;
                }
                try
                {
                    numPeople = Integer.parseInt(numPeopleField.getText());
                    if(numPeople < 2)
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Please make sure the number of people is greater than 1.");
                        goodToStart = false;
                    }
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure the number of people is a positive integer greater than 1");
                    goodToStart = false;
                }
                try
                {
                    boardType = settingFrame.getBoardTypeNum();
                    socialDistanceValue = settingFrame.getSocialDistanceValueNum();
                    minAge = settingFrame.getMinAgeNum();
                    maxAge = settingFrame.getMaxAgeNum();
                    minPreExistingConditions = settingFrame.getMinPreExistingConditionsNum();
                    maxPreExistingConditions = settingFrame.getMaxPreExistingConditionsNum();
                    timeUntilQuarantine = settingFrame.getTimeUntilQuarantineNum();
                    asymptomaticChance = settingFrame.getAsymptomaticChanceNum();
                    socialDistanceChance = settingFrame.getSocialDistanceChanceNum();
                    quarantineChance = settingFrame.getQuarantineChanceNum();
                    travelersPer = settingFrame.getTravelersPer();
                    reinfectRate = settingFrame.getReinfectRateNum();
                    antiBodyTime = settingFrame.getAntiBodyTimeNum();
                    quarBoard = settingFrame.isQuarBoardBool();

                    gui.getSimBoardPanel().setReset(false);

                    if(disease != null && goodToStart)
                    {
                        numStatsFile++; //Used to make an additional results file in Stats class
                        numSimulationLabel.setText("Simulation: " + numStatsFile);
                        simEngine = new Engine(gui, disease, numPeople, boardType, quarBoard, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer, timeUntilQuarantine, quarantineChance, reinfectRate, antiBodyTime);
                        simEngine.getClock().start();
                        speedUp.setVisible(true);
                        slowDown.setVisible(true);
                        isPlaying = true;
                        if (settingFrame.getSocialDistanceChanceNum() > 0) {
                            isSocialDist = true;
                            toggleSocDist.setBackground(CustomColor.ARTICHOKE_GREEN);
                        } else isSocialDist = false;
                        canStart = false;

                        backgroundMusic.stop();
                        changeSong();
                        backgroundMusic.play();

                        toggleSocDist.setToolTipText(settingFrame.getSocialDistanceChanceNum() * 100 + " % of people are set social distancing");
                    }
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters are numbers and filled in correctly.");
                }
            }
        }
    }

    /**
     * Resumes a simulation after setting or info panel closes
     */
    public void resumeSim()
    {
        simEngine.getClock().start();
        backgroundMusic.resume();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public int getDelay()
    {
        return simEngine.getDelay();
    }
    public boolean isPlaying()
    {
        return isPlaying;
    }
    public int getNumStatsFile()
    {
        return numStatsFile;
    }
}