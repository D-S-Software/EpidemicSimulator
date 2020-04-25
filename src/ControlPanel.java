import Library.CustomColor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener{

    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private JRadioButton choice1, choice2, choice3, choice4, custom;
    private JButton start, playPause, reset, toggleMusic;
    private ButtonGroup g1;
    private JTextField contagiousRange, contagiousPercent, baseMortalityRate, baseMinTimeSick, baseMaxTimeSick, startPercentHealthy, numPeopleField;
    private JLabel contagiousRangeLabel, contagiousPercentLabel, baseMortalityRateLabel, baseMinTimeSickLabel, baseMaxTimeSickLabel, startPercentHealthyLabel, numPeopleLabel;
    private Disease disease;
    private Engine simEngine;
    boolean toPause = true, canStart = true, canType = true, musicPlaying = true;
    private GUI gui;
    Timer checkTick;
    Music backgroundMusic = new Music("BlackOps.wav");

    public ControlPanel(GUI gui)
    {
        this.gui = gui;

        addSelectionPanel();
        addSimSettingPanel();
        addParamPanel();
        addButtonPanel();
        addTitleControlPanel();

        checkTick = new Timer(10, this);
        checkTick.start();
    }

    public void addSelectionPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = .1;
        gbc.weighty = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(5, 2));

        choice1 = new JRadioButton("Disease 1   ");
        choice1.setFont(choice1.getFont ().deriveFont (16.0f));
        choice1.setBackground(CustomColor.BUTTON);
        choice1.setForeground(CustomColor.ON_BUTTON_LABEL);
        choice1.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice2 = new JRadioButton("Disease 2   ");
        choice2.setFont(choice2.getFont ().deriveFont (16.0f));
        choice2.setBackground(CustomColor.BUTTON);
        choice2.setForeground(CustomColor.ON_BUTTON_LABEL);
        choice2.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice3 = new JRadioButton("Disease 3   ");
        choice3.setFont(choice3.getFont ().deriveFont (16.0f));
        choice3.setBackground(CustomColor.BUTTON);
        choice3.setForeground(CustomColor.ON_BUTTON_LABEL);
        choice3.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice4 = new JRadioButton("Disease 4   ");
        choice4.setFont(choice4.getFont ().deriveFont (16.0f));
        choice4.setBackground(CustomColor.BUTTON);
        choice4.setForeground(CustomColor.ON_BUTTON_LABEL);
        choice4.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        custom = new JRadioButton("Custom");
        custom.setFont(custom.getFont ().deriveFont (16.0f));
        custom.setBackground(CustomColor.BUTTON);
        custom.setForeground(CustomColor.ON_BUTTON_LABEL);
        custom.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        g1 = new ButtonGroup();
        g1.add(choice1);
        g1.add(choice2);
        g1.add(choice3);
        g1.add(choice4);
        g1.add(custom);

        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/corona.jpg"));
        Image image1 = pic1.getImage();
        Image image11 = image1.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon edit1 = new ImageIcon(image11);

        ImageIcon pic2 = new ImageIcon(ClassLoader.getSystemResource("res/bacteria1.jpg"));
        Image image2 = pic2.getImage();
        Image image22 = image2.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon edit2 = new ImageIcon(image22);

        ImageIcon pic3 = new ImageIcon(ClassLoader.getSystemResource("res/virus4.png"));
        Image image3 = pic3.getImage();
        Image image33 = image3.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon edit3 = new ImageIcon(image33);

        ImageIcon pic4 = new ImageIcon(ClassLoader.getSystemResource("res/virus3.jpg"));
        Image image4 = pic4.getImage();
        Image image44 = image4.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon edit4 = new ImageIcon(image44);

        JLabel pic1Label = new JLabel();
        JLabel pic2Label = new JLabel();
        JLabel pic3Label = new JLabel();
        JLabel pic4Label = new JLabel();
        pic1Label.setIcon(edit1);
        pic2Label.setIcon(edit2);
        pic3Label.setIcon(edit3);
        pic4Label.setIcon(edit4);

        p.add(choice1);
        p.add(pic1Label);
        p.add(choice2);
        p.add(pic2Label);
        p.add(choice3);
        p.add(pic3Label);
        p.add(choice4);
        p.add(pic4Label);
        p.add(custom);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.KOBICHA);
        mainPanel.add(p, gbc);
    }
    public void addTitleControlPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);
        JLabel controls = new JLabel("Controls:");
        controls.setFont(controls.getFont ().deriveFont (24.0f));
        controls.setForeground(CustomColor.ON_BLOOD_RED_LABEL);
        p.add(controls);
        mainPanel.add(p, gbc);
    }
    public void addSimSettingPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 3));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);

        toggleMusic = new JButton("Toggle Music");
        toggleMusic.setBackground(CustomColor.BUTTON);
        toggleMusic.setFont(toggleMusic.getFont ().deriveFont (18.0f));
        toggleMusic.setForeground(CustomColor.ON_BUTTON_LABEL);
        toggleMusic.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        toggleMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(musicPlaying)
                {
                    backgroundMusic.pause();
                    musicPlaying = false;
                }
                else
                {
                    backgroundMusic.resume();
                    musicPlaying = true;
                }
            }
        });

        //ImageIcon pic = new ImageIcon(ClassLoader.getSystemResource("res/SettingsIcon.jpg"));
        //Image image = pic.getImage();
        //JButton settings = new JButton((new ImageIcon(image.getScaledInstance(120,120, java.awt.Image.SCALE_SMOOTH))));
        //JButton settings = new JButton(pic);
        //p.add(settings);

        JLabel filler1 = new JLabel();
        JLabel filler2 = new JLabel();

        p.add(toggleMusic);
        p.add(filler1);
        p.add(filler2);

        mainPanel.add(p, gbc);
    }
    public void addParamPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = .1;
        gbc.weighty = 50;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(2, 6));

        contagiousPercent = new JTextField(1);
        contagiousPercent.setFont(contagiousPercent.getFont ().deriveFont (18.0f));
        contagiousPercent.setBackground(CustomColor.FIELD);
        contagiousPercent.setForeground(CustomColor.ON_BUTTON_LABEL);
        contagiousPercent.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        contagiousRange = new JTextField(1);
        contagiousRange.setFont(contagiousRange.getFont ().deriveFont (18.0f));
        contagiousRange.setBackground(CustomColor.FIELD);
        contagiousRange.setForeground(CustomColor.ON_BUTTON_LABEL);
        contagiousRange.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        baseMortalityRate = new JTextField(1);
        baseMortalityRate.setFont(baseMortalityRate.getFont ().deriveFont (18.0f));
        baseMortalityRate.setBackground(CustomColor.FIELD);
        baseMortalityRate.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMortalityRate.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        baseMinTimeSick = new JTextField(1);
        baseMinTimeSick.setFont(baseMinTimeSick.getFont ().deriveFont (18.0f));
        baseMinTimeSick.setBackground(CustomColor.FIELD);
        baseMinTimeSick.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMinTimeSick.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        baseMaxTimeSick = new JTextField(1);
        baseMaxTimeSick.setFont(baseMaxTimeSick.getFont ().deriveFont (18.0f));
        baseMaxTimeSick.setBackground(CustomColor.FIELD);
        baseMaxTimeSick.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMaxTimeSick.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        startPercentHealthy = new JTextField(1);
        startPercentHealthy.setFont(startPercentHealthy.getFont ().deriveFont (18.0f));
        startPercentHealthy.setBackground(CustomColor.FIELD);
        startPercentHealthy.setForeground(CustomColor.ON_BUTTON_LABEL);
        startPercentHealthy.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));

        contagiousPercentLabel = new JLabel("Contagious (%)");
        contagiousPercentLabel.setFont(contagiousPercentLabel.getFont ().deriveFont (15.0f));
        contagiousPercentLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        contagiousRangeLabel = new JLabel("Contagious Range");
        contagiousRangeLabel.setFont(contagiousRangeLabel.getFont ().deriveFont (15.0f));
        contagiousRangeLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        baseMortalityRateLabel = new JLabel("  Mortality Rate (%)");
        baseMortalityRateLabel.setFont(baseMortalityRateLabel.getFont ().deriveFont (15.0f));
        baseMortalityRateLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        baseMinTimeSickLabel = new JLabel("  Min Time Sick (s)");
        baseMinTimeSickLabel.setFont( baseMinTimeSickLabel.getFont ().deriveFont (15.0f));
        baseMinTimeSickLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        baseMaxTimeSickLabel = new JLabel("  Max Time Sick (s)");
        baseMaxTimeSickLabel.setFont(baseMaxTimeSickLabel.getFont ().deriveFont (15.0f));
        baseMaxTimeSickLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        startPercentHealthyLabel = new JLabel("  Start Healthy (%)");
        startPercentHealthyLabel.setFont(startPercentHealthyLabel.getFont ().deriveFont (15.0f));
        startPercentHealthyLabel.setForeground(CustomColor.ON_BUTTON_LABEL);

        p.add(contagiousPercentLabel);
        p.add(contagiousPercent);
        p.add(baseMinTimeSickLabel);
        p.add(baseMinTimeSick);
        p.add(baseMortalityRateLabel);
        p.add(baseMortalityRate);
        p.add(contagiousRangeLabel);
        p.add(contagiousRange);
        p.add(baseMaxTimeSickLabel);
        p.add(baseMaxTimeSick);
        p.add(startPercentHealthyLabel);
        p.add(startPercentHealthy);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.KOBICHA);
        mainPanel.add(p, gbc);
    }
    public void addButtonPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 50;
        gbc.weighty = .1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 5));

        numPeopleLabel = new JLabel("Number of People:  ");
        numPeopleLabel.setFont(numPeopleLabel.getFont ().deriveFont (20.0f));
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

                if(canStart)
                {
                    if(custom.isSelected())
                    {
                        if(contagiousPercent.getText().equals("") || contagiousRange.getText().equals("")
                                || baseMortalityRate.getText().equals("") || baseMinTimeSick.getText().equals("")
                                || baseMaxTimeSick.getText().equals("") || startPercentHealthy.getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters for Custom before starting!");
                            disease = null;
                        }
                        else
                        {
                            disease = new Disease(Integer.parseInt(contagiousRange.getText()), Double.parseDouble(contagiousPercent.getText()) / 100,
                                    Double.parseDouble(baseMortalityRate.getText()) / 100, Integer.parseInt(baseMinTimeSick.getText()) /100,
                                    Integer.parseInt(baseMaxTimeSick.getText()) /100, Double.parseDouble(startPercentHealthy.getText()) / 100);
                        }
                    }
                    if(disease != null)
                    {
                        int numPeople;
                        if("".equals(numPeopleField.getText()))
                            numPeople = 0;
                        else
                            numPeople = Integer.parseInt(numPeopleField.getText());

                        gui.getBoardPanel().setReset(false);
                        simEngine = new Engine(gui, disease, numPeople);
                        simEngine.getClock().start();
                        canStart = false;
                        checkTick.stop();
                        gui.getTallyPanel().showGraphModeButton();
                        backgroundMusic.loop();
                    }
                }
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
                        backgroundMusic.pause();
                        musicPlaying = false;
                        toPause = false;
                    }
                    else
                    {
                        simEngine.getClock().start();
                        backgroundMusic.resume();
                        musicPlaying = true;
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

                gui.getTallyPanel().setNumHealthyLabel("NumHealthy: ");
                gui.getTallyPanel().setNumSickLabel("NumSick: ");
                gui.getTallyPanel().setNumRecoveredLabel("NumRecovered: ");
                gui.getTallyPanel().setNumDeadLabel("NumDead: ");

                disease = null;
                canStart = true;

                gui.getBoardPanel().setReset(true);

                gui.getBoardPanel().repaint();

                gui.getXYChartPanel().resetXY();
                gui.getXYChartPanel2().resetXY();
                gui.getPieChartPanel().resetPie();

                checkTick.start();
                backgroundMusic.stop();
            }
        });

        p.add(start);
        p.add(playPause);
        p.add(reset);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(CustomColor.BLOOD_RED);
        mainPanel.add(p, gbc);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(custom.isSelected() && canType)
        {
            contagiousPercent.setText("");
            contagiousRange.setText("");
            baseMortalityRate.setText("");
            baseMinTimeSick.setText("");
            baseMaxTimeSick.setText("");
            startPercentHealthy.setText("");
            canType = false;
        }
        if(choice1.isSelected())
        {
            disease = new Disease1();
            contagiousPercent.setText(disease.getContagiousPercent() * 100 + "");
            contagiousRange.setText("" + disease.getContagiousRange());
            baseMortalityRate.setText(disease.getBaseMortalityRate() * 100 + "");
            baseMinTimeSick.setText("" + disease.getBaseMinTimeSick() / 100);
            baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick() / 100);
            startPercentHealthy.setText(disease.getStartPercentHealthy() * 100 + "");
            canType = true;
        }
        if(choice2.isSelected())
        {
            disease = new Disease2();
            contagiousPercent.setText(disease.getContagiousPercent() * 100 + "");
            contagiousRange.setText("" + disease.getContagiousRange());
            baseMortalityRate.setText(disease.getBaseMortalityRate() * 100 + "");
            baseMinTimeSick.setText("" + disease.getBaseMinTimeSick() / 100);
            baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick() / 100);
            startPercentHealthy.setText(disease.getStartPercentHealthy() * 100 + "");
            canType = true;
        }
        if(choice3.isSelected())
        {
            disease = new Disease3();
            contagiousPercent.setText(disease.getContagiousPercent() * 100 + "");
            contagiousRange.setText("" + disease.getContagiousRange());
            baseMortalityRate.setText(disease.getBaseMortalityRate() * 100 + "");
            baseMinTimeSick.setText("" + disease.getBaseMinTimeSick() / 100);
            baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick() / 100);
            startPercentHealthy.setText(disease.getStartPercentHealthy() * 100 + "");
            canType = true;
        }
        if(choice4.isSelected())
        {
            disease = new Disease4();
            contagiousPercent.setText(disease.getContagiousPercent() * 100 + "");
            contagiousRange.setText("" + disease.getContagiousRange());
            baseMortalityRate.setText(disease.getBaseMortalityRate() * 100 + "");
            baseMinTimeSick.setText("" + disease.getBaseMinTimeSick() / 100);
            baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick() / 100);
            startPercentHealthy.setText(disease.getStartPercentHealthy() * 100 + "");
            canType = true;
        }
    }
}
