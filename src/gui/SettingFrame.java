package gui;

import backend.disease.Disease;
import backend.disease.Disease1;
import backend.disease.Disease2;
import backend.disease.Disease3;
import backend.disease.Disease4;
import lib.CustomColor;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingFrame extends JFrame implements ActionListener{

    private int boardTypeNum = 1, minPreExistingConditionsNum, maxPreExistingConditionsNum, diseaseSel;
    private double asymptomaticChanceNum, socialDistanceChanceNum, quarantineChanceNum, travelersPer, socialDistanceValueNum, minAgeNum, maxAgeNum, timeUntilQuarantineNum, reinfectRateNum, antiBodyTimeNum;
    private boolean quarBoardBool = false;
    private JRadioButton choice1, choice2, choice3, choice4, custom;
    private ButtonGroup g1;
    private JTextField contagiousRange, contagiousPercent, baseMortalityRate, baseMinTimeSick, baseMaxTimeSick, startPercentHealthy;
    private JLabel contagiousRangeLabel, contagiousPercentLabel, baseMortalityRateLabel, baseMinTimeSickLabel, baseMaxTimeSickLabel, startPercentHealthyLabel;
    private Disease disease;

    JTextField travelers, timeUntilQuarantine, percentQuarantine, asymptomaticChance, socialDistanceValue, percentSocialDist, minAge, maxAge, minConditions, maxConditions, reinfectRate, antiBodyTime;

    JRadioButton generalBoard, quadBoard, eightBoard, quarButton, regButton;

    GridBagConstraints gbcMain = new GridBagConstraints();
    JPanel centerPanel, mainPanel = new JPanel(new GridBagLayout());
    JMenuBar mb;
    JPanel p;
    ControlPanel controlPanel;
    int pX, pY;

    /**
     * Creates a setting frame for the parameters of the simulation when the gear button is clicked
     * @param controlPanel The controlPanel obj used to pause the simulation when the settings panel is open
     */
    public SettingFrame(ControlPanel controlPanel)
    {
        setBackground(CustomColor.BACKGROUND);
        setPreferredSize(new Dimension(1120, 680));
        getContentPane().setBackground(CustomColor.BACKGROUND);
        this.controlPanel = controlPanel;

        mainPanel.setBackground(CustomColor.BACKGROUND);

        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){
            //Welp :P
        }

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
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;

        addCenterPanel();

        gbcMain.gridx = 2;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 1;

        addRightPanel();

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.gridwidth = 3;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 10;
        gbcMain.weighty = 2;

        addBottomPanel();

        add(mainPanel);

        Timer checkOpen = new Timer(10, this);
        checkOpen.start();

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creates and adds the left panel for the setting frame
     */
    private void addLeftPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        leftTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel boardSelection = new JLabel("Select Board");
        boardSelection.setForeground(CustomColor.LIGHT_GRAY);
        boardSelection.setFont(boardSelection.getFont ().deriveFont (18.0f));

        leftTopPanel.add(boardSelection);

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
        generalBoard.setForeground(CustomColor.LIGHT_GRAY);
        generalBoard.setFont(generalBoard.getFont ().deriveFont (16.0f));
        generalBoard.setSelected(true);

        quadBoard = new JRadioButton("Quad    ");
        quadBoard.setBackground(CustomColor.BUTTON);
        quadBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        quadBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        quadBoard.setForeground(CustomColor.LIGHT_GRAY);
        quadBoard.setFont(quadBoard.getFont ().deriveFont (16.0f));

        eightBoard = new JRadioButton("Octo    ");
        eightBoard.setBackground(CustomColor.BUTTON);
        eightBoard.setForeground(CustomColor.ON_BUTTON_LABEL);
        eightBoard.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        eightBoard.setForeground(CustomColor.LIGHT_GRAY);
        eightBoard.setFont(eightBoard.getFont ().deriveFont (16.0f));

        quarButton = new JRadioButton("Quarantine");
        quarButton.setBackground(CustomColor.BUTTON);
        quarButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        quarButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        quarButton.setForeground(CustomColor.LIGHT_GRAY);
        quarButton.setFont(quarButton.getFont ().deriveFont (16.0f));

        regButton = new JRadioButton("Standard");
        regButton.setBackground(CustomColor.BUTTON);
        regButton.setForeground(CustomColor.ON_BUTTON_LABEL);
        regButton.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        regButton.setForeground(CustomColor.LIGHT_GRAY);
        regButton.setFont(regButton.getFont ().deriveFont (16.0f));
        regButton.setSelected(true);

        ButtonGroup g1 = new ButtonGroup();
        g1.add(generalBoard);
        g1.add(quadBoard);
        g1.add(eightBoard);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(quarButton);
        g2.add(regButton);

        JLabel monoLabel = new JLabel();
        monoLabel.setIcon(single);
        monoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generalBoard.setSelected(true);
            }
        });

        JLabel quadLabel = new JLabel();
        quadLabel.setIcon(quad);
        quadLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quadBoard.setSelected(true);
            }
        });

        JLabel octoLabel = new JLabel();
        octoLabel.setIcon(oct);
        octoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eightBoard.setSelected(true);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 5;
        gbc.weighty = .1;

        leftPanel.add(leftTopPanel, gbc);

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
        quarPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        quarPanel.add(quarButton);
        quarPanel.add(regButton);

        leftPanel.add(quarPanel, gbc);

        mainPanel.add(leftPanel, gbcMain);
    }

    /**
     * Creates the center panel for the setting frame
     */
    private void addCenterPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel centerTitlePanel = new JPanel();
        centerTitlePanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        centerTitlePanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel diseaseSelection = new JLabel("Select Disease");
        diseaseSelection.setForeground(CustomColor.LIGHT_GRAY);
        diseaseSelection.setFont(diseaseSelection.getFont ().deriveFont (18.0f));

        centerTitlePanel.add(diseaseSelection);

        choice1 = new JRadioButton("Disease 1");
        choice1.setFont(choice1.getFont ().deriveFont (16.0f));
        choice1.setBackground(CustomColor.DAVYS_GRAY);
        choice1.setForeground(CustomColor.LIGHT_GRAY);
        choice1.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice1.setSelected(true);
        choice2 = new JRadioButton("Disease 2");
        choice2.setFont(choice2.getFont ().deriveFont (16.0f));
        choice2.setBackground(CustomColor.BUTTON);
        choice2.setForeground(CustomColor.LIGHT_GRAY);
        choice2.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice3 = new JRadioButton("Disease 3");
        choice3.setFont(choice3.getFont ().deriveFont (16.0f));
        choice3.setBackground(CustomColor.BUTTON);
        choice3.setForeground(CustomColor.LIGHT_GRAY);
        choice3.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        choice4 = new JRadioButton("Disease 4");
        choice4.setFont(choice4.getFont ().deriveFont (16.0f));
        choice4.setBackground(CustomColor.BUTTON);
        choice4.setForeground(CustomColor.LIGHT_GRAY);
        choice4.setBorder(BorderFactory.createLineBorder(CustomColor.ON_BUTTON_LABEL));
        custom = new JRadioButton("Custom");
        custom.setFont(custom.getFont ().deriveFont (16.0f));
        custom.setBackground(CustomColor.BUTTON);
        custom.setForeground(CustomColor.LIGHT_GRAY);
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
        pic1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice1.setSelected(true);
            }
        });
        pic2Label.setIcon(edit2);
        pic2Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice2.setSelected(true);
            }
        });
        pic3Label.setIcon(edit3);
        pic3Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice3.setSelected(true);
            }
        });
        pic4Label.setIcon(edit4);
        pic4Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choice4.setSelected(true);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = .2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        centerPanel.add(centerTitlePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(choice1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(pic1Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(choice2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(pic2Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(choice3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(pic3Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(choice4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(pic4Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 2;
        centerPanel.add(custom, gbc);

        contagiousPercent = new JTextField(1);
        contagiousPercent.setFont(contagiousPercent.getFont ().deriveFont (15.0f));
        contagiousPercent.setBackground(CustomColor.FIELD);
        contagiousPercent.setForeground(CustomColor.ON_BUTTON_LABEL);
        contagiousPercent.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)contagiousPercent.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        contagiousRange = new JTextField(1);
        contagiousRange.setFont(contagiousRange.getFont ().deriveFont (15.0f));
        contagiousRange.setBackground(CustomColor.FIELD);
        contagiousRange.setForeground(CustomColor.ON_BUTTON_LABEL);
        contagiousRange.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)contagiousRange.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        baseMortalityRate = new JTextField(1);
        baseMortalityRate.setFont(baseMortalityRate.getFont ().deriveFont (15.0f));
        baseMortalityRate.setBackground(CustomColor.FIELD);
        baseMortalityRate.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMortalityRate.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)baseMortalityRate.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        baseMinTimeSick = new JTextField(1);
        baseMinTimeSick.setFont(baseMinTimeSick.getFont ().deriveFont (15.0f));
        baseMinTimeSick.setBackground(CustomColor.FIELD);
        baseMinTimeSick.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMinTimeSick.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)baseMinTimeSick.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        baseMaxTimeSick = new JTextField(1);
        baseMaxTimeSick.setFont(baseMaxTimeSick.getFont ().deriveFont (15.0f));
        baseMaxTimeSick.setBackground(CustomColor.FIELD);
        baseMaxTimeSick.setForeground(CustomColor.ON_BUTTON_LABEL);
        baseMaxTimeSick.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)baseMaxTimeSick.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        startPercentHealthy = new JTextField(1);
        startPercentHealthy.setFont(startPercentHealthy.getFont ().deriveFont (15.0f));
        startPercentHealthy.setBackground(CustomColor.FIELD);
        startPercentHealthy.setForeground(CustomColor.ON_BUTTON_LABEL);
        startPercentHealthy.setMinimumSize(new Dimension(60, 10));
        ((AbstractDocument)startPercentHealthy.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        contagiousPercentLabel = new JLabel("<html>Contagious (%)</html>");
        contagiousPercentLabel.setForeground(CustomColor.LIGHT_GRAY);
        contagiousPercentLabel.setFont(contagiousPercentLabel.getFont ().deriveFont (15.0f));

        contagiousRangeLabel = new JLabel("<html>Contagious Range<br>(1-20)</html>");
        contagiousRangeLabel.setForeground(CustomColor.LIGHT_GRAY);
        contagiousRangeLabel.setFont(contagiousRangeLabel.getFont ().deriveFont (15.0f));

        baseMortalityRateLabel = new JLabel("Mortality (%)");
        baseMortalityRateLabel.setForeground(CustomColor.LIGHT_GRAY);
        baseMortalityRateLabel.setFont(baseMortalityRateLabel.getFont ().deriveFont (15.0f));

        baseMinTimeSickLabel = new JLabel("Min Sick (s)");
        baseMinTimeSickLabel.setForeground(CustomColor.LIGHT_GRAY);
        baseMinTimeSickLabel.setFont(baseMinTimeSickLabel.getFont ().deriveFont (15.0f));

        baseMaxTimeSickLabel = new JLabel("Max Sick (s)");
        baseMaxTimeSickLabel.setForeground(CustomColor.LIGHT_GRAY);
        baseMaxTimeSickLabel.setFont(baseMaxTimeSickLabel.getFont ().deriveFont (15.0f));

        startPercentHealthyLabel = new JLabel("Start Healthy (%)");
        startPercentHealthyLabel.setForeground(CustomColor.LIGHT_GRAY);
        startPercentHealthyLabel.setFont(startPercentHealthyLabel.getFont ().deriveFont (15.0f));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(contagiousPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(contagiousPercent, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMinTimeSickLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMinTimeSick, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMortalityRateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMortalityRate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(contagiousRangeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(contagiousRange, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMaxTimeSickLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(baseMaxTimeSick, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(startPercentHealthyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        centerPanel.add(startPercentHealthy, gbc);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        centerPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);

        mainPanel.add(centerPanel, gbcMain);
    }

    /**
     * Creates and adds the right panel for the setting frame
     */
    private void addRightPanel()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        rightTopPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, CustomColor.BACKGROUND));

        JLabel paramLabel = new JLabel("Select the Parameters");
        paramLabel.setForeground(CustomColor.LIGHT_GRAY);
        paramLabel.setFont(paramLabel.getFont ().deriveFont (18.0f));

        rightTopPanel.add(paramLabel);

        travelers = new JTextField(100);
        travelers.setText("8");
        travelers.setBackground(CustomColor.FIELD);
        travelers.setForeground(CustomColor.ON_BUTTON_LABEL);
        travelers.setMinimumSize(new Dimension(60, 10));
        travelers.setFont(travelers.getFont ().deriveFont (15.0f));
        ((AbstractDocument)travelers.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        timeUntilQuarantine = new JTextField(1);
        timeUntilQuarantine.setText("3");
        timeUntilQuarantine.setBackground(CustomColor.FIELD);
        timeUntilQuarantine.setForeground(CustomColor.ON_BUTTON_LABEL);
        timeUntilQuarantine.setMinimumSize(new Dimension(60, 10));
        timeUntilQuarantine.setFont(timeUntilQuarantine.getFont ().deriveFont (15.0f));
        ((AbstractDocument)timeUntilQuarantine.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        percentQuarantine = new JTextField(1);
        percentQuarantine.setText("80");
        percentQuarantine.setBackground(CustomColor.FIELD);
        percentQuarantine.setForeground(CustomColor.ON_BUTTON_LABEL);
        percentQuarantine.setMinimumSize(new Dimension(60, 10));
        percentQuarantine.setFont(percentQuarantine.getFont ().deriveFont (15.0f));
        ((AbstractDocument)percentQuarantine.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        asymptomaticChance = new JTextField(1);
        asymptomaticChance.setText("0");
        asymptomaticChance.setBackground(CustomColor.FIELD);
        asymptomaticChance.setForeground(CustomColor.ON_BUTTON_LABEL);
        asymptomaticChance.setMinimumSize(new Dimension(60, 10));
        asymptomaticChance.setFont(asymptomaticChance.getFont ().deriveFont (15.0f));
        ((AbstractDocument)asymptomaticChance.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        socialDistanceValue = new JTextField(1);
        socialDistanceValue.setText("30");
        socialDistanceValue.setBackground(CustomColor.FIELD);
        socialDistanceValue.setForeground(CustomColor.ON_BUTTON_LABEL);
        socialDistanceValue.setMinimumSize(new Dimension(60, 10));
        socialDistanceValue.setFont(socialDistanceValue.getFont ().deriveFont (15.0f));
        ((AbstractDocument)socialDistanceValue.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        percentSocialDist = new JTextField(1);
        percentSocialDist.setText("0");
        percentSocialDist.setBackground(CustomColor.FIELD);
        percentSocialDist.setForeground(CustomColor.ON_BUTTON_LABEL);
        percentSocialDist.setMinimumSize(new Dimension(60, 10));
        percentSocialDist.setFont(percentSocialDist.getFont ().deriveFont (15.0f));
        ((AbstractDocument)percentSocialDist.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        reinfectRate = new JTextField(1);
        reinfectRate.setText("0");
        reinfectRate.setBackground(CustomColor.FIELD);
        reinfectRate.setForeground(CustomColor.ON_BUTTON_LABEL);
        reinfectRate.setMinimumSize(new Dimension(60, 10));
        reinfectRate.setFont(reinfectRate.getFont ().deriveFont (15.0f));
        ((AbstractDocument)reinfectRate.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        antiBodyTime = new JTextField(1);
        antiBodyTime.setText("35");
        antiBodyTime.setBackground(CustomColor.FIELD);
        antiBodyTime.setForeground(CustomColor.ON_BUTTON_LABEL);
        antiBodyTime.setMinimumSize(new Dimension(60, 10));
        antiBodyTime.setFont(antiBodyTime.getFont ().deriveFont (15.0f));
        ((AbstractDocument)antiBodyTime.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        minAge = new JTextField(1);
        minAge.setText("20");
        minAge.setBackground(CustomColor.FIELD);
        minAge.setForeground(CustomColor.ON_BUTTON_LABEL);
        minAge.setMinimumSize(new Dimension(60, 10));
        minAge.setFont(minAge.getFont ().deriveFont (15.0f));
        ((AbstractDocument)minAge.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        maxAge = new JTextField(1);
        maxAge.setText("80");
        maxAge.setBackground(CustomColor.FIELD);
        maxAge.setForeground(CustomColor.ON_BUTTON_LABEL);
        maxAge.setMinimumSize(new Dimension(60, 10));
        maxAge.setFont(maxAge.getFont ().deriveFont (15.0f));
        ((AbstractDocument)maxAge.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        minConditions = new JTextField(1);
        minConditions.setText("0");
        minConditions.setBackground(CustomColor.FIELD);
        minConditions.setForeground(CustomColor.ON_BUTTON_LABEL);
        minConditions.setMinimumSize(new Dimension(60, 10));
        minConditions.setFont(minConditions.getFont ().deriveFont (15.0f));
        ((AbstractDocument)minConditions.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        maxConditions = new JTextField(1);
        maxConditions.setText("3");
        maxConditions.setBackground(CustomColor.FIELD);
        maxConditions.setForeground(CustomColor.ON_BUTTON_LABEL);
        maxConditions.setMinimumSize(new Dimension(60, 10));
        maxConditions.setFont(maxConditions.getFont ().deriveFont (15.0f));
        ((AbstractDocument)maxConditions.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        JLabel travelersLabel = new JLabel("Travelers (%)");
        travelersLabel.setForeground(CustomColor.LIGHT_GRAY);
        travelersLabel.setFont(travelersLabel.getFont ().deriveFont (16.0f));

        JLabel timeQuarLabel = new JLabel("Time until sick quarantine (s)");
        timeQuarLabel.setForeground(CustomColor.LIGHT_GRAY);
        timeQuarLabel.setFont(timeQuarLabel.getFont ().deriveFont (16.0f));

        JLabel percentQuarLabel = new JLabel("Sick who quarantine (%)");
        percentQuarLabel.setForeground(CustomColor.LIGHT_GRAY);
        percentQuarLabel.setFont(percentQuarLabel.getFont ().deriveFont (16.0f));

        JLabel asPercentLabel = new JLabel("Asymptomatic People (%)");
        asPercentLabel.setForeground(CustomColor.LIGHT_GRAY);
        asPercentLabel.setFont(asPercentLabel.getFont ().deriveFont (16.0f));

        JLabel socDistValLabel = new JLabel("Social Distance");
        socDistValLabel.setForeground(CustomColor.LIGHT_GRAY);
        socDistValLabel.setFont(socDistValLabel.getFont ().deriveFont (16.0f));

        JLabel socialDisPercentLabel = new JLabel("Social Dist. Participation (%)  ");
        socialDisPercentLabel.setForeground(CustomColor.LIGHT_GRAY);
        socialDisPercentLabel.setFont(socialDisPercentLabel.getFont ().deriveFont (16.0f));

        JLabel reinfectRateLabel = new JLabel("Can be reinfected (%)  ");
        reinfectRateLabel.setForeground(CustomColor.LIGHT_GRAY);
        reinfectRateLabel.setFont(reinfectRateLabel.getFont ().deriveFont (16.0f));

        JLabel antiBodyTimeLabel = new JLabel("How long antibodies last (s)  ");
        antiBodyTimeLabel.setForeground(CustomColor.LIGHT_GRAY);
        antiBodyTimeLabel.setFont(antiBodyTimeLabel.getFont ().deriveFont (16.0f));

        JLabel minAgeLabel = new JLabel("Min Age");
        minAgeLabel.setForeground(CustomColor.LIGHT_GRAY);
        minAgeLabel.setFont(minAgeLabel.getFont ().deriveFont (16.0f));

        JLabel maxAgeLabel = new JLabel("Max Age");
        maxAgeLabel.setForeground(CustomColor.LIGHT_GRAY);
        maxAgeLabel.setFont(maxAgeLabel.getFont ().deriveFont (16.0f));

        JLabel minCondLabel = new JLabel("Min Conditions");
        minCondLabel.setForeground(CustomColor.LIGHT_GRAY);
        minCondLabel.setFont(minCondLabel.getFont ().deriveFont (16.0f));

        JLabel maxCondLabel = new JLabel("Max Conditions");
        maxCondLabel.setForeground(CustomColor.LIGHT_GRAY);
        maxCondLabel.setFont(maxCondLabel.getFont ().deriveFont (16.0f));

        JLabel travelersA = new JLabel("<html>     The percent of people who can move <br/>     without bound in a divided board</html>");
        travelersA.setMinimumSize(new Dimension(250, 40));
        travelersA.setForeground(CustomColor.LIGHT_GRAY);
        travelersA.setFont(travelersA.getFont ().deriveFont (13.0f));

        JLabel timeUntilQuarantineA = new JLabel("<html>     The amount of time until a <br/>     sick person quarantines</html>");
        timeUntilQuarantineA.setMinimumSize(new Dimension(250, 40));
        timeUntilQuarantineA.setForeground(CustomColor.LIGHT_GRAY);
        timeUntilQuarantineA.setFont(timeUntilQuarantineA.getFont ().deriveFont (13.0f));

        JLabel percentQuarantineA = new JLabel("<html>     The percent of sick people <br/>     who will quarantine</html>");
        percentQuarantineA.setMinimumSize(new Dimension(250, 40));
        percentQuarantineA.setForeground(CustomColor.LIGHT_GRAY);
        percentQuarantineA.setFont(percentQuarantineA.getFont ().deriveFont (13.0f));

        JLabel asymptomaticChanceA = new JLabel("<html>     The percent of people asymptomatic <br/>     (no quarantine or dying, yet contagious)</html>");
        asymptomaticChanceA.setMinimumSize(new Dimension(250, 40));
        asymptomaticChanceA.setForeground(CustomColor.LIGHT_GRAY);
        asymptomaticChanceA.setFont(asymptomaticChanceA.getFont ().deriveFont (13.0f));

        JLabel socialDistanceValueA = new JLabel("<html>     The amount of space needed during <br/>     social distancing (30 recom.)</html>");
        socialDistanceValueA.setMinimumSize(new Dimension(250, 40));
        socialDistanceValueA.setForeground(CustomColor.LIGHT_GRAY);
        socialDistanceValueA.setFont(socialDistanceValueA.getFont ().deriveFont (13.0f));

        JLabel percentSocialDistA = new JLabel("<html>     Percent of people who social <br/>     distance (0 to turn off)</html>");
        percentSocialDistA.setMinimumSize(new Dimension(250, 40));
        percentSocialDistA.setForeground(CustomColor.LIGHT_GRAY);
        percentSocialDistA.setFont(percentSocialDistA.getFont ().deriveFont (13.0f));

        JLabel reinfectRateA = new JLabel("<html>     Percent of people who can be<br/>     reinfected (0 to turn off)</html>");
        reinfectRateA.setMinimumSize(new Dimension(250, 40));
        reinfectRateA.setForeground(CustomColor.LIGHT_GRAY);
        reinfectRateA.setFont(reinfectRateA.getFont ().deriveFont (13.0f));

        JLabel antiBodyTimeA = new JLabel("<html>     How long antibodies last <br/>     (when % reinfect > 0)</html>");
        antiBodyTimeA.setMinimumSize(new Dimension(250, 40));
        antiBodyTimeA.setForeground(CustomColor.LIGHT_GRAY);
        antiBodyTimeA.setFont(antiBodyTimeA.getFont ().deriveFont (13.0f));

        JLabel minAgeA = new JLabel("<html>     The min possible age of a person <br/>     (affects total sick time and mortality rate)</html>");
        minAgeA.setMinimumSize(new Dimension(250, 40));
        minAgeA.setForeground(CustomColor.LIGHT_GRAY);
        minAgeA.setFont(minAgeA.getFont ().deriveFont (13.0f));

        JLabel maxAgeA = new JLabel("<html>     The max possible age of a person <br>     (affects total sick time and mortality rate)</html>");
        maxAgeA.setMinimumSize(new Dimension(250, 40));
        maxAgeA.setForeground(CustomColor.LIGHT_GRAY);
        maxAgeA.setFont(maxAgeA.getFont ().deriveFont (13.0f));

        JLabel minConditionsA = new JLabel("<html>     The min possible preexisting condition <br/>      a person can have (affects mortality rate)</html>");
        minConditionsA.setMinimumSize(new Dimension(250, 40));
        minConditionsA.setForeground(CustomColor.LIGHT_GRAY);
        minConditionsA.setFont(minConditionsA.getFont ().deriveFont (13.0f));

        JLabel maxConditionsA = new JLabel("<html>     The max possible preexisting condition <br/>     a person can have (affects mortality rate)</html>");
        maxConditionsA.setMinimumSize(new Dimension(250, 40));
        maxConditionsA.setForeground(CustomColor.LIGHT_GRAY);
        maxConditionsA.setFont(maxConditionsA.getFont ().deriveFont (13.0f));

        double weightXLabel = 5;
        double weightXFeild = 5;
        double weightXArea = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 10;
        gbc.weighty = .1;

        rightPanel.add(rightTopPanel, gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(reinfectRateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(reinfectRate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(reinfectRateA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXLabel;
        gbc.weighty = 1;

        rightPanel.add(antiBodyTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXFeild;
        gbc.weighty = 1;

        rightPanel.add(antiBodyTime, gbc);

        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = weightXArea;
        gbc.weighty = 1;

        rightPanel.add(antiBodyTimeA, gbc);

        mainPanel.add(rightPanel, gbcMain);

        checkDiseaseFields();
        addKeyBindings();
    }

    /**
     * Creates and adds the bottom panel for the setting frame
     */
    private void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(CustomColor.BACKGROUND);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton continueSim = new JButton("Continue");
        continueSim.setBackground(CustomColor.BUTTON);
        continueSim.setFont(continueSim.getFont ().deriveFont (16.0f));
        continueSim.setForeground(CustomColor.ON_BUTTON_LABEL);

        continueSim.addActionListener(e -> closeSettings());

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbcMain);
    }

    private void addKeyBindings()
    {
        InputMap inputMap = ((JPanel)this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JPanel)this.getContentPane()).getActionMap();

        Action closeSettings = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeSettings();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("S"), "Close Settings");
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "Close Settings");
        actionMap.put("Close Settings", closeSettings);
    }

    /**Checks if the setting frame is being used and updates the board type and parameters that are selected each tick
     * and sets the text for the disease parameter panel based on the disease parameters
     * @param e event each tick
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!isVisible())
        {
            selectBoard();
            selectParams();
        }
        checkDiseaseFields();
    }

    /**
     * Helper Method for filling in the text fields when a user changes the selected disease
     */
    private void checkDiseaseFields()
    {
        if(custom.isSelected() && diseaseSel != 5)
        {
            contagiousPercent.setText("");
            contagiousRange.setText("");
            baseMortalityRate.setText("");
            baseMinTimeSick.setText("");
            baseMaxTimeSick.setText("");
            startPercentHealthy.setText("");
            diseaseSel = 5;
        }
        if(choice1.isSelected() && diseaseSel != 1)
        {
            disease = new Disease1();
            setDiseaseParameters();
            diseaseSel = 1;
        }
        if(choice2.isSelected() && diseaseSel != 2)
        {
            disease = new Disease2();
            setDiseaseParameters();
            diseaseSel = 2;
        }
        if(choice3.isSelected() && diseaseSel != 3)
        {
            disease = new Disease3();
            setDiseaseParameters();
            diseaseSel = 3;
        }
        if(choice4.isSelected() && diseaseSel != 4)
        {
            disease = new Disease4();
            setDiseaseParameters();
            diseaseSel = 4;
        }
    }

    /**
     * Helper Method for Disease Parameters in the Settings window
     */
    private void setDiseaseParameters()
    {
        contagiousPercent.setText(disease.getContagiousPercent() + "");
        contagiousRange.setText(disease.getContagiousRange() + "");
        baseMortalityRate.setText(disease.getBaseMortalityRate() + "");
        baseMinTimeSick.setText("" + Math.round(disease.getBaseMinTimeSick()/100.0));
        baseMaxTimeSick.setText("" + Math.round(disease.getBaseMaxTimeSick()/100.0));
        startPercentHealthy.setText(disease.getStartPercentHealthy() + "");
    }

    /**
     * Collected the data from the board selection panel
     */
    private void selectBoard()
    {
        if(generalBoard.isSelected())
            boardTypeNum = 1;
        else if(quadBoard.isSelected())
            boardTypeNum = 4;
        else if(eightBoard.isSelected())
            boardTypeNum = 8;

        if(quarButton.isSelected())
            quarBoardBool = true;
        else if(regButton.isSelected())
            quarBoardBool = false;
    }

    /**
     * Collects the data from the parameters board
     */
    private void selectParams()
    {
        travelersPer = Double.parseDouble(travelers.getText()) / 100;
        timeUntilQuarantineNum = Double.parseDouble(timeUntilQuarantine.getText()) * 100;
        quarantineChanceNum = Double.parseDouble(percentQuarantine.getText()) / 100;
        asymptomaticChanceNum = Double.parseDouble(asymptomaticChance.getText()) / 100;
        socialDistanceChanceNum = Double.parseDouble(percentSocialDist.getText()) / 100;
        socialDistanceValueNum = Double.parseDouble(socialDistanceValue.getText());
        minAgeNum = Double.parseDouble(minAge.getText());
        maxAgeNum = Double.parseDouble(maxAge.getText());
        minPreExistingConditionsNum = Integer.parseInt(minConditions.getText());
        maxPreExistingConditionsNum = Integer.parseInt(maxConditions.getText());
        reinfectRateNum = Double.parseDouble(reinfectRate.getText()) / 100;
        antiBodyTimeNum = Double.parseDouble(antiBodyTime.getText()) * 100;
    }

    /**
     * Closes the Settings window
     */
    public void closeSettings()
    {
        try
        {
            if(getTravelers().getText().equals("") || getTimeUntilQuarantine().getText().equals("") || getPercentQuarantine().getText().equals("") || getAsymptomaticChance().getText().equals("")
                    || getSocialDistanceValue().getText().equals("") || getPercentSocialDist().getText().equals("") || getMinAge().getText().equals("") || getMaxAge().getText().equals("")
                    || getMinConditions().getText().equals("") || getMaxConditions().getText().equals("") || getReinfectRate().getText().equals("") || getAntiBodyTime().getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters in settings before starting!");
            }
            else if(Double.parseDouble(getMinAge().getText()) > Double.parseDouble(getMaxAge().getText()) || Integer.parseInt(getMinConditions().getText()) > Integer.parseInt(getMaxConditions().getText()))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please make sure Min Age is less than or equal to Max Age and Min Conditions is less than or equal to Max Conditions!");
            }
            else if(contagiousPercent.getText().equals("") || contagiousRange.getText().equals("")
                    || baseMortalityRate.getText().equals("") || baseMinTimeSick.getText().equals("")
                    || baseMaxTimeSick.getText().equals("") || startPercentHealthy.getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please fill in all parameters for Custom Disease before starting!");
            }
            else if(Double.parseDouble(contagiousRange.getText()) > 20 || Double.parseDouble(contagiousRange.getText()) < 1)
            {
                JOptionPane.showMessageDialog(new JFrame(), "The contagious range must be between 1 - 20");
            }
            else if(Double.parseDouble(baseMinTimeSick.getText()) > Double.parseDouble(baseMaxTimeSick.getText()))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Please make sure Min Time Sick is less than or equal to Max Time Sick!");
            }
            else if(controlPanel.isPlaying())
            {
                JOptionPane.showMessageDialog(new JFrame(), "Reset simulation to enable changes to settings.");
                controlPanel.resumeSim();
                setVisible(false);
            }
            else setVisible(false);
        }
        catch (java.lang.NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please make sure all parameters are numbers and filled in correctly and Condition counts are integers!");
        }
    }

    /** Getter and Setter methods*/

    public double getTravelersPer()
    {
        return travelersPer;
    }

    public int getBoardTypeNum() {
        return boardTypeNum;
    }

    public double getMinAgeNum() {
        return minAgeNum;
    }

    public double getMaxAgeNum() {
        return maxAgeNum;
    }

    public int getMinPreExistingConditionsNum() {
        return minPreExistingConditionsNum;
    }

    public int getMaxPreExistingConditionsNum() {
        return maxPreExistingConditionsNum;
    }

    public double getSocialDistanceValueNum() {
        return socialDistanceValueNum;
    }

    public double getTimeUntilQuarantineNum() {
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

    public double getReinfectRateNum() {
        return reinfectRateNum;
    }

    public double getAntiBodyTimeNum() {
        return antiBodyTimeNum;
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
    public JTextField getReinfectRate()
    {
        return reinfectRate;
    }
    public JTextField getAntiBodyTime()
    {
        return antiBodyTime;
    }
    public JTextField getBaseMaxTimeSick() {
        return baseMaxTimeSick;
    }
    public JTextField getBaseMinTimeSick() {
        return baseMinTimeSick;
    }
    public JTextField getContagiousRange() {
        return contagiousRange;
    }
    public JTextField getContagiousPercent() {
        return contagiousPercent;
    }
    public JTextField getBaseMortalityRate() {
        return baseMortalityRate;
    }
    public JTextField getStartPercentHealthy() {
        return startPercentHealthy;
    }
}
