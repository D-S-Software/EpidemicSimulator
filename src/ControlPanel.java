import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private JRadioButton choice1, choice2, choice3, custom;
    private JButton select, startStop, playPause, reset;
    private ButtonGroup g1;
    private JTextField contagiousRange, contagiousPercent, baseMortalityRate, baseMinTimeSick, baseMaxTimeSick, startPercentHealthy, numPeopleField;
    private JLabel contagiousRangeLabel, contagiousPercentLabel, baseMortalityRateLabel, baseMinTimeSickLabel, baseMaxTimeSickLabel, startPercentHealthyLabel, numPeopleLabel;
    private Disease disease;
    private Engine simEngine;
    boolean toPause = true, canStart = true;
    private GUI gui;
    private int baseXLen, baseYLen;

    public ControlPanel(GUI gui, int baseXLen, int baseYLen)
    {
        this.gui = gui;
        this.baseXLen = baseXLen;
        this.baseYLen = baseYLen;

        addSelectionPanel();
        addLabelPanel();
        addParamPanel();
        addButtonPanel();
        addImagePanel();
    }

    public void addSelectionPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = .5;
        gbc.weighty = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(5, 1));

        choice1 = new JRadioButton("Disease 1");
        choice2 = new JRadioButton("Disease 2");
        choice3 = new JRadioButton("Disease 3");
        custom = new JRadioButton("Custom");
        select = new JButton("Select");
        p.add(choice1);
        p.add(choice2);
        p.add(choice3);
        p.add(custom);
        p.add(select);
        g1 = new ButtonGroup();
        g1.add(choice1);
        g1.add(choice2);
        g1.add(choice3);
        g1.add(custom);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(Color.getHSBColor((1 - 1) / 9f, 0.75f, 0.95f));
        mainPanel.add(p, gbc);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(choice1.isSelected())
                {
                    disease = new Disease1();
                    contagiousPercent.setText(disease.getContagiousPercent() + "");
                    contagiousRange.setText("" + disease.getContagiousRange());
                    baseMortalityRate.setText(disease.getBaseMortalityRate() + "");
                    baseMinTimeSick.setText("" + disease.getBaseMinTimeSick());
                    baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick());
                    startPercentHealthy.setText(disease.getStartPercentHealthy() + "");
                }
                if(choice2.isSelected())
                {
                    disease = new Disease2();
                    contagiousPercent.setText(disease.getContagiousPercent() + "");
                    contagiousRange.setText("" + disease.getContagiousRange());
                    baseMortalityRate.setText(disease.getBaseMortalityRate() + "");
                    baseMinTimeSick.setText("" + disease.getBaseMinTimeSick());
                    baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick());
                    startPercentHealthy.setText(disease.getStartPercentHealthy() + "");
                }
                if(choice3.isSelected())
                {
                    disease = new Disease3();
                    contagiousPercent.setText(disease.getContagiousPercent() + "");
                    contagiousRange.setText("" + disease.getContagiousRange());
                    baseMortalityRate.setText(disease.getBaseMortalityRate() + "");
                    baseMinTimeSick.setText("" + disease.getBaseMinTimeSick());
                    baseMaxTimeSick.setText("" + disease.getBaseMaxTimeSick());
                    startPercentHealthy.setText(disease.getStartPercentHealthy() + "");
                }
                if(custom.isSelected())
                {
                    if(contagiousPercent.getText().equals("") || contagiousRange.getText().equals("")
                    || baseMortalityRate.getText().equals("") || baseMinTimeSick.getText().equals("")
                    || baseMaxTimeSick.getText().equals("") || startPercentHealthy.getText().equals(""))
                        JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters for Custom then hit 'Select'");
                    else
                    {
                        disease = new Disease(Integer.parseInt(contagiousRange.getText()), Double.parseDouble(contagiousPercent.getText()),
                                Double.parseDouble(baseMortalityRate.getText()), Integer.parseInt(baseMinTimeSick.getText()),
                                Integer.parseInt(baseMaxTimeSick.getText()), Double.parseDouble(startPercentHealthy.getText()));
                    }
                }
            }
        });
    }
    public void addImagePanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.add(new JLabel("[Update Image]"));
        p.setBackground(Color.getHSBColor((2 - 1) / 9f, 0.75f, 0.95f));
        mainPanel.add(p, gbc);
    }
    public void addLabelPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JLabel controls = new JLabel("Controls:");
        controls.setFont(controls.getFont ().deriveFont (24.0f));
        p.add(controls);
        p.setBackground(Color.getHSBColor((3 - 1) / 9f, 0.75f, 0.95f));
        mainPanel.add(p, gbc);
    }
    public void addParamPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(2, 6));

        contagiousPercent = new JTextField(1);
        contagiousRange = new JTextField(1);
        baseMortalityRate = new JTextField(1);
        baseMinTimeSick = new JTextField(1);
        baseMaxTimeSick = new JTextField(1);
        startPercentHealthy = new JTextField(1);

        contagiousPercentLabel = new JLabel("contagiousPercent");
        //contagiousPercentLabel.setPreferredSize(new Dimension(110,10));
        contagiousRangeLabel = new JLabel("contagiousRange");
        //contagiousRangeLabel.setPreferredSize(new Dimension(110,10));
        baseMortalityRateLabel = new JLabel("  baseMortalityRate");
        //baseMortalityRateLabel.setPreferredSize(new Dimension(110,10));
        baseMinTimeSickLabel = new JLabel("  baseMinTimeSick");
        //baseMinTimeSickLabel.setPreferredSize(new Dimension(110,10));
        baseMaxTimeSickLabel = new JLabel("  baseMaxTimeSick");
        //baseMaxTimeSickLabel.setPreferredSize(new Dimension(110,10));
        startPercentHealthyLabel = new JLabel("  startPercentHealthy");
        //startPercentHealthyLabel.setPreferredSize(new Dimension(110,10));

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
        p.setBackground(Color.getHSBColor((4 - 1) / 9f, 0.75f, 0.95f));
        mainPanel.add(p, gbc);
    }
    public void addButtonPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        JPanel p = new JPanel(new GridLayout(1, 5));

        numPeopleLabel = new JLabel("NumPeople: ");
        p.add(numPeopleLabel);

        numPeopleField = new JTextField(1);
        p.add(numPeopleField);

        startStop = new JButton("Start");
        startStop.setPreferredSize(new Dimension(100,30));

        startStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numPeople;
                if("".equals(numPeopleField.getText()))
                    numPeople = 0;
                else
                    numPeople = Integer.parseInt(numPeopleField.getText());

                if(canStart)
                {
                    gui.getBoardPanel().setReset(false);
                    simEngine = new Engine(gui, disease, numPeople, baseXLen, baseYLen);
                    simEngine.getClock().start();
                    canStart = false;
                }
            }
        });

        playPause = new JButton("Play/Pause");
        playPause.setPreferredSize(new Dimension(100,30));

        playPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toPause)
                {
                    simEngine.getClock().stop();
                    toPause = false;
                }
                else
                {
                    simEngine.getClock().start();
                    toPause = true;
                }
            }
        });

        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100,30));

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                simEngine.getClock().stop();
                gui.setNumHealthyLabel("NumHealthy: ");
                gui.setNumSickLabel("NumSick: ");
                gui.setNumRecoveredLabel("NumRecovered: ");
                gui.setNumDeadLabel("NumDead: ");
                simEngine = null;
                canStart = true;
                gui.getBoardPanel().setReset(true);
                gui.getBoardPanel().repaint();
            }
        });

        p.add(startStop);
        p.add(playPause);
        p.add(reset);

        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        p.setBackground(Color.getHSBColor((5 - 1) / 9f, 0.75f, 0.95f));
        mainPanel.add(p, gbc);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getStartStop()
    {
        return startStop;
    }

    public JButton getPlayPause() {
        return playPause;
    }

    public JButton getReset() {
        return reset;
    }

    public JButton getSelect() {
        return select;
    }

    public Disease getDisease() {
        return disease;
    }
}
