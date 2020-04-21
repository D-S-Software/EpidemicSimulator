import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener
{


    /** A Board object is required to the TalleyPanel can be placed based on the Board object's BoardDimensions */
    public TallyPanel(Dimensions tDimens)
    {
        setBounds(tDimens.xOrigin,tDimens.yOrigin,tDimens.xLen,tDimens.yLen);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);

        Graphics2D g2D = (Graphics2D)g;


    }

    public void actionPerformed(ActionEvent event)
    {
        repaint();
    }

}
