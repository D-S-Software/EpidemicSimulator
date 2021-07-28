package gui;

import backend.Engine;
import backend.Music;
import backend.disease.Disease;
import lib.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements ActionListener {

    private JPanel mainPanel;
    private JButton startPause, reset, toggleMusic, toggleSocDist, slowDown, speedUp, toggleCenters;
    private JTextField numPeopleField;
    private JLabel numPeopleLabel, numSimulationLabel, filler;
    private Disease disease;
    private Engine simEngine;
    private GUI gui;
    private Timer checkTick;
    private Music backgroundMusic;
    private SettingFrame settingFrame;
    private ArrayList<Music> musicSongs = new ArrayList<>();
    private InfoFrame infoFrame;
    private ControlPanel controlPanel = this;
    private Formatter formatter;
    GridBagConstraints gbc;

    private int boardType, minPreExistingConditions, maxPreExistingConditions, numPeople, numStatsFile = 0, previousSong = 1;
    private double asymptomaticChance, socialDistanceChance, quarantineChance, travelersPer, socialDistanceValue, minAge, maxAge, timeUntilQuarantine, reinfectRate, antiBodyTime;
    private boolean toPause = true, canStart = true, musicPlaying = true, isPlaying = false, isSocialDist, quarBoard, toggleMoving;

    /**Creates a control Panel object for the gui
     *
     * @param gui The gui object needed to create the engine object when the user starts the simulation
     */
    public ControlPanel(GUI gui)
    {
        this.gui = gui;
        formatter = new Formatter();
        gbc = new GridBagConstraints();
        settingFrame = new SettingFrame(this);

        mainPanel = new JPanel(new GridBagLayout());
        formatter.formatPanel(mainPanel, CustomColor.BACKGROUND, new Rectangle(8,8,8,8), null);

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
        formatter.setGBC(gbc, 0,0,1,1,10,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        JPanel p = new JPanel();
        formatter.formatPanel(p, CustomColor.BLOOD_RED, new Rectangle(8,8,8,8), new GridLayout(1,2));

        JButton info = new JButton();
        formatter.formatButton(info, CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"infoIcon.png", null);
        info.addActionListener(e -> infoButton());

        toggleMusic = new JButton();
        formatter.formatButton(toggleMusic,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"musicIcon.png","Click to pause / Double click to change song");
        toggleMusic.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2)
                {
                    backgroundMusic.stop();
                    changeSong();
                    toggleMusic.setBackground(CustomColor.BUTTON);
                    musicPlaying = true; //Makes sure that the first click does not stop the music
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
        formatter.setGBC(gbc,1,0,1,1,50,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));

        JPanel p = new JPanel();
        formatter.formatPanel(p,CustomColor.BLOOD_RED,new Rectangle(8,8,8,8), new GridLayout(1,5));

        toggleSocDist = new JButton();
        formatter.formatButton(toggleSocDist,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"socialDistanceIcon.png", null);
        toggleSocDist.addActionListener(e -> socialDistButton());

        toggleCenters = new JButton();
        formatter.formatButton(toggleCenters,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"centersIcon.png","Toggle travel to center");
        toggleCenters.addActionListener(e -> toggleCentersButton());

        JButton settings = new JButton();
        formatter.formatButton(settings,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"settingsicon.png", null);
        settings.addActionListener(e -> settingButton());

        speedUp = new JButton();
        formatter.formatButton(speedUp,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"speedUpIcon.png","Increase Speed");
        speedUp.addActionListener(e -> speedUpButton());

        slowDown = new JButton();
        formatter.formatButton(slowDown, CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"slowDownIcon.png","Slow Down");
        slowDown.addActionListener(e -> slowDownButton());

        p.add(toggleSocDist);
        p.add(toggleCenters);
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
        formatter.setGBC(gbc,0,1,2,1,50,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        JPanel p = new JPanel();
        formatter.formatPanel(p,CustomColor.BLOOD_RED,new Rectangle(8,8,8,8),new GridLayout(1,5));

        numPeopleLabel = new JLabel("<html>Number<br/>of People: </html>");
        formatter.formatLabel(numPeopleLabel,CustomColor.SILVER,17.0f,null);
        p.add(numPeopleLabel);

        numPeopleField = new JTextField(1);
        formatter.formatTextField(numPeopleField,CustomColor.FIELD, CustomColor.SILVER,CustomColor.ON_BUTTON_LABEL, 20.0f, null,null);
        p.add(numPeopleField);

        startPause = new JButton();
        formatter.formatButton(startPause, CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"playIcon.png",null);
        startPause.addActionListener(e -> startSim());

        reset = new JButton();
        formatter.formatButton(reset,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"resetIcon.png",null);
        reset.addActionListener(e -> resetButton());

        numSimulationLabel = new JLabel("   Simulation:   ");
        formatter.formatLabel(numSimulationLabel,CustomColor.SILVER,16.0f,null);

        filler = new JLabel();
        formatter.formatLabel(filler,CustomColor.SILVER,16.0f,null);

        p.add(startPause);
        p.add(reset);
        p.add(filler);
        p.add(numSimulationLabel);

        mainPanel.add(p, gbc);
    }

    /**
     * Starts a simulation if possible (ie checks to make sure parameters are sufficient)
     */
    public void startSim()
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
                    disease = new Disease(Integer.parseInt(settingFrame.getContagiousRange().getText()), Integer.parseInt(settingFrame.getContagiousPercent().getText()),
                            Integer.parseInt(settingFrame.getBaseMortalityRate().getText()), Integer.parseInt(settingFrame.getBaseMinTimeSick().getText()) *100,
                            Integer.parseInt(settingFrame.getBaseMaxTimeSick().getText()) *100, Integer.parseInt(settingFrame.getStartPercentHealthy().getText()));
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters for Disease in settings are filled in correctly!");
                    goodToStart = false;
                }
                try
                {
                    numPeople = Integer.parseInt(numPeopleField.getText());
                    if(numPeople < 2 || numPeople > 5000)
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Please make sure the number of people is between 2 and 5000.");
                        goodToStart = false;
                    }
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure the number of people is a positive integer and greater than 1 and less than 5000");
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
                        numSimulationLabel.setText("   Simulation: " + numStatsFile);
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
                        if(musicPlaying)
                            backgroundMusic.play();

                        toggleSocDist.setToolTipText(settingFrame.getSocialDistanceChanceNum() * 100 + " % of people are set social distancing");
                        formatter.formatButton(startPause,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"pauseIcon.png",null);

                        gui.getFrame().requestFocusInWindow();
                    }
                }
                catch (java.lang.NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters are filled in correctly.");
                }
            }
        }
        else
            playPauseButton();
    }

    /**
     * Method checks if song is finished and plays another one
     * @param e event tick
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(backgroundMusic.getClip().getMicrosecondPosition() == backgroundMusic.getClip().getMicrosecondLength()-1)
        {
            backgroundMusic.stop();
            changeSong();
            backgroundMusic.play();
        }
    }

    /**
     * Helper method for resuming a simulation after setting or info panel closes
     */
    public void resumeSim()
    {
        simEngine.getClock().start();
        if(musicPlaying)
            backgroundMusic.resume();
    }

    /**
     * Helper method for opening the info screen
     */
    public void infoButton(){
        infoFrame = new InfoFrame(controlPanel);
        if(simEngine != null)
            simEngine.getClock().stop();
        backgroundMusic.pause();
    }

    /**
     * Helper method for toggling social distance feature
     */
    public void socialDistButton()
    {
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

    /**
     * Helper method for toggling move to center feature
     */
    public void toggleCentersButton()
    {
        if(isPlaying)
        {
            if(toggleMoving)
            {
                gui.getSimBoardPanel().getSimBoard().toggleMovingTarget(false);
                toggleCenters.setToolTipText("Toggle travel to center");
                toggleMoving = false;
                toggleCenters.setBackground(CustomColor.BUTTON);
            }
            else
            {
                gui.getSimBoardPanel().getSimBoard().toggleMovingTarget(true);
                toggleCenters.setToolTipText("Stop travel to center");
                toggleMoving = true;
                toggleCenters.setBackground(CustomColor.ARTICHOKE_GREEN);
            }
        }
    }

    /**
     * Helper method for opening the settings window
     */
    public void settingButton(){
        settingFrame.setVisible(true);
        if(simEngine != null)
            simEngine.getClock().stop();
        backgroundMusic.pause();
    }

    /**
     * Helper method for speeding up the simulation
     */
    public void speedUpButton(){
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

    /**
     * Helper method for slowing down the simulation
     */
    public void slowDownButton(){
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

    /**
     * Helper method for starting the simulation / pausing it
     */
    public void playPauseButton(){
        if(disease != null && !canStart)
        {
            if(toPause)
            {
                simEngine.getClock().stop();
                isPlaying = false;
                backgroundMusic.pause();
                musicPlaying = false;
                formatter.formatButton(startPause,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"playIcon.png",null);
                toPause = false;
            }
            else
            {
                simEngine.getClock().start();
                isPlaying = true;
                backgroundMusic.resume();
                musicPlaying = true;
                formatter.formatButton(startPause,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"pauseIcon.png",null);
                toPause = true;
            }
            gui.getTallyPanel().showGraphModeButton();
        }
    }

    /**
     * Helper method for resetting the simulation
     */
    public void resetButton()
    {
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
        toggleCenters.setToolTipText("Toggle travel to center");

        gui.getStats().getCreateFile().closeFile();

        backgroundMusic.stop();
        toggleSocDist.setBackground(CustomColor.BUTTON);
        formatter.formatButton(startPause,CustomColor.BUTTON,CustomColor.ON_BUTTON_LABEL,"playIcon.png",null);
        toggleCenters.setBackground(CustomColor.BUTTON);
    }

    /** Getter and Setter Methods */

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