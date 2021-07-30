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

    JPanel mainPanel;
    GridBagConstraints gbc = new GridBagConstraints();
    ControlPanel controlPanel;
    Formatter formatter;

    /**
     * Creates an info frame for the main gui when the info button is clicked
     * @param controlPanel The controlPanel obj used to pause the simulation when the info panel is open
     */
    public InfoFrame(ControlPanel controlPanel) {

        this.controlPanel = controlPanel;
        formatter = new Formatter();
        formatter.formatFrame(this, CustomColor.BACKGROUND, new Dimension(850, 660), new GridLayout(), "virus1Logo.png");
        formatter.setMenuBar(this);

        mainPanel = new JPanel();
        formatter.formatPanel(mainPanel,CustomColor.BACKGROUND, null, new GridBagLayout());

        formatter.setGBC(gbc,0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2));
        addTopPanel();

        formatter.setGBC(gbc,0,1,1,1,1,20);
        addMiddlePanel();

        formatter.setGBC(gbc,0,3,1,1,1,0);
        addBottomPanel();

        add(mainPanel);

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
        addKeyBindings();
    }

    /**
     * Creates and adds the top title panel in the info frame
     */
    private void addTopPanel()
    {
        JPanel topPanel = new JPanel();
        formatter.formatPanel(topPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8, 8, 8, 8), null);

        JLabel title = new JLabel("Epidemic Simulator Info");
        formatter.formatLabel(title, CustomColor.LIGHT_GRAY,25.0f, null);
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

        middlePane.addHyperlinkListener(e -> {
            if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if(Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (IOException | URISyntaxException ioException) {
                        ioException.printStackTrace();
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
        formatter.formatPanel(bottomPanel,CustomColor.SPACE_CADET_LIGHT,new Rectangle(8,8,8,8),null);

        JButton continueSim = new JButton("Close");
        formatter.formatButton(continueSim,CustomColor.BUTTON, CustomColor.ON_BUTTON_LABEL, null,16.0f);
        continueSim.addActionListener(e -> closeInfo());
        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbc);
    }

    /**
     * Adds keybindings to the info frame - including 'i' to close the window
     */
    private void addKeyBindings()
    {
        InputMap inputMap = ((JPanel)this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JPanel)this.getContentPane()).getActionMap();

        Action closeInfo = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeInfo();
            }
        };
        inputMap.put(KeyStroke.getKeyStroke("I"), "Close Info");
        actionMap.put("Close Info", closeInfo);
    }

    private void closeInfo()
    {
        setVisible(false);
        if(controlPanel.isPlaying())
            controlPanel.resumeSim();
    }
}
