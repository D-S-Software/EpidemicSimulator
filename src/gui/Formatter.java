package gui;

import lib.CustomColor;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

    int pX, pY; //Used for mouse pointer action listener for menus bars

    /**
     * Formats a JFrame
     * @param frame frame object being formatted
     * @param backgroundColor background color
     * @param dim dimensions of the frame
     * @param layout layout object applied to the frame
     * @param iconFile filename for the icon symbol for the window
     */
    public void formatFrame(JFrame frame, Color backgroundColor, Dimension dim, LayoutManager layout, String iconFile)
    {
        frame.getContentPane().setBackground(backgroundColor);
        if(dim != null)
            frame.setPreferredSize(dim);
        ImageIcon imIco = new ImageIcon(ClassLoader.getSystemResource("res/" + iconFile));
        Image image = imIco.getImage();
        frame.setIconImage(image);
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Formats a JPanel
     * @param panel panel object being formatted
     * @param background background color
     * @param rect dimensions for border
     * @param layout layout object applied to the JPanel
     */
    public void formatPanel(JPanel panel, Color background, Rectangle rect, LayoutManager layout)
    {
        panel.setBackground(background);
        if(rect != null)
            panel.setBorder(BorderFactory.createEmptyBorder(rect.x,rect.y,rect.width,rect.height));
        if(layout != null)
            panel.setLayout(layout);
    }

    /**
     * Formats a JLabel with a photo
     * @param label label object being formatted
     * @param photo image name to be placed in label
     * @param width width of image
     * @param height height of image
     */
    public void formatLabel(JLabel label, String photo, int width, int height)
    {
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/" + photo));
        Image image1 = pic1.getImage();
        if(width != 0 && height != 0) //0,0 means not scaled
            image1 = image1.getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon edit1 = new ImageIcon(image1);
        label.setIcon(edit1);
    }

    /**
     * Formats a JLabel with text
     * @param label label object being formatted
     * @param textColor color of the text
     * @param fontSize size of the font
     * @param labelSize preferred size of the label
     */
    public void formatLabel(JLabel label, Color textColor, float fontSize, Dimension labelSize)
    {
        label.setForeground(textColor);
        label.setFont(label.getFont().deriveFont(fontSize));
        if(labelSize != null)
            label.setPreferredSize(labelSize);
    }

    /**
     * Formats a Label with text (includes a set minimum size)
     * @param label label object being formatted
     * @param textColor color of the text
     * @param fontSize size of the font
     * @param labelSize minimum size of the label
     */
    public void formatLabelMin(JLabel label, Color textColor, float fontSize, Dimension labelSize)
    {
        label.setForeground(textColor);
        label.setFont(label.getFont().deriveFont(fontSize));
        if(labelSize != null)
            label.setMinimumSize(labelSize);
    }

    /**
     * Formats a JButton with text
     * @param button button object being formatted
     * @param backgroundColor background color
     * @param foregroundColor color of text
     * @param borderColor color of border (if present)
     * @param fontSize size of the font
     */
    public void formatButton(JButton button, Color backgroundColor, Color foregroundColor, Color borderColor, float fontSize)
    {
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        button.setFont(button.getFont().deriveFont(fontSize));
        button.setForeground(foregroundColor);
        if(borderColor != null)
            button.setBorder(BorderFactory.createLineBorder(borderColor));
    }

    /**
     * Formats a JButton with text and a tooltip
     * @param button button object being formatted
     * @param backgroundColor background color
     * @param foregroundColor color of text
     * @param borderColor color of border
     * @param fontSize size of the font
     * @param toolTip Text to be displayed when mouse hovers over the button
     */
    public void formatButton(JButton button, Color backgroundColor, Color foregroundColor, Color borderColor, float fontSize, String toolTip)
    {
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        button.setFont(button.getFont().deriveFont(fontSize));
        button.setForeground(foregroundColor);
        button.setBorder(BorderFactory.createLineBorder(borderColor));
        button.setToolTipText(toolTip);
        button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
    }

    /**
     * Formats a JButton with a photo
     * @param button button object to be formatted
     * @param backgroundColor background color
     * @param photo name of photo to be used
     */
    public void formatButton(JButton button, Color backgroundColor, String photo)
    {
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        ImageIcon pic1 = new ImageIcon(ClassLoader.getSystemResource("res/" + photo));
        Image image1 = pic1.getImage();
        ImageIcon edit2 = new ImageIcon(image1);
        button.setIcon(edit2);
    }

    /**
     * Formats a JButton with a photo and tooltip. The photo is also scaled to 50, 50
     * @param button button object to be formatted
     * @param backgroundColor background color
     * @param borderColor color of border
     * @param photo name of photo to be used
     * @param toolTip String of text to be displayed on button when mouse is hovered over it (if applicable)
     */
    public void formatButton(JButton button, Color backgroundColor, Color borderColor, String photo, String toolTip)
    {
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(borderColor));
        ImageIcon picInfo = new ImageIcon(ClassLoader.getSystemResource("res/" + photo));
        Image image = picInfo.getImage();
        button.setIcon(new ImageIcon(image.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH)));
        button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        if(toolTip != null)
            button.setToolTipText(toolTip);
    }

    /**
     * Formats a JTextField and only allows digits to be entered into the field
     * @param textField text field object to be formatted
     * @param backgroundColor background color
     * @param foregroundColor color of text
     * @param borderColor color of the border (if applicable)
     * @param fontSize size of the font
     * @param size minimum size of the text field (if applicable)
     * @param text text to be displayed in the field (if applicable)
     */
    public void formatTextField(JTextField textField, Color backgroundColor, Color foregroundColor, Color borderColor, float fontSize, Dimension size, String text)
    {
        textField.setBackground(backgroundColor);
        textField.setForeground(foregroundColor);
        if(borderColor != null)
            textField.setBorder(BorderFactory.createLineBorder(borderColor));
        textField.setFont(textField.getFont().deriveFont(fontSize));
        if(size != null)
            textField.setMinimumSize(size);
        if(text != null)
            textField.setText(text);

        //Only allows Numbers to be entered into text field
        ((AbstractDocument)textField.getDocument()).setDocumentFilter(new DocumentFilter(){
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
    }

    /**
     * Formats a JRadioButton
     * @param radioButton radio button to be formatted
     * @param backgroundColor background color
     * @param foregroundColor color of text
     * @param borderColor color of the border
     * @param fontSize size of the font
     */
    public void formatRadioButton(JRadioButton radioButton, Color backgroundColor, Color foregroundColor, Color borderColor, float fontSize)
    {
        radioButton.setBackground(backgroundColor);
        radioButton.setForeground(foregroundColor);
        radioButton.setBorder(BorderFactory.createLineBorder(borderColor));
        radioButton.setFont(radioButton.getFont().deriveFont(fontSize));
    }

    /**
     * Creates a menu bar the removes the button at the top right of the window for a solid, custom top bar
     * @param frame frame to apply the custom menu bar to
     */
    public void setMenuBar(JFrame frame)
    {
        frame.setUndecorated(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            //Welp :P
        }

        // Create JMenuBar
        JMenuBar mb = new JMenuBar();
        mb.setBackground(CustomColor.CINEROUS);
        mb.setLayout(new BorderLayout());

        // Create panel
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(10, 25));
        p.setOpaque(false);

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
                frame.setLocation(frame.getLocation().x + me.getX() - pX, frame.getLocation().y + me.getY() - pY);
            }
        });

        frame.setJMenuBar(mb);
    }

    /**
     * Applies the given parameters to the Grid Bag Constraints object to be used in a GridBagLayout for either a panel or frame
     * @param gbc grid bag constraints object
     * @param x x position of element
     * @param y y position of element
     * @param w width of element
     * @param h height of element
     * @param wx relative weight for horizontal size
     * @param wy relative weight for vertical size
     * @param anchor position to set element to
     * @param fill int that decides to fill open space vertically and/or horizontally
     * @param insets Inset object that details a border around the element
     * @return Returns the updated grid bag constraints object
     */
    public GridBagConstraints setGBC(GridBagConstraints gbc, int x, int y, int w, int h, double wx, double wy, int anchor, int fill, Insets insets)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = wx;
        gbc.weighty = wy;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = insets;

        return gbc;
    }

    /**
     * Applies the given parameters to the Grid Bag Constraints object to be used in a GridBagLayout for either a panel or frame
     * @param gbc grid bag constraints object
     * @param x x position of element
     * @param y y position of element
     * @param w width of element
     * @param h height of element
     * @param wx relative weight for horizontal size
     * @param wy relative weight for vertical size
     * @return Returns the updated grid bag constraints object
     */
    public GridBagConstraints setGBC(GridBagConstraints gbc, int x, int y, int w, int h, double wx, double wy)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = wx;
        gbc.weighty = wy;

        return gbc;
    }
}