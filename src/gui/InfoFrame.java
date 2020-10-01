package gui;

import lib.CustomColor;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class InfoFrame extends JFrame {

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JMenuBar mb;
    JPanel p;
    ControlPanel controlPanel;
    int pX, pY;

    /**
     * Creates an info frame for the main gui when the info button is clicked
     * @param controlPanel The controlPanel obj used to pause the simulation when the info panel is open
     */
    public InfoFrame(ControlPanel controlPanel) {

        setBackground(CustomColor.BACKGROUND);
        setPreferredSize(new Dimension(850, 660));
        getContentPane().setBackground(CustomColor.BACKGROUND);
        this.controlPanel = controlPanel;

        mainPanel.setBackground(CustomColor.BACKGROUND);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        // Create JMenuBar
        mb = new JMenuBar();
        mb.setBackground(CustomColor.CINEROUS);
        mb.setLayout(new BorderLayout());

        // Create panel
        p = new JPanel();
        p.setPreferredSize(new Dimension(10, 25));
        p.setOpaque(false);

        // To west, mac style!
        mb.add(p, BorderLayout.WEST);

        // Add mouse listener for JMenuBar mb
        mb.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                pX = me.getX();
                pY = me.getY();
            }
        });

        // Add MouseMotionListener for detecting drag
        mb.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - pX, getLocation().y + me.getY() - pY);
            }
        });

        // Set the menu bar
        setJMenuBar(mb);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;

        addTopPanel();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 20;

        addMiddlePanel();

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;

        addBottomPanel();

        add(mainPanel);

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creates and adds the top title panel in the info frame
     */
    private void addTopPanel()
    {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel title = new JLabel("Epidemic Simulator Info");
        title.setForeground(CustomColor.LIGHT_GRAY);
        title.setFont(title.getFont ().deriveFont (25.0f));

        topPanel.add(title);

        mainPanel.add(topPanel, gbc);
    }

    /**
     * Creates and adds the middle panel for the info frame
     */
    private void addMiddlePanel() {

        java.net.URL url = ClassLoader.getSystemResource("res/EpidemicInfoBio.html");
        JEditorPane middlePane = new JEditorPane();
        middlePane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        middlePane.setEditable(false);

        middlePane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if(Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (URISyntaxException uriSyntaxException) {
                            uriSyntaxException.printStackTrace();
                        }
                    }
                }
            }
        });

        if (url != null) {
            try {
                middlePane.setPage(url);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + url);
            }
        } else {
            System.err.println("Couldn't find file: src/res/EpidemicInfoBio.html");
        }

        mainPanel.add(middlePane,gbc);
    }
    /**
     * Creates and adds the bottom panel for the info frame
     */
    private void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(CustomColor.SPACE_CADET_LIGHT);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton continueSim = new JButton("Close");
        continueSim.setBackground(CustomColor.BUTTON);
        continueSim.setFont(continueSim.getFont ().deriveFont (16.0f));
        continueSim.setForeground(CustomColor.ON_BUTTON_LABEL);

        continueSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                if(controlPanel.isPlaying())
                    controlPanel.resumeSim();
            }
        });

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbc);
    }
}
