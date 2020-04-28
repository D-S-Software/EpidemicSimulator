import Library.CustomColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoFrame extends JFrame {

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JMenuBar mb;
    JPanel p;
    int pX, pY;

    public InfoFrame() {
        setBackground(CustomColor.BACKGROUND);
        setPreferredSize(new Dimension(850, 600));

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
        gbc.weighty = .1;

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
        gbc.weighty = .1;

        addBottomPanel();

        add(mainPanel);

        pack();
        setLocationByPlatform(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void addTopPanel()
    {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(CustomColor.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel title = new JLabel("Epidemic Simulator Info");

        topPanel.add(title);

        mainPanel.add(topPanel, gbc);
    }

    public void addMiddlePanel()
    {
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(CustomColor.WHITE);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Start here..."); //TODO Fill in info screen in this box

        middlePanel.add(infoArea, BorderLayout.BEFORE_FIRST_LINE);

        mainPanel.add(middlePanel, gbc);
    }

    public void addBottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(CustomColor.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton continueSim = new JButton("Close");
        continueSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });

        bottomPanel.add(continueSim);

        mainPanel.add(bottomPanel, gbc);
    }
}
