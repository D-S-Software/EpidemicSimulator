import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyPanel extends JPanel implements ActionListener
{
    public TallyPanel()
    {
        super();
        setBackground(Color.ORANGE);
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
